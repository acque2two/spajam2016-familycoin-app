package rainbow_rider.kirin.spajam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class PhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        ImageView imageView1 = (ImageView) findViewById(R.id.photo_imageView1);
        ImageView imageView2 = (ImageView) findViewById(R.id.photo_imageView2);

        Intent intent = getIntent();
        int genreId = intent.getIntExtra("genre", -1);

        assert imageView1 != null;
        assert imageView2 != null;

        final int image1;
        final int image2;

        if(genreId == 1){
            image1 = R.mipmap.bath;
            image2 = R.mipmap.broom;

        }else if(genreId == 2){
            image1 = R.mipmap.cook_beaf;
            image2 = R.mipmap.cook_knife;
        }else if(genreId == 3){
            image1 = R.mipmap.washing_machine;
            image2 = R.mipmap.clothes;
        }else if(genreId == 4){
            image1 = R.mipmap.study_pen;
            image2 = R.mipmap.study_book;
        }else if(genreId == 5){
            image1 = R.mipmap.shopping_basket;
            image2 = R.mipmap.shopping_shoes;
        }else if(genreId == 6){
            image1 = R.mipmap.etc_tonkachi;
            image2  =R.mipmap.etc_wrench;
        }else{
            image1 = R.drawable.ic_menu_camera;
            image2 = R.drawable.ic_menu_camera;
        }

        imageView1.setImageResource(image1);
        imageView2.setImageResource(image2);

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // imageView1.getBackground().toString();
                Intent intent = new Intent();
                intent.putExtra("image", image1);

                setResult(RESULT_OK, intent);
                finish();
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // imageView1.getBackground().toString();
                Intent intent = new Intent();
                intent.putExtra("image", image2);

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
