package rainbow_rider.kirin.spajam;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import rainbow_rider.kirin.spajam.Data.Data;
import rainbow_rider.kirin.spajam.Data.Family;
import rainbow_rider.kirin.spajam.Data.NFC;
import rainbow_rider.kirin.spajam.Data.User;

public class NfcActivity extends Activity implements NfcAdapter.CreateNdefMessageCallback,
        NfcAdapter.OnNdefPushCompleteCallback {

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
        setContentView(R.layout.activity_main);

        mContext = this.getApplicationContext();

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        mNfcAdapter.setNdefPushMessageCallback(this, this);
        mNfcAdapter.setOnNdefPushCompleteCallback(this, this);
    }

    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
        //Beamで送りたいメッセージ
        NFC nfc = new NFC();
        nfc.adult = true;
        nfc.manager = true;
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
            Log.d("NFC/ONRESUME/RECEIVE","received:" + receiveBeam);
            Toast.makeText(this.getApplicationContext(),"データをうけとりました",Toast.LENGTH_LONG).show();
            loadData(getApplicationContext());
            NFC nfc = JSON.decode(receiveBeam, NFC.class);
            if(allData.family.isEmpty())
                allData.family.add(new Family());
            if(allData.family.get(0).users.isEmpty())
                allData.family.get(0).users.add(new User());
            allData.family.get(0).users.get(0).adult = nfc.adult;
            allData.family.get(0).users.get(0).admin = nfc.manager;
            allData.family.get(0).users.get(0).sex = nfc.sex;
            allData.family.get(0).f_id = nfc.fixedNum;
            Intent intent_res = new Intent();
            Bundle bundle = new Bundle();
            bundle.putSerializable("nfc",nfc);
            intent_res.putExtras(bundle);
            setResult(RESULT_OK, intent_res);
            finish();

        }
    }

    @Override
    public void onNdefPushComplete(NfcEvent event) {
        //Beam送信完了時のハンドラー
        ndefPushHandler.obtainMessage(MESSAGE_SENT).sendToTarget();
    }


    Data allData;

    private boolean loadData(Context context) {
        // アプリ標準の Preferences を取得する
        SharedPreferences sp = this.getPreferences(Context.MODE_PRIVATE);

        allData = JSON.decode(sp.getString("DATA_JSON", "{}"), Data.class);

        boolean ans;
        ans = allData.getFamily() != null;

        return ans;
    }

    private boolean saveData(Context context) {
        // アプリ標準の Preferences を取得する
        SharedPreferences sp = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor spedit = sp.edit();
        spedit.putString("DATA_JSON", JSON.encode(allData));
        spedit.commit();
        return true;
    }


}
