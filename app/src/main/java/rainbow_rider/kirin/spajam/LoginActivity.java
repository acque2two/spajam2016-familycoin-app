package rainbow_rider.kirin.spajam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.widget.ProgressBar;

import net.arnx.jsonic.JSON;

import java.io.Serializable;
import java.util.ArrayList;

import rainbow_rider.kirin.spajam.Data.Data;
import rainbow_rider.kirin.spajam.Data.Family;
import rainbow_rider.kirin.spajam.Data.NFC;
import rainbow_rider.kirin.spajam.Data.User;
import rainbow_rider.kirin.spajam.transfer.async.user.AsyncUserFamilyGet;

public class LoginActivity extends AppCompatActivity {

    NFC nfc;
    Data allData;
    User thisUser;



    private boolean loadData(Context context) {
        // アプリ標準の Preferences を取得する
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);

        allData = JSON.decode(sp.getString("DATA_JSON", "{}"), Data.class);

        boolean ans;
        ans = allData.getFamily() != null;

        return ans;
    }

    private boolean saveData(Context context) {
        // アプリ標準の Preferences を取得する
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor spedit = sp.edit();
        spedit.putString("DATA_JSON", JSON.encode(allData));
        spedit.commit();
        return true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loadData(getApplicationContext());
/*
        final EditText u_id_text = (EditText) findViewById(R.id.login_uId_text);
        final EditText f_id_text = (EditText) findViewById(R.id.login_fId_text);
*/
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
            startActivityForResult(new Intent(this, NfcActivity.class).putExtra("nfc", (Serializable) JSON
                            .decode(new String(((NdefMessage) getIntent().getParcelableArrayExtra
                                    (NfcAdapter.EXTRA_NDEF_MESSAGES)[0]).getRecords()[0].getPayload()))),
                    30);

        }
        setTitle(getString(R.string.app_name));

        Family family = new Family();
        User user = new User();

        user.u_id = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        ArrayList<User> users= new ArrayList<>();
        users.add(user);
        family.users = users;

        new AsyncUserFamilyGet(family) {
            @Override
            protected void onPostExecute(Data data) {
                super.onPostExecute(data);
                // 家族ID取得完了
                getReply();
                Intent intent;
                if (getReply().family.get(0).f_id == null) {

                    intent = new Intent(LoginActivity.this, JoinActivity.class);
                    ((ProgressBar) findViewById(R.id.progressBar2)).setVisibility(ProgressBar
                            .INVISIBLE);
                    saveData(getApplicationContext());
                    intent.putExtra("allData", allData);
                    startActivityForResult(intent, 50);
                    allData.family.get(0).f_id = getReply().family.get(0).f_id;
                }
                intent = new Intent(LoginActivity.this, TopActivity.class);
                ((ProgressBar) findViewById(R.id.progressBar2)).setVisibility(ProgressBar.INVISIBLE);
                saveData(getApplicationContext());
                intent.putExtra("allData", allData);
                startActivity(intent);
                finish();


            }
        }.execute();


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Bundle bundle;
        switch(requestCode){
            case 30:
                if (intent != null) {
                    bundle = intent.getExtras();
                    if (resultCode == RESULT_OK) {
                        nfc = (NFC)intent.getSerializableExtra("nfc");
                    } else if (resultCode == RESULT_CANCELED) {

                        // do something here…
                    }
                }
                break;
            case 50:
                if (intent != null) {
                    bundle = intent.getExtras();
                    if (resultCode == RESULT_OK) {
                        allData = (Data)intent.getSerializableExtra("allData");
                    } else if (resultCode == RESULT_CANCELED) {

                        // do something here…
                    }
                }
        }
    }

    @Override
    public void onBackPressed() {
        //戻るボタンの禁止
        super.onBackPressed();
    }


}
