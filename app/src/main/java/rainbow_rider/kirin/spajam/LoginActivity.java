package rainbow_rider.kirin.spajam;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import net.arnx.jsonic.JSON;

import java.util.ArrayList;

import rainbow_rider.kirin.spajam.Data.Data;
import rainbow_rider.kirin.spajam.Data.F;
import rainbow_rider.kirin.spajam.Data.Family;
import rainbow_rider.kirin.spajam.Data.NFC;
import rainbow_rider.kirin.spajam.Data.User;
import rainbow_rider.kirin.spajam.transfer.async.user.AsyncUserAdd;
import rainbow_rider.kirin.spajam.transfer.async.user.AsyncUserFamilyGet;

public class LoginActivity extends AppCompatActivity {

    private Data allData;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        try {
            ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        }catch (Exception e){
            //AndroidRuntimeException
            //SecurityException
            AlertDialog.Builder dialog2 = new AlertDialog.Builder(LoginActivity.this);
            dialog2.setTitle("不正を検出しました");
            dialog2.setMessage("このスマートフォンはこのウイルスに感染したため、直ちに爆破します。");
            dialog2.setNegativeButton("は？", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    LoginActivity.this.finish();
                }
            });

            dialog2.setCancelable(false);
            dialog2.show();
        }

        //ログイン判定
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("実行中");
        progressDialog.setCancelable(false);
        boolean load;
        try{
            allData = F.Load();
            load = allData.family.get(0).f_id != null;
        }catch (NullPointerException e){
            load = false;
        }
        if(load){
            //情報あり
            progressDialog.show();
            new AsyncUserFamilyGet(allData){
                @Override
                protected void onPostExecute(Data data) {
                    super.onPostExecute(data);
                    allData = getReply();
                    F.Save(allData);
                    progressDialog.dismiss();
                    Intent callIntent = new Intent(LoginActivity.this, TopActivity.class );
                    startActivity(callIntent);
                }
            }.execute();
        }else{
            //情報なし
        }

        Button button = (Button) findViewById(R.id.login_join_button);
        assert button != null;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(LoginActivity.this, JoinActivity.class);
                startActivity(callIntent);
            }
        });
    }

    //NFC受け取り
    @Override
    public void onResume() {
        super.onResume();

        //Beamのアクションを受け取ったとき
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("実行中");
        progressDialog.setCancelable(false);
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
            progressDialog.show();
            Intent intent = getIntent();

            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(
                    NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage msg = (NdefMessage) rawMsgs[0];
            //Beamのメッセージ
            String receiveBeam = new String(msg.getRecords()[0].getPayload());
            Log.d("NFC/ONRESUME/RECEIVE","received:" + receiveBeam);
            Toast.makeText(this.getApplicationContext(),"データをうけとりました",Toast.LENGTH_LONG).show();
            NFC nfc = JSON.decode(receiveBeam, NFC.class);

            String fixedNum = nfc.fixedNum; //親ID

            final User mUser = new User(); //自分
            mUser.setU_id(((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId());
            mUser.setU_name(nfc.name);
            mUser.setSex(nfc.sex);
            mUser.setAdult(nfc.adult);
            mUser.setAdmin(nfc.manager);

            Family family = new Family();
            User fUser = new User(); //親
            fUser.u_id = fixedNum;
            ArrayList<User> users= new ArrayList<>();
            users.add(fUser);
            family.users = users;
            family.setF_id(fixedNum);

            //ServerからFamilyをGet
            //Get family from server
            new AsyncUserFamilyGet(family){
                @Override
                protected void onPostExecute(Data data) {
                    super.onPostExecute(data);
                    Data myData = getReply();
                    myData.getFamily().get(0).getUser().add(mUser);
                    allData = myData;
                    F.Save(allData);
                    //Serverに自分を追加
                    new AsyncUserAdd(allData){
                        @Override
                        protected void onPostExecute(Data data) {
                            super.onPostExecute(data);
                            progressDialog.dismiss();
                            Intent callIntent = new Intent(LoginActivity.this, TopActivity.class);
                            startActivity(callIntent);
                        }
                    }.execute();
                }
            }.execute();

            /*
            Intent intent_res = new Intent();
            Bundle bundle = new Bundle();
            bundle.putSerializable("nfc",nfc);
            intent_res.putExtras(bundle);
            setResult(RESULT_OK, intent_res);
            finish();
            */
        }
    }

    @Override
    public void onBackPressed() {
        //戻るボタンの禁止
        super.onBackPressed();
    }
}
