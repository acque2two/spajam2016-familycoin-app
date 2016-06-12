package rainbow_rider.kirin.spajam;

import android.content.Context;
import android.content.Intent;
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
import rainbow_rider.kirin.spajam.Data.Product;
import rainbow_rider.kirin.spajam.Data.User;
import rainbow_rider.kirin.spajam.Data.Work;
import rainbow_rider.kirin.spajam.transfer.async.user.AsyncScoreChange;

public class PDActivity extends AppCompatActivity {

    private Data allData;
    private String my_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pd);

        loadData(PDActivity.this);

        Intent intent = getIntent();
        int p_id = intent.getIntExtra("p_id", -1);

        final Button send_button = (Button) findViewById(R.id.pd_get_button);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        TextView title_text = (TextView) findViewById(R.id.pd_title_text);

        assert title_text != null;

        Product product = new Product();
        User user = new User();

        for(Product p : allData.getFamily().get(0).getProduct()){
            if(p.getP_id() == p_id){
                product = p;
            }
        }

        title_text.setText(product.getP_name());

        //send button
        assert send_button != null;
        final Product finalProduct = product;
        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < allData.getFamily().get(0).getUser().size(); i++){
                    if(my_id.equals(allData.getFamily().get(0).getUser().get(i))){
                        allData.getFamily().get(0).getUser().get(i).setScore(
                                allData.getFamily().get(0).getUser().get(i).getScore() - finalProduct.getP_point());
                    }
                }
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
        my_id = sp.getString("my_id", "");
        boolean ans;
        ans = allData.getFamily() != null;

        return ans;
    }
}