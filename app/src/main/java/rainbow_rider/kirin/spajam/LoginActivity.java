package rainbow_rider.kirin.spajam;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import net.arnx.jsonic.JSON;

import java.util.ArrayList;

import rainbow_rider.kirin.spajam.Data.Data;
import rainbow_rider.kirin.spajam.Data.Family;
import rainbow_rider.kirin.spajam.Data.User;
import rainbow_rider.kirin.spajam.transfer.async.family.AsyncFamilyAdd;
import rainbow_rider.kirin.spajam.transfer.async.family.AsyncFamilyExist;
import rainbow_rider.kirin.spajam.transfer.async.user.AsyncUserAdd;
import rainbow_rider.kirin.spajam.transfer.async.user.AsyncUserExist;

public class LoginActivity extends AppCompatActivity {

    Family family;
    EditText name_text;
    EditText u_id_text;
    EditText f_id_text;
    RadioGroup sex_group;
    RadioGroup adult_group;
    RadioGroup admin_group;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    private User user = new User();
    private Data allData = new Data();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name_text = ( EditText ) findViewById( R.id.login_name_text );
        u_id_text = ( EditText ) findViewById( R.id.login_uId_text );
        f_id_text = ( EditText ) findViewById( R.id.login_fId_text );
        sex_group = ( RadioGroup ) findViewById( R.id.login_radioG_sex );
        adult_group = ( RadioGroup ) findViewById( R.id.login_radioG_adult );
        admin_group = ( RadioGroup ) findViewById( R.id.login_radioG_admin );

        setTitle(getString(R.string.app_name));

        final Boolean[] sex = new Boolean[1];
        assert sex_group != null;
        sex_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton) findViewById(i);
                if(radioButton.getText().equals("おとこ")){
                    sex[0] = true;
                }else if (radioButton.getText().equals("おんな")) {
                    sex[0] = false;
                }
            }
        });

        final Boolean[] adult = new Boolean[1];
        assert adult_group != null;
        adult_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton) findViewById(i);
                if(radioButton.getText().equals("おとな")){
                    adult[0] = true;
                }else if (radioButton.getText().equals("こども")) {
                    adult[0] = false;
                }
            }
        });

        final Boolean[] admin = new Boolean[1];
        assert admin_group != null;
        admin_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton) findViewById(i);
                if(radioButton.getText().equals("はい")){
                    admin[0] = true;
                }else if (radioButton.getText().equals("いいえ")) {
                    admin[0] = false;
                }
            }
        });

        //Create Button
        Button button = (Button) findViewById(R.id.login_create_button);
        assert button != null;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = name_text.getText().toString();
                String u_id = u_id_text.getText().toString();
                String f_id = f_id_text.getText().toString();

                boolean[] an = new boolean[2];
                an = checkInput(name, u_id, f_id, sex, adult, admin);
                if ( an[ 0 ] ) {
                    //error
                    AlertDialog.Builder alert02 = new AlertDialog.Builder(LoginActivity.this);
//ダイアログタイトルをセット
                    alert02.setTitle("エラー");
//ダイアログメッセージをセット
                    alert02.setMessage("すでに登録されているIDです");
                    // ボタンがクリックされた時に呼び出されるコールバックリスナーを登録します
                    alert02.setPositiveButton("OK", null);
//ダイアログ表示
                    alert02.show();
                }else if(an[1]){
                    AlertDialog.Builder alert02 = new AlertDialog.Builder(LoginActivity.this);
//ダイアログタイトルをセット
                    alert02.setTitle("エラー");
//ダイアログメッセージをセット
                    alert02.setMessage("入力していない項目があります");
                    // ボタンがクリックされた時に呼び出されるコールバックリスナーを登録します
                    alert02.setPositiveButton("OK", null);
//ダイアログ表示
                    alert02.show();
                }else {
                    user.setU_name(name);
                    user.setU_id(u_id);
                    user.setF_id(f_id);
                    user.setSex(sex[0]);
                    user.setAdult(adult[0]);
                    user.setAdmin(admin[0]);
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
                    saveData(LoginActivity.this);

                    Toast.makeText(LoginActivity.this, "作成", Toast.LENGTH_SHORT).show();
                }
            }
        });


/*過去の遺物 フォント変更
        TextView activity_login_title_textView = (TextView) findViewById(R.id.activity_login_title_textView);

        activity_login_title_textView.setTypeface(Typeface.SERIF);
*/
    }


    private boolean saveData(Context context) {
        // アプリ標準の Preferences を取得する
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor spedit = sp.edit();
        if ( allData.family == null ) {
            allData.family = new ArrayList<>();
            allData.family.add( family );
        }
        allData.family.get(0).users.add(user);

        spedit.putString("my_id", user.getU_name());
        spedit.putString("DATA_JSON", JSON.encode(allData));
        spedit.apply();
        return true;

    }

    public boolean[] checkInput(String name, String  u_id, String f_id, Boolean[] sex,Boolean[] adult,Boolean[] admin){
        final boolean[] ans = new boolean[2];
        for(int i = 0; i < ans.length; i++){
            ans[i] = false;
        }

        User u = new User();
        u.setU_id(u_id);
        u.setF_id(f_id);
        u.setSex(sex[0]);
        u.setAdult(adult[0]);
        u.setAdmin(admin[0]);

        ArrayList<User> uList = new ArrayList<User>(){};
        uList.add(u);

        Family family = new Family();
        family.setUser(uList);
        family.setF_id(f_id);

        new AsyncUserExist(family){
            @Override
            protected void onPostExecute(Data data) {
                super.onPostExecute(data);
                ans[0] = data.isStatus();
            }
        };

        int white = android.graphics.Color.rgb(250,250,250);
        int red = android.graphics.Color.rgb(239,154,154);

            name_text.setBackgroundColor(white);
            u_id_text.setBackgroundColor(white);
            f_id_text.setBackgroundColor(white);
            sex_group.setBackgroundColor(white);
            adult_group.setBackgroundColor(white);
            admin_group.setBackgroundColor(white);

        if (name == null){
            name_text.setBackgroundColor(red);
        }else if(u_id == null){
            u_id_text.setBackgroundColor(red);
        }else if(f_id == null){
            f_id_text.setBackgroundColor(red);
        }else if (sex == null){
            sex_group.setBackgroundColor(red);
        }else if (adult == null){
            adult_group.setBackgroundColor(red);
        }else if (admin == null){
            admin_group.setBackgroundColor(red);
        }else{
            ans[1] = true;
        }

        return ans;
    }

    @Override
    public void onBackPressed() {
        //戻るボタンの禁止
        //super.onBackPressed();
    }

}
