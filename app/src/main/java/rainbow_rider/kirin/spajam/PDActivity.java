package rainbow_rider.kirin.spajam;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.arnx.jsonic.JSON;

import rainbow_rider.kirin.spajam.Data.Data;
import rainbow_rider.kirin.spajam.transfer.async.user.AsyncScoreChange;

public class PDActivity extends AppCompatActivity {

    private Data allData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pd);

        final Button send_button = (Button) findViewById(R.id.pd_get_button);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        TextView title_text = (TextView) findViewById(R.id.pd_title_text);
        TextView user_text = (TextView) findViewById(R.id.pd_user_text);

        assert title_text != null;
        assert user_text != null;

        //send button
        assert send_button != null;
        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncScoreChange(allData){
                    @Override
                    protected void onPostExecute(Data data) {
                        super.onPostExecute(data);
                        finish();
                    }
                };
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
}
