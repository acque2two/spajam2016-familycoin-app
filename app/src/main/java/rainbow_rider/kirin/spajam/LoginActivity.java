package rainbow_rider.kirin.spajam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.widget.ProgressBar;

import net.arnx.jsonic.JSON;

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
        SharedPreferences sp = context.getSharedPreferences("allData",Context.MODE_PRIVATE);

        allData = JSON.decode(sp.getString("DATA_JSON", "{}"), Data.class);
        boolean ans;
        ans = allData.getFamily() != null;
        return ans;
    }

    private boolean saveData(Context context) {
        SharedPreferences sp =  context.getSharedPreferences("allData",Context.MODE_PRIVATE);
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

        setTitle(getString(R.string.app_name));
        Family family = new Family();
        User user = new User();
        user.u_id = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        family.users = users;
        new AsyncUserFamilyGet(family) {
            @Override
            protected void onPostExecute(Data data) {
                super.onPostExecute(data);
                Intent intent;

                if (getReply().family.isEmpty() || getReply().family.get(0).f_id == null ) {
                    intent = new Intent(LoginActivity.this, JoinActivity.class);
                    ((ProgressBar) findViewById(R.id.progressBar2)).setVisibility(ProgressBar
                            .INVISIBLE);
                    saveData(getApplicationContext());
                    intent.putExtra("allData", allData);
                    startActivityForResult(intent, 50);
                }else {
                    allData.family.get(0).f_id = getReply().family.get(0).f_id;
                    intent = new Intent(LoginActivity.this, TopActivity.class);
                    ((ProgressBar) findViewById(R.id.progressBar2)).setVisibility(ProgressBar
                            .INVISIBLE);
                    saveData(getApplicationContext());
                    intent.putExtra("allData", allData);
                    startActivity(intent);
                    finish();
                }
            }
        }.execute();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Bundle bundle;
        switch (requestCode) {
            case 30:
                if (intent != null) {
                    bundle = intent.getExtras();
                    if (resultCode == RESULT_OK) {
                        nfc = (NFC) intent.getSerializableExtra("nfc");
                    }
                }
                break;
            case 50:
                if (intent != null) {
                    bundle = intent.getExtras();
                    allData = (Data) intent.getSerializableExtra("allData");
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
