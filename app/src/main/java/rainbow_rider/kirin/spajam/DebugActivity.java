package rainbow_rider.kirin.spajam;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Random;

public class DebugActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);

        Button main = (Button) findViewById(R.id.debug_main);
        Button top = (Button) findViewById(R.id.debug_top);
        Button detail = (Button) findViewById(R.id.debug_detail);
        Button post = (Button) findViewById(R.id.debug_post);
        Button photo = (Button) findViewById(R.id.debug_photo);
        Button d1 = (Button) findViewById(R.id.debug_1);
        Button d3 = (Button) findViewById(R.id.debug_3);

        Button login = (Button) findViewById(R.id.debug_login);
        Button join = (Button) findViewById(R.id.debug_join);
        Button nfc = (Button) findViewById(R.id.debug_nfc);
        Button present = (Button) findViewById(R.id.debug_present);
        Button pd = (Button) findViewById(R.id.debug_pd);
        Button d2 = (Button) findViewById(R.id.debug_2);
        Button d4 = (Button) findViewById(R.id.debug_4);

        ArrayList<Button> buttons = new ArrayList<Button>();
        buttons.add(main);
        buttons.add(top);
        buttons.add(detail);
        buttons.add(post);
        buttons.add(photo);
        buttons.add(d1);
        buttons.add(d3);

        buttons.add(login);
        buttons.add(join);
        buttons.add(nfc);
        buttons.add(present);
        buttons.add(pd);
        buttons.add(d2);
        buttons.add(d4);

        ArrayList<Integer> colors = colorList();

        for(int i = 0; i < buttons.size(); i ++){
            int j = new java.util.Random().nextInt(colors.size());
            buttons.get(i).setBackgroundColor(colors.get(j));
        }


        RelativeLayout layout = (RelativeLayout) findViewById(R.id.debug_layout);
        layout.setBackgroundColor(colors.get(new java.util.Random().nextInt(colors.size())));

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(DebugActivity.this, MainActivity.class);
                startActivity(callIntent);
            }
        });

        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(DebugActivity.this, TopActivity.class);
                startActivity(callIntent);
            }
        });
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(DebugActivity.this, DetailActivity.class);
                startActivity(callIntent);
            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(DebugActivity.this, PostActivity.class);
                startActivity(callIntent);
            }
        });
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(DebugActivity.this, PhotoActivity.class);
                startActivity(callIntent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(DebugActivity.this, LoginActivity.class);
                startActivity(callIntent);
            }
        });

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(DebugActivity.this, JoinActivity.class);
                startActivity(callIntent);
            }
        });

        nfc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(DebugActivity.this, NfcActivity.class);
                startActivity(callIntent);
            }
        });

        present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(DebugActivity.this, PresentActivity.class);
                startActivity(callIntent);
            }
        });
        pd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(DebugActivity.this, PDActivity.class);
                startActivity(callIntent);
            }
        });
    }

    public ArrayList<Integer> colorList(){
        ArrayList<Integer> colors = new ArrayList<Integer>(){{
            add(Color.rgb(255, 205, 210)); //Red100
            add(Color.rgb(229, 115, 115)); //Red300
            add(Color.rgb(244, 67, 54)); //Red500

            add(Color.rgb(248,187,208));//Pink
            add(Color.rgb(240,98,146));
            add(Color.rgb(233,30,99));

            add(Color.rgb(225, 190, 231)); //Purple
            add(Color.rgb(186, 104, 200));
            add(Color.rgb(156,39,176));

            add(Color.rgb(209,196,233));//DeepPurple
            add(Color.rgb(186,104,200));
            add(Color.rgb(156,39,176));

            add(Color.rgb(197,202,233));//Indigo
            add(Color.rgb(121,134,203));
            add(Color.rgb(63,81,181));

            add(Color.rgb(187,222,251));//blue
            add(Color.rgb(100,181,246));
            add(Color.rgb(33,150,243));

            add(Color.rgb(179,229,252));//LightBlue
            add(Color.rgb(79,195,247));
            add(Color.rgb(3,169,244));

            add(Color.rgb(178,235,242));//Cyan
            add(Color.rgb(77,208,225));
            add(Color.rgb(0,188,212));

            add(Color.rgb(178,223,219));//Teal
            add(Color.rgb(77,182,172));
            add(Color.rgb(0,150,136));

            add(Color.rgb(200,230,201));//Green
            add(Color.rgb(129,199,132));
            add(Color.rgb(76,175,80));

            add(Color.rgb(220,237,200));//LightGreen
            add(Color.rgb(174,213,129));
            add(Color.rgb(139,195,74));

            add(Color.rgb(240,244,195));//Lime
            add(Color.rgb(220,231,117));
            add(Color.rgb(205,220,57));

            add(Color.rgb(255,249,196));//Yellow
            add(Color.rgb(255,241,118));
            add(Color.rgb(255,235,59));

            add(Color.rgb(255,236,179));//Amber
            add(Color.rgb(255,213,79));
            add(Color.rgb(255,193,7));

            add(Color.rgb(255,224,178));//Orange
            add(Color.rgb(255,183,77));
            add(Color.rgb(255,152,0));

            add(Color.rgb(255,204,188));//DeepOrange
            add(Color.rgb(255,138,101));
            add(Color.rgb(255,87,34));

            add(Color.rgb(215,204,200));//Brown
            add(Color.rgb(161,136,127));
            add(Color.rgb(121,85,72));

            add(Color.rgb(245,245,245));//Gray
            add(Color.rgb(224,224,224));
            add(Color.rgb(158,158,158));

            add(Color.rgb(207,216,220));//BlueGray
            add(Color.rgb(144,164,174));
            add(Color.rgb(96,125,139));
        }};

        return colors;
    }
}
