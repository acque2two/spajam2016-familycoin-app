package rainbow_rider.kirin.spajam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AnimeActivity extends AppCompatActivity {
    private boolean TEXT_VISIBILITY = true;
    private TextView mTextView;
    private Animation anim_start;
    private Animation anim_end;

    private ImageView bottom;
    private ImageView top;
    private ImageView right;
    private ImageView left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_anime);

        mTextView = (TextView) findViewById(R.id.text_view);
        Button button = (Button) findViewById(R.id.button);
        anim_start = AnimationUtils.loadAnimation(this, R.anim.right_to_left);
        anim_end = AnimationUtils.loadAnimation(this, R.anim.home2);
        bottom = (ImageView) findViewById(R.id.ani_bottom);
        top = (ImageView) findViewById(R.id.ani_top);
        right = (ImageView) findViewById(R.id.ani_right);
        left = (ImageView) findViewById(R.id.ani_left);

        final ArrayList<ImageView> images = new ArrayList<ImageView>(){{
            add(bottom);
            add(top);
            add(right);
            add(left);
        }};

        assert button != null;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TEXT_VISIBILITY) {
                    TEXT_VISIBILITY = false;
                    mTextView.startAnimation(anim_start);
                    mTextView.setVisibility(View.VISIBLE);
                    for(ImageView i : images){
                        i.startAnimation(anim_start);
                        i.setVisibility(View.VISIBLE);
                    }
                } else {
                    TEXT_VISIBILITY = true;
                    mTextView.startAnimation(anim_end);
                    mTextView.setVisibility(View.INVISIBLE);
                    for(ImageView i : images){
                        i.startAnimation(anim_end);
                        i.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
    }
}
