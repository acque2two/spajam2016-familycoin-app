package rainbow_rider.kirin.spajam;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import net.arnx.jsonic.JSON;

import java.util.ArrayList;

import java.io.Serializable;

import rainbow_rider.kirin.spajam.Data.Data;
import rainbow_rider.kirin.spajam.Data.User;
import rainbow_rider.kirin.spajam.transfer.async.family.AsyncAllData;

public class MainActivity extends AppCompatActivity {

    private User user = new User();
    private Data allData = new Data();

    private boolean VISIBILITY = true;

    private Animation bottom_to_top;
    private Animation top_to_bottom;
    private Animation right_to_left;
    private Animation left_to_right;
    private Animation top_right;
    private Animation top_left;

    ImageView bottom;
    ImageView left;
    ImageView right;
    ImageView topLeft;
    ImageView topRight;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData(getApplicationContext());
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
            startActivityForResult(new Intent(this, NfcActivity.class).putExtra("nfc",
                    (Serializable) JSON.decode(new String(((NdefMessage) getIntent()
                            .getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)[0])
                            .getRecords()[0].getPayload()))), 30);
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);
        //loadData(getApplicationContext());

        //setTitle(getString(R.string.app_name));

        ImageView activity_main_imageView = (ImageView) findViewById(R.id.activity_main_imageView);
        ImageView activity_main2_imageView = (ImageView) findViewById(R.id.activity_main2_imageView);


        bottom_to_top = AnimationUtils.loadAnimation(this, R.anim.bottom_to_top);
        top_to_bottom = AnimationUtils.loadAnimation(this, R.anim.top_to_bottom);
        right_to_left = AnimationUtils.loadAnimation(this, R.anim.right_to_left);
        left_to_right = AnimationUtils.loadAnimation(this, R.anim.left_to_right);
        top_left = AnimationUtils.loadAnimation(this, R.anim.top_left);
        top_right = AnimationUtils.loadAnimation(this, R.anim.top_right);

        final ArrayList<ImageView> images = new ArrayList<ImageView>() {{
            add(bottom);
            add(left);
            add(right);
            add(topLeft);
            add(topRight);
        }};
        bottom.setAnimation(bottom_to_top);
        bottom.setVisibility(View.VISIBLE);

        left.setAnimation(left_to_right);
        left.setVisibility(View.VISIBLE);

        right.setAnimation(right_to_left);
        right.setVisibility(View.VISIBLE);

        topLeft.setAnimation(top_left);
        topLeft.setVisibility(View.VISIBLE);

        topRight.setAnimation(top_right);
        topRight.setVisibility(View.VISIBLE);

        //Intent callIntent = new Intent(MainActivity.this, NfcActivity.class);
        //startActivity(callIntent);

        final Button startButton = (Button) findViewById(R.id.main_start);
        assert startButton != null;
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                            //MainActivity.this.finish();
                        }
                    });

                    dialog2.setCancelable(false);
                    dialog2.show();
                }

            }
        });
/*
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
                    callIntent = new Intent(MainActivity.this, LoginActivity
                            .class);
                    startActivity(callIntent);
                }
            }
        });*/
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    private boolean loadData(Context context) {
        // アプリ標準の Preferences を取得する
        SharedPreferences sp = this.getPreferences(Context.MODE_PRIVATE);

        allData = JSON.decode(sp.getString("DATA_JSON", "{}"), Data.class);

        boolean ans;
        ans = allData.getFamily() != null;

        return ans;
    }

    private boolean saveData(Context context) {
        // アプリ標準の Preferences を取得する
        SharedPreferences sp = this.getPreferences(Context.MODE_PRIVATE);
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

