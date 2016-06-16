package rainbow_rider.kirin.spajam;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class JoinActivity extends AppCompatActivity {

    String g_Name;
    String g_uId;
    String g_fId;
    int g_sex = -1;
    int g_adult = -1;
    int g_admin = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        final EditText nameText = (EditText) findViewById(R.id.join_name_text);
        final EditText uIdText = (EditText) findViewById(R.id.join_id_text);
        final EditText fIdText = (EditText) findViewById(R.id.join_family_text);

        final ArrayList<EditText> editTextArrayList = new ArrayList<EditText>();
        editTextArrayList.add(nameText);
        editTextArrayList.add(uIdText);
        editTextArrayList.add(fIdText);

        final RadioGroup sexGroup = (RadioGroup) findViewById(R.id.join_sex_group);
        final RadioGroup adultGroup = (RadioGroup) findViewById(R.id.join_adult_group);
        final RadioGroup adminGroup = (RadioGroup) findViewById(R.id.join_admin_group);

        final ArrayList<RadioGroup> radioGroupArrayList = new ArrayList<RadioGroup>();
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
                g_Name = nameText.getText().toString();
                g_uId = uIdText.getText().toString();
                g_fId = fIdText.getText().toString();

                boolean[] check = checkInput();

                for (int i = 0; i < editTextArrayList.size(); i++) {
                    if (check[i]) {
                        editTextArrayList.get(i).setBackgroundColor(red);
                    } else {
                        editTextArrayList.get(i).setBackgroundColor(white);
                    }
                }

                for (int i = 0; i < radioGroupArrayList.size(); i++) {
                    if (check[i + 3]) {
                        radioGroupArrayList.get(i).setBackgroundColor(red);
                    } else {
                        radioGroupArrayList.get(i).setBackgroundColor(white);
                    }
                }

            }
        });
    }

    public boolean[] checkInput() {
        boolean[] ans = new boolean[6];
        if (!g_Name.equals("")) {
            ans[0] = true;
        }
        if (!g_uId.equals("")) {
            ans[1] = true;
        }
        if (!g_fId.equals("")) {
            ans[2] = true;
        }
        if (g_sex >= 0) {
            ans[3] = true;
        }
        if (g_adult >= 0) {
            ans[4] = true;
        }
        if (g_admin >= 0) {
            ans[5] = true;
        }
        return ans;
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
