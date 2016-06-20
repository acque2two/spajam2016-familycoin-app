package rainbow_rider.kirin.spajam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import rainbow_rider.kirin.spajam.Data.Data;
import rainbow_rider.kirin.spajam.Data.F;
import rainbow_rider.kirin.spajam.Data.Product;
import rainbow_rider.kirin.spajam.Data.User;
import rainbow_rider.kirin.spajam.transfer.async.user.AsyncScoreChange;

public class PDActivity extends AppCompatActivity {

    private Data allData;
    private String my_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pd);

        allData = F.Load();

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
                for(int i = 0; i < allData.getFamily().get(0).getUsers().size(); i++){
                    if(my_id.equals(allData.getFamily().get(0).getUsers().get(i))){
                        allData.getFamily().get(0).getUsers().get(i).setScore(
                                allData.getFamily().get(0).getUsers().get(i).getScore() - finalProduct.getP_point());
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

}
