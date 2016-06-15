package rainbow_rider.kirin.spajam;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import net.arnx.jsonic.JSON;

import java.nio.charset.Charset;

import rainbow_rider.kirin.spajam.Data.Data;
import rainbow_rider.kirin.spajam.Data.NFC;

public class NfcActivity extends Activity implements NfcAdapter.CreateNdefMessageCallback,
        NfcAdapter.OnNdefPushCompleteCallback {

    private static final int MESSAGE_SENT = 1;
    private NfcAdapter mNfcAdapter;
    private static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this.getApplicationContext();

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        mNfcAdapter.setNdefPushMessageCallback(this, this);
        mNfcAdapter.setOnNdefPushCompleteCallback(this, this);
    }

    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
        //Beamで送りたいメッセージ
        String text = "Beam text";
        NFC nfc = new NFC();
        nfc.adult = true;
        nfc.manager = true;
        nfc.sex = true;
        nfc.fixedNum =( (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId() ;

        NdefRecord[] record = new NdefRecord[]{ createMimeRecord(
                "application/rainbow_rider.kirin.spajam", JSON.encode(nfc).getBytes()) } ;

                /**
                 * 他のデバイスがAndroidアプリケーションレコード（AAR）を
                 * 受信したときに指定されたアプリケーションが実行されることが保証されています。
                */
                //,NdefRecord.createApplicationRecord("com.example.demobeam")


        NdefMessage msg = new NdefMessage(record);
        return msg;
    }

    public NdefRecord createMimeRecord(String mimeType, byte[] payload) {
        byte[] mimeBytes = mimeType.getBytes(Charset.forName("US-ASCII"));
        NdefRecord mimeRecord = new NdefRecord(
                NdefRecord.TNF_MIME_MEDIA, mimeBytes, new byte[0], payload);
        return mimeRecord;
    }

    @Override
    public void onResume() {
        super.onResume();

        //Beamのアクションを受け取ったとき
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
            Intent intent = getIntent();
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(
                    NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage msg = (NdefMessage) rawMsgs[0];
            //Beamのメッセージ
            String receiveBeam = new String(msg.getRecords()[0].getPayload());
            Toast.makeText(getApplicationContext(),
                    "received:" + receiveBeam,
                    Toast.LENGTH_LONG).show();
            NFC nfc = JSON.decode(receiveBeam.toString(), NFC.class);


        }
    }

    @Override
    public void onNdefPushComplete(NfcEvent event) {
        //Beam送信完了時のハンドラー
        ndefPushHandler.obtainMessage(MESSAGE_SENT).sendToTarget();
    }

    private final static Handler ndefPushHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_SENT:
                    Toast.makeText(mContext,
                            "Beam sent.....",
                            Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };
}