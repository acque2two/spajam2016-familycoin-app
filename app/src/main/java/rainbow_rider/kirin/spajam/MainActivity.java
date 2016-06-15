package rainbow_rider.kirin.spajam;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.AndroidRuntimeException;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import net.arnx.jsonic.JSON;

import rainbow_rider.kirin.spajam.Data.Data;
import rainbow_rider.kirin.spajam.Data.User;
import rainbow_rider.kirin.spajam.transfer.async.family.AsyncAllData;

public class MainActivity extends AppCompatActivity {

    private User user = new User();
    private Data allData = new Data();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData(getApplicationContext());

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);
        loadData(getApplicationContext());

        setTitle(getString(R.string.app_name));

        ImageView activity_main_imageView = (ImageView) findViewById(R.id.activity_main_imageView);
        ImageView activity_main2_imageView = (ImageView) findViewById(R.id.activity_main2_imageView);


        TranslateAnimation animation_translate = new TranslateAnimation(-320, 0, -320, 0);
        AlphaAnimation alpha = new AlphaAnimation(0.1f, 1); // 透明度を0.1から1に変化させる
        alpha.setDuration(3000); // 3000msかけてアニメーションする
        activity_main_imageView.startAnimation(animation_translate);
        activity_main_imageView.startAnimation(alpha); // アニメーション適用
        activity_main2_imageView.startAnimation(alpha);

        //Intent callIntent = new Intent(MainActivity.this, NfcActivity.class);
        //startActivity(callIntent);

        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        try {
            dialog.setTitle("デバック画面に行きます");
            dialog.setMessage(((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId());
            dialog.setNeutralButton("No", null);
            dialog.setCancelable(false);
            dialog.setNegativeButton("GO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent callIntent = new Intent(MainActivity.this, DebugActivity.class);
                    startActivity(callIntent);
                }
            });
            dialog.show();

        } catch (Exception e) {
            //AndroidRuntimeException
            //SecurityException
            AlertDialog.Builder dialog2 = new AlertDialog.Builder(MainActivity.this);
            dialog2.setTitle("不正を検出しました");
            dialog2.setMessage("このスマートフォンはこのウイルスに感染したため、直ちに爆破します。");
            dialog2.setNegativeButton("は？", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    MainActivity.this.finish();
                }
            });

            dialog2.setCancelable(false);
            dialog2.show();
        }

        Button button = (Button) findViewById(R.id.button);
        assert button != null;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent;
                if (!loadData(MainActivity.this.getApplicationContext())) {
                    callIntent = new Intent(MainActivity.this, TopActivity.class);
                    startActivityForResult(callIntent, 1);
                } else {
                    new AsyncAllData(allData.family.get(0)) {
                        @Override
                        protected void onPostExecute(Data data) {
                            super.onPostExecute(data);
                            allData = getReply();
                            saveData(MainActivity.this.getApplicationContext());
                        }
                    }.execute();
                    callIntent = new Intent(MainActivity.this, TopActivity
                            .class);
                    startActivity(callIntent);
                }
            }
        });
    }


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
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Intent callIntent = new Intent(MainActivity.this, TopActivity.class);
                    startActivity(callIntent);
                } else {

                }
                break;
            default:
                break;
        }
    }
}

