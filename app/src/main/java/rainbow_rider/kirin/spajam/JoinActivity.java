package rainbow_rider.kirin.spajam;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

import rainbow_rider.kirin.spajam.Data.Data;
import rainbow_rider.kirin.spajam.Data.F;
import rainbow_rider.kirin.spajam.Data.Family;
import rainbow_rider.kirin.spajam.Data.User;
import rainbow_rider.kirin.spajam.transfer.async.family.AsyncFamilyAdd;
import rainbow_rider.kirin.spajam.transfer.async.family.AsyncFamilyExist;

public class JoinActivity extends AppCompatActivity {

    String g_Name;
    String g_uId;
    String g_fId;
    int g_sex = -1;
    int g_adult = -1;
    int g_admin = -1;

    ProgressDialog progressDialog;

    Data allData;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        final EditText nameText = (EditText) findViewById(R.id.join_name_text);
        final EditText fIdText = (EditText) findViewById(R.id.join_family_text);

        final ArrayList<EditText> editTextArrayList = new ArrayList<>();
        editTextArrayList.add(nameText);
        editTextArrayList.add(fIdText);

        final RadioGroup sexGroup = (RadioGroup) findViewById(R.id.join_sex_group);
        final RadioGroup adultGroup = (RadioGroup) findViewById(R.id.join_adult_group);
        final RadioGroup adminGroup = (RadioGroup) findViewById(R.id.join_admin_group);

        final ArrayList<RadioGroup> radioGroupArrayList = new ArrayList<>();
        radioGroupArrayList.add(sexGroup);
        radioGroupArrayList.add(adultGroup);
        radioGroupArrayList.add(adminGroup);

        final int white = Color.rgb(250, 250, 250);
        final int red = Color.rgb(239, 154, 154);

        AlertDialog.Builder alert = new AlertDialog.Builder(JoinActivity.this);
        alert.setTitle("");
        alert.setMessage("あなたは家族で初めて登録する人ですか？\n家族で登録している人がいる場合「いいえ」を選択してください");
        alert.setCancelable(false);
        alert.setPositiveButton("はい", null);
        alert.setNeutralButton("いいえ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent callIntent = new Intent(JoinActivity.this, LoginActivity.class);
                startActivity(callIntent);
            }
        });
        alert.show();

        assert sexGroup != null;
        sexGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton checkedButton = (RadioButton) findViewById(checkedId);
                sexGroup.setBackgroundColor(white);
                assert checkedButton != null;
                if (checkedButton.getText().equals("おとこ")) {
                    g_sex = 1;
                } else if (checkedButton.getText().equals("おんな")) {
                    g_sex = 0;
                }
            }
        });

        assert adultGroup != null;
        adultGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton checkedButton = (RadioButton) findViewById(checkedId);
                adultGroup.setBackgroundColor(white);
                assert checkedButton != null;
                if (checkedButton.getText().equals("おとな")) {
                    g_adult = 1;
                } else if (checkedButton.getText().equals("こども")) {
                    g_adult = 0;
                }
            }
        });

        assert adminGroup != null;
        adminGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton checkedButton = (RadioButton) findViewById(checkedId);
                adminGroup.setBackgroundColor(white);
                assert checkedButton != null;
                if (checkedButton.getText().equals("はい")) {
                    g_admin = 1;
                } else if (checkedButton.getText().equals("いいえ")) {
                    g_admin = 0;
                }
            }
        });

        Button createButton = (Button) findViewById(R.id.join_create_button);
        assert createButton != null;
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assert nameText != null;
                assert fIdText != null;

                g_Name = nameText.getText().toString();
                g_uId = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
                g_fId = fIdText.getText().toString();

                boolean[] check = checkInput();

                for (int i = 0; i < editTextArrayList.size(); i++) {
                    if (check[i]) {
                        editTextArrayList.get(i).setBackgroundColor(white);
                    } else {
                        editTextArrayList.get(i).setBackgroundColor(red);
                    }
                }

                for (int i = 0; i < radioGroupArrayList.size(); i++) {
                    if (check[i + 2]) {
                        radioGroupArrayList.get(i).setBackgroundColor(white);

                    } else {
                        radioGroupArrayList.get(i).setBackgroundColor(red);

                    }
                }
                if (check[check.length - 1]) {
                    progressDialog = new ProgressDialog(JoinActivity.this);
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.setMessage("実行中");
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    User user = new User();
                    user.setU_name(g_Name);
                    user.setU_id(g_uId);
                    user.setF_id(g_fId);
                    user.setSex(g_sex == 1);
                    user.setAdult(g_adult == 1);
                    user.setAdmin(g_admin == 1);

                    ArrayList<User> users = new ArrayList<>();
                    users.add(user);

                    final Family family = new Family();
                    family.setUsers(users);

                    final ArrayList<Family> families = new ArrayList<>();
                    families.add(family);

                    new AsyncFamilyExist(family){
                        @Override
                        protected void onPostExecute(Data data) {
                            super.onPostExecute(data);
                            getReply();
                            if(getReply().isStatus()){
                                new CreateDialog(JoinActivity.this).alertButton("すでに存在するIDです", "家族IDを変更してください", "OK");
                                progressDialog.dismiss();
                            }else{
                                new AsyncFamilyAdd(family){
                                    @Override
                                    protected void onPostExecute(Data data) {
                                        super.onPostExecute(data);
                                        allData.setFamily(families);
                                        F.Save(allData);
                                        progressDialog.dismiss();
                                        Intent callIntent = new Intent(JoinActivity.this, TopActivity.class);
                                        startActivity(callIntent);
                                    }
                                }.execute();
                            }
                        }
                    }.execute();
                }
            }
        });
    }

    public boolean[] checkInput() {
        boolean[] ans = new boolean[6];
        if (!g_Name.equals("")) {
            ans[0] = true;
        }
        if (!g_fId.equals("")) {
            ans[1] = true;
        }
        if (g_sex >= 0) {
            ans[2] = true;
        }
        if (g_adult >= 0) {
            ans[3] = true;
        }
        if (g_admin >= 0) {
            ans[4] = true;
        }

        int ok = 0;
        for (int i = 0; i < ans.length - 1; i++) {
            if (ans[0]) {
                ok += 1;
            }
        }
        if (ok >= ans.length - 1) {
            ans[ans.length - 1] = true;
        }
        return ans;
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
            Action.TYPE_VIEW, // TODO: choose an action type.
            "Join Page", // TODO: Define a title for the content shown.
            // TODO: If you have web page content that matches this app activity's content,
            // make sure this auto-generated web page URL is correct.
            // Otherwise, set the URL to null.
            Uri.parse("http://host/path"),
            // TODO: Make sure this auto-generated app URL is correct.
            Uri.parse("android-app://rainbow_rider.kirin.spajam/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
            Action.TYPE_VIEW, // TODO: choose an action type.
            "Join Page", // TODO: Define a title for the content shown.
            // TODO: If you have web page content that matches this app activity's content,
            // make sure this auto-generated web page URL is correct.
            // Otherwise, set the URL to null.
            Uri.parse("http://host/path"),
            // TODO: Make sure this auto-generated app URL is correct.
            Uri.parse("android-app://rainbow_rider.kirin.spajam/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
/*

    public int checkInputList(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("処理を実行中しています");
        progressDialog.setCancelable(true);
        progressDialog.show();

        final EditText name_text = (EditText) findViewById(R.id.login_name_text);
        final EditText u_id_text = (EditText) findViewById(R.id.login_uId_text);
        final EditText f_id_text = (EditText) findViewById(R.id.login_fId_text);
        RadioGroup sex_group = (RadioGroup) findViewById(R.id.login_radioG_sex);
        RadioGroup adult_group = (RadioGroup) findViewById(R.id.login_radioG_adult);
        RadioGroup admin_group = (RadioGroup) findViewById(R.id.login_radioG_admin);

        int white = Color.rgb(250, 250, 250);
        int red = Color.rgb(239, 154, 154);

        name_text.setBackgroundColor(white);
        u_id_text.setBackgroundColor(white);
        f_id_text.setBackgroundColor(white);
        sex_group.setBackgroundColor(white);
        adult_group.setBackgroundColor(white);
        admin_group.setBackgroundColor(white);

        int[] ans = new int[]{0};

        if (name.equals("")){
            name_text.setBackgroundColor(red);
            ans[0] = -1;
        }
        if(u_id.equals("")){
            u_id_text.setBackgroundColor(red);
            ans[0] = -1;

        }
        if(f_id.equals("")){
            f_id_text.setBackgroundColor(red);
            ans[0] = -1;
        }
        if (sex < 0) {
            sex_group.setBackgroundColor(red);
            ans[0] = -1;
        }
        if (adult < 0) {
            adult_group.setBackgroundColor(red);
            ans[0] = -1;
        }
        if (admin < 0) {
            admin_group.setBackgroundColor(red);
            ans[0] = -1;
        }
        return ans[0];
    }
 */
