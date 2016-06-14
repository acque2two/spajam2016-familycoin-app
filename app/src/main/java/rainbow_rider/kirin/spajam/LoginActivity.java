package rainbow_rider.kirin.spajam;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import net.arnx.jsonic.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import rainbow_rider.kirin.spajam.Data.Data;
import rainbow_rider.kirin.spajam.Data.Family;
import rainbow_rider.kirin.spajam.Data.User;
import rainbow_rider.kirin.spajam.transfer.async.family.AsyncFamilyAdd;
import rainbow_rider.kirin.spajam.transfer.async.family.AsyncFamilyExist;
import rainbow_rider.kirin.spajam.transfer.async.user.AsyncUserAdd;
import rainbow_rider.kirin.spajam.transfer.async.user.AsyncUserExist;

public class LoginActivity extends AppCompatActivity {

    public Family family;

    public String name;
    public String u_id;
    public String f_id;
    public int sex = -1;
    public int adult = -1;
    public int admin = -1;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    private User user = new User();
    private Data allData = new Data();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText name_text = (EditText) findViewById(R.id.login_name_text);
        final EditText u_id_text = (EditText) findViewById(R.id.login_uId_text);
        final EditText f_id_text = (EditText) findViewById(R.id.login_fId_text);
        RadioGroup sex_group = (RadioGroup) findViewById(R.id.login_radioG_sex);
        RadioGroup adult_group = (RadioGroup) findViewById(R.id.login_radioG_adult);
        RadioGroup admin_group = (RadioGroup) findViewById(R.id.login_radioG_admin);

        setTitle(getString(R.string.app_name));

        assert sex_group != null;
        sex_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton) findViewById(i);
                if (radioButton.getText().equals("おとこ")) {
                    sex = 1;
                } else if (radioButton.getText().equals("おんな")) {
                    sex = 0;
                }
            }
        });

        assert adult_group != null;
        adult_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton) findViewById(i);
                if (radioButton.getText().equals("おとな")) {
                    adult = 1;
                } else if (radioButton.getText().equals("こども")) {
                    adult = 0;
                }
            }
        });

        assert admin_group != null;
        admin_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton) findViewById(i);
                if (radioButton.getText().equals("はい")) {
                    admin = 1;
                } else if (radioButton.getText().equals("いいえ")) {
                    admin = 0;
                }
            }
        });

        //Create Button
        Button button = (Button) findViewById(R.id.login_create_button);
        assert button != null;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assert name_text != null;
                assert f_id_text != null;
                assert u_id_text != null;

                name = name_text.getText().toString();
                u_id = u_id_text.getText().toString();
                f_id = f_id_text.getText().toString();

                int i = 10;

                int ans = checkInputList();

                if (ans == -1) {
                    new CreateDialog(LoginActivity.this).alertButton("エラー", "入力されていない項目があります","OK").show();
                } else if (ans == -2) {
                    new CreateDialog(LoginActivity.this).alertButton("エラー", "すでに存在しているIDです", "Ok").show();
                } else {
                    user.setU_name(name);
                    user.setU_id(u_id);
                    user.setF_id(f_id);
                    user.setSex(true);
                    user.setAdult(true);
                    user.setAdmin(true);
                    ArrayList<User> users = new ArrayList<User>();
                    users.add(user);
                    family = new Family();
                    family.setF_id(f_id);
                    family.setUser(users);

                    new AsyncFamilyExist(family) {
                        @Override
                        protected void onPostExecute(Data data) {
                            super.onPostExecute(data);
                            Data reply = getReply();
                            LoginActivity.this.findViewById(R.id.progressBar2).setVisibility(View.GONE);
                            if (!reply.isStatus()) {

                                // 家族未登録
                                final AsyncUserAdd userAdd = new AsyncUserAdd(family) {
                                    @Override
                                    protected void onPostExecute(Data data) {
                                        super.onPostExecute(data);
                                        LoginActivity.this.findViewById(R.id.progressBar2).setVisibility(View.GONE);
                                        Toast.makeText(LoginActivity.this.getApplicationContext
                                                (), "登録が完了しました。", Toast.LENGTH_LONG).show();

                                        Intent intent = new Intent();
                                        setResult(RESULT_OK, intent);

                                        finish();
                                    }
                                };

                                // 確認ダイアログの生成
                                AlertDialog.Builder alertDlg = new AlertDialog.Builder(LoginActivity.this);
                                alertDlg.setTitle("家族が未登録です");
                                alertDlg.setMessage("家族を新規に登録する必要があります。登録しますか？");
                                alertDlg.setPositiveButton(
                                        "はい",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                final EditText editView = new EditText(LoginActivity.this);
                                                new AlertDialog.Builder(LoginActivity.this)
                                                        .setIcon(android.R.drawable.ic_dialog_info)
                                                        .setTitle("家族の表示名を設定してください")
                                                        //setViewにてビューを設定します。
                                                        .setView(editView)
                                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int whichButton) {
                                                                //入力した文字をトースト出力する
                                                                family.setF_name(editView.getText().toString());
                                                                new AsyncFamilyAdd(family) {
                                                                    @Override
                                                                    protected void onPostExecute(Data data) {
                                                                        super.onPostExecute(data);

                                                                        Toast.makeText(
                                                                                LoginActivity.this.getApplicationContext(), "家族登録が完了しました。", Toast.LENGTH_LONG).show();
                                                                        userAdd.execute();
                                                                        LoginActivity.this.findViewById(R.id.progressBar2).setVisibility(View.GONE);
                                                                    }
                                                                }.execute();
                                                                LoginActivity.this.findViewById(R.id.progressBar2).setVisibility(View.VISIBLE);
                                                            }
                                                        })
                                                        .setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int whichButton) {
                                                            }
                                                        })
                                                        .show();
                                                LoginActivity.this.findViewById(R.id.progressBar2).setVisibility(View.VISIBLE);
                                            }
                                        });
                                alertDlg.setNegativeButton(
                                        "いいえ",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                // Cancel ボタンクリック処理
                                                return;
                                            }
                                        });

                                alertDlg.create().show();
                            } else {
                                //初回
                                new AsyncUserAdd(family) {
                                    @Override
                                    protected void onPostExecute(Data data) {
                                        super.onPostExecute(data);
                                        LoginActivity.this.findViewById(R.id.progressBar2)
                                                .setVisibility(View.GONE);
                                        Toast.makeText(LoginActivity.this.getApplicationContext
                                                (), "登録が完了しました。", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent();
                                        setResult(RESULT_OK, intent);

                                        finish();
                                    }
                                }.execute();
                            }
                        }
                    }.execute();
                    LoginActivity.this.findViewById(R.id.progressBar2).setVisibility(View.VISIBLE);
                    saveData(LoginActivity.this.getApplicationContext());

                    Toast.makeText(LoginActivity.this, "作成", Toast.LENGTH_SHORT).show();
                }
            }
        });


/*過去の遺物 フォント変更
        TextView activity_login_title_textView = (TextView) findViewById(R.id.activity_login_title_textView);

        activity_login_title_textView.setTypeface(Typeface.SERIF);
*/
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


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

    private boolean saveData(Context context) {
        // アプリ標準の Preferences を取得する
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor spedit = sp.edit();
        if (allData.family == null) {
            allData.family = new ArrayList<>();
            allData.family.add(family);
        }
        allData.family.get(0).users.add(user);

        spedit.putString("my_id", user.getU_name());
        spedit.putString("DATA_JSON", JSON.encode(allData));
        spedit.commit();
        return true;

    }

    @Override
    public void onBackPressed() {
        //戻るボタンの禁止
        super.onBackPressed();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Login Page", // TODO: Define a title for the content shown.
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
                "Login Page", // TODO: Define a title for the content shown.
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
