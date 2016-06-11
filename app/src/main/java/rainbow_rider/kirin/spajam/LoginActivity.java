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

public class LoginActivity extends AppCompatActivity {

    Family family;
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

        setTitle(getString(R.string.app_name));

        final EditText name_text = (EditText) findViewById(R.id.login_name_text);
        final EditText u_id_text = (EditText) findViewById(R.id.login_uId_text);
        final EditText f_id_text = (EditText) findViewById(R.id.login_fId_text);
        final RadioGroup sex_group = (RadioGroup) findViewById(R.id.login_radioG_sex);
        RadioGroup adult_group = (RadioGroup) findViewById(R.id.login_radioG_adult);
        RadioGroup admin_group = (RadioGroup) findViewById(R.id.login_radioG_admin);

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

                user.setU_name(name);
                user.setU_id(u_id);
                user.setF_id(f_id);
                user.setSex(sex[0]);
                user.setAdult(adult[0]);
                user.setAdmin(admin[0]);
                ArrayList<User> users = new ArrayList<User>(  );
                users.add( user );
                family = new Family();
                family.setF_id( f_id );
                family.setUser( users );

                new AsyncFamilyExist( family ){
                    @Override
                    protected void onPostExecute( Data data ) {
                        super.onPostExecute( data );
                        Data reply = getReply();
                        LoginActivity.this.findViewById( R.id.progressBar2 ).setVisibility( View.GONE );
                        if (!reply.isStatus()){

                            // 家族未登録
                            final AsyncUserAdd userAdd = new AsyncUserAdd( family ) {
                                @Override
                                protected void onPostExecute( Data data ) {
                                    super.onPostExecute( data );
                                    LoginActivity.this.findViewById( R.id.progressBar2 ).setVisibility( View.GONE );
                                    Toast.makeText( LoginActivity.this.getApplicationContext
                                            (), "登録が完了しました。1", Toast.LENGTH_LONG ).show();

                                    Intent intent = new Intent();
                                    setResult(RESULT_OK, intent);

                                    finish();
                                }
                            };

                            // 確認ダイアログの生成
                            AlertDialog.Builder alertDlg = new AlertDialog.Builder( LoginActivity.this );
                            alertDlg.setTitle("家族が未登録です");
                            alertDlg.setMessage("家族を新規に登録する必要があります。登録しますか？");
                            alertDlg.setPositiveButton(
                                    "はぁい",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            final EditText editView = new EditText( LoginActivity.this );
                                            new AlertDialog.Builder( LoginActivity.this )
                                                    .setIcon( android.R.drawable.ic_dialog_info )
                                                    .setTitle( "家族の表示名を設定してください" )
                                                    //setViewにてビューを設定します。
                                                    .setView( editView )
                                                    .setPositiveButton( "OK", new DialogInterface.OnClickListener() {
                                                        public void onClick( DialogInterface dialog, int whichButton ) {
                                                            //入力した文字をトースト出力する
                                                            family.setF_name( editView.getText().toString() );
                                                            new AsyncFamilyAdd( family ) {
                                                                @Override
                                                                protected void onPostExecute( Data data ) {
                                                                    super.onPostExecute( data );

                                                                    Toast.makeText(
                                                                            LoginActivity.this.getApplicationContext(), "家族登録が完了しました。2", Toast.LENGTH_LONG).show();
                                                                    userAdd.execute();
                                                                    LoginActivity.this.findViewById( R.id.progressBar2 ).setVisibility(View.GONE );
                                                                }
                                                            }.execute();
                                                            LoginActivity.this.findViewById( R.id.progressBar2 ).setVisibility(View.VISIBLE );
                                                        }
                                                    } )
                                                    .setNegativeButton( "キャンセル", new DialogInterface.OnClickListener() {
                                                        public void onClick( DialogInterface dialog, int whichButton ) {
                                                        }
                                                    } )
                                                    .show();
                                            LoginActivity.this.findViewById( R.id.progressBar2 ).setVisibility(View.VISIBLE );
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
                            new AsyncUserAdd( family ) {
                                @Override
                                protected void onPostExecute( Data data ) {
                                    super.onPostExecute( data );
                                    LoginActivity.this.findViewById( R.id.progressBar2 )
                                                      .setVisibility( View.GONE );
                                    Toast.makeText( LoginActivity.this.getApplicationContext
                                            (), "登録が完了しました。3", Toast.LENGTH_LONG ).show();
                                    Intent intent = new Intent();
                                    setResult(RESULT_OK, intent);

                                    finish();
                                }
                            }.execute();
                        }
                    }
                }.execute(  );
                LoginActivity.this.findViewById( R.id.progressBar2 ).setVisibility( View.VISIBLE );
                saveData(LoginActivity.this);

                Toast.makeText(LoginActivity.this, "作成", Toast.LENGTH_SHORT).show();
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

        spedit.putString("DATA_JSON", JSON.encode(allData));
        spedit.apply();
        return true;

    }

    @Override
    public void onBackPressed() {
        //戻るボタンの禁止
        super.onBackPressed();
    }

}
