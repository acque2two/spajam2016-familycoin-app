package rainbow_rider.kirin.spajam;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import net.arnx.jsonic.JSON;

import java.nio.charset.Charset;

import rainbow_rider.kirin.spajam.Data.NFC;

public class NfcActivity extends Activity implements NfcAdapter.CreateNdefMessageCallback,
        NfcAdapter.OnNdefPushCompleteCallback {

    String g_Name;
    String g_uId;
    String g_fId;
    int g_sex = -1;
    int g_adult = -1;
    int g_admin = -1;

    private static final int MESSAGE_SENT = 1;
    private static Context mContext;
    private final static Handler ndefPushHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_SENT:
                    Toast.makeText(mContext,
                            "ひつようなデータをおくりました",
                            Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };
    private NfcAdapter mNfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);

        mContext = this.getApplicationContext();

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        mNfcAdapter.setNdefPushMessageCallback(this, this);
        mNfcAdapter.setOnNdefPushCompleteCallback(this, this);
    }

    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
        //Beamで送りたいメッセージ
        NFC nfc = new NFC();
        nfc.name = "tarou";
        nfc.adult = false;
        nfc.manager = false;
        nfc.sex = true;
        nfc.fixedNum = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE))
                .getDeviceId();

        NdefRecord[] record = new NdefRecord[]{createMimeRecord(
                "application/rainbow_rider.kirin.spajam", JSON.encode(nfc).getBytes()),
                NdefRecord.createApplicationRecord("rainbow_rider.kirin.spajam")};

        NdefMessage msg = new NdefMessage(record);
        return msg;
    }

    public NdefRecord createMimeRecord(String mimeType, byte[] payload) {
        byte[] mimeBytes = mimeType.getBytes(Charset.forName("UTF-8"));
        NdefRecord mimeRecord = new NdefRecord(
                NdefRecord.TNF_MIME_MEDIA, mimeBytes, new byte[0], payload);
        return mimeRecord;
    }

    @Override
    public void onNdefPushComplete(NfcEvent event) {
        //Beam送信完了時のハンドラー
        ndefPushHandler.obtainMessage(MESSAGE_SENT).sendToTarget();
    }
}
