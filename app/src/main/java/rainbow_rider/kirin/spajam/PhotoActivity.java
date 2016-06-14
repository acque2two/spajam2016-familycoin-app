package rainbow_rider.kirin.spajam;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

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

        Bitmap bitmap1 = null;
        Bitmap bitmap2 = null;

        final String genre;
        final String image1;
        final String image2;

        AssetManager assetManager = getResources().getAssets();

        if(genreId == 1){
            genre = "cleaning/";
            image1 = genre + "bath.png";
            image2 = genre + "broom.png";
        }else if(genreId == 2){
            genre = "cuisine/";
            image1 = genre + "cook_beef.png";
            image2 = genre + "cook_knife.png";
        }else if(genreId == 3){
            genre = "washing/";
            image1 = genre + "clothes.png";
            image2 = genre + "washing_machine.png";
        }else if(genreId == 4){
            genre = "study/";
            image1 = genre + "study_pen.png";
            image2 = genre + "study_book.png";
        }else if(genreId == 5){
            genre = "shopping/";
            image1 = genre + "shopping_basket.png";
            image2 = genre + "shopping_shoes.png";
        }else if(genreId == 6){
            genre = "etc/";
            image1 = genre + "etc_tonkachi.png";
            image2 = genre + "etc_wrench.png";
        }else{
            //とりあえず。
            genre = "cleaning/";
            image1 = genre + "bath.png";
            image2 = "broom.png";
        }

        //http://pentan.info/android/app/sample/asset_manager.html  たぶんもっと簡単にできる
        try {
            InputStream inputStream1 = getResources().getAssets().open("images/" + image1);
            InputStream inputStream2 = getResources().getAssets().open("images/" + image2);
            bitmap1 = BitmapFactory.decodeStream(inputStream1);
            bitmap2 = BitmapFactory.decodeStream(inputStream2);
        } catch (IOException e) {
            Log.d("Assets","Error");
        }

        imageView1.setImageBitmap(bitmap1);
        imageView2.setImageBitmap(bitmap2);

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

    @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent();
            setResult(RESULT_CANCELED, intent);
            finish();
            return true;
        }
        return false;
    }
}
