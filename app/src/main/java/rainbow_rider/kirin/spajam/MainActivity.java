package rainbow_rider.kirin.spajam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
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
        loadData( getApplicationContext() );
        setContentView(R.layout.activity_main);
        loadData( getApplicationContext() );

        setTitle(getString(R.string.app_name));

        ImageView activity_main_imageView = (ImageView) findViewById(R.id.activity_main_imageView);
        ImageView activity_main2_imageView = (ImageView) findViewById(R.id.activity_main2_imageView);


        AlphaAnimation alpha = new AlphaAnimation(0.1f, 1); // 透明度を0.1から1に変化させる
        alpha.setDuration(3000); // 3000msかけてアニメーションする
        activity_main_imageView.startAnimation(alpha); // アニメーション適用
        activity_main2_imageView.startAnimation(alpha);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent;
                if ( !loadData( MainActivity.this.getApplicationContext() ) ) {
                    callIntent = new Intent( MainActivity.this, LoginActivity.class );
                    startActivityForResult( callIntent , 1);
                }else{
                    new AsyncAllData( allData.family.get( 0 ) ) {
                        @Override
                        protected void onPostExecute( Data data ) {
                            super.onPostExecute( data );
                            allData = getReply();
                            saveData( MainActivity.this.getApplicationContext() );
                        }
                    }.execute();
                    callIntent = new Intent( MainActivity.this, TopActivity.class );
                    startActivity( callIntent );
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

