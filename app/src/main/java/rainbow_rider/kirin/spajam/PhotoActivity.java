package rainbow_rider.kirin.spajam;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        final ImageView imageView1 = (ImageView) findViewById(R.id.photo_imageView1);
        final ImageView imageView2 = (ImageView) findViewById(R.id.photo_imageView2);

        Intent intent = new Intent();
        int genreId = intent.getIntExtra("genre", -1);

        Toast.makeText(PhotoActivity.this, Integer.toString(genreId), Toast.LENGTH_SHORT).show();

        assert imageView1 != null;
        assert imageView2 != null;

        if(genreId == 1){
            imageView1.setImageResource(R.mipmap.bath);
            imageView2.setImageResource(R.mipmap.broom);
        }else if(genreId == 2){
            imageView1.setImageResource(R.mipmap.cook_beaf);
            imageView2.setImageResource(R.mipmap.cook_knife);
        }else if(genreId == 3){
            imageView1.setImageResource(R.mipmap.washing_machine);
            imageView2.setImageResource(R.mipmap.clothes);
        }else if(genreId == 4){
            imageView1.setImageResource(R.mipmap.study_pen);
            imageView2.setImageResource(R.mipmap.study_book);
        }else if(genreId == 5){
            imageView1.setImageResource(R.mipmap.shopping_basket);
            imageView2.setImageResource(R.mipmap.shopping_shoes);
        }else if(genreId == 6){
            imageView1.setImageResource(R.mipmap.etc_tonkachi);
            imageView2.setImageResource(R.mipmap.etc_wrench);
        }else{

        }

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // imageView1.getBackground().toString();
                int a = R.mipmap.bath;
                Intent intent = new Intent();
                intent.putExtra("image", a);

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
