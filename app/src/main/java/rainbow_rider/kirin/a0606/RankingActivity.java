package rainbow_rider.kirin.a0606;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import rainbow_rider.kirin.a0606.Data.Data;
import rainbow_rider.kirin.a0606.transfer.question.QuestionUserRankList;

public class RankingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        final ArrayList<HashMap<String, String>> list_data = new ArrayList<HashMap<String, String>>();
        final HashMap<String, String> hashTmp = new HashMap<String, String>();

        new QuestionUserRankList(){
            @Override
            protected void onPostExecute(Data data) {
                super.onPostExecute(data);
                Data replay = getReply();
                for (int i = 0; i < 10; i++) {
                    String a = replay.getUser().get(i).getUser_name();
                    Long b = replay.getUser().get(i).getCount();
                    Long c = replay.getUser().get(i).getUser_id();
                    hashTmp.put("main", a);
                    hashTmp.put("sub", b.toString());
                    hashTmp.put("right", c.toString());
                    list_data.add(new HashMap<String, String>(hashTmp));

                    hashTmp.clear();
                }

                SimpleAdapter simp = new SimpleAdapter(getApplicationContext(), list_data, R.layout.two_line_list_item,
                        new String[]{"right", "main", "sub"}, new int[]{R.id.item_right, R.id.item_main, R.id.item_sub});

                ((ListView)findViewById(R.id.listView)).setAdapter(simp);

            }
        }.execute();


    }

}


