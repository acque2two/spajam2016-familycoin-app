package rainbow_rider.kirin.spajam;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.gms.common.api.GoogleApiClient;

public class LoginActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

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
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = name_text.getText().toString();
                String u_id = u_id_text.getText().toString();
                String f_id = f_id_text.getText().toString();

                saveUserData(LoginActivity.this, name, u_id, f_id, sex[0], adult[0], admin[0]);

            }
        });


/*過去の遺物 フォント変更
        TextView activity_login_title_textView = (TextView) findViewById(R.id.activity_login_title_textView);

        activity_login_title_textView.setTypeface(Typeface.SERIF);
*/
    }

    private void saveUserData(Context context, String name, String u_id, String f_id, Boolean sex, Boolean adult, Boolean admin) {
        // アプリ標準の Preferences を取得する
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);

        // Preferences に書き込むための Editor クラスを取得する
        SharedPreferences.Editor editor = sp.edit();

        // putXxxx("キー",データ) にて書き込むデータを登録する
        editor.putString("name", name);
        editor.putString("u_id", u_id);
        editor.putString("f_id", f_id);
        editor.putBoolean("sex", sex);
        editor.putBoolean("admin", admin);
        editor.putBoolean("adult", adult);

        // 書き込みを確定する
        editor.commit();

    }

    @Override
    public void onBackPressed() {
        //戻るボタンの禁止
        //super.onBackPressed();
    }

}
