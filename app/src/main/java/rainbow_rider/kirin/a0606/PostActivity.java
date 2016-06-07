package rainbow_rider.kirin.a0606;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileDescriptor;
import java.io.IOException;

public class PostActivity extends AppCompatActivity {
    private static final int RESULT_PICK_IMAGEFILE = 1001;
    private ImageView imageView;
    private Button image_button;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature( Window.FEATURE_ACTION_BAR );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        imageView = (ImageView) findViewById(R.id.imageView);
        image_button = (Button) findViewById(R.id.post_image_button);
        Spinner genre_spinner = (Spinner) findViewById(R.id.post_genre_spinner);
        Spinner answer_spinner = (Spinner) findViewById(R.id.post_selectAnswer_spinner);
        EditText title = (EditText) findViewById(R.id.post_title);
        EditText answerA = (EditText) findViewById(R.id.post_answerA_editText);
        EditText answerB = (EditText) findViewById(R.id.post_answerB_editText);
        EditText answerC = (EditText) findViewById(R.id.post_answerC_editText);
        EditText answerD = (EditText) findViewById(R.id.post_answerD_editText);


        assert image_button != null;
        image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's file browser.
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

                // Filter to only show results that can be "opened", such as a
                // file (as opposed to a list of contacts or timezones)
                intent.addCategory(Intent.CATEGORY_OPENABLE);

                // Filter to show only images, using the image MIME data type.
                // it would be "*/*".
                intent.setType("image/*");

                startActivityForResult(intent, RESULT_PICK_IMAGEFILE);
            }
        });

        //Genre spinner
        ArrayAdapter<String> genre_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        genre_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        genre_adapter.add("選択してください");
        genre_adapter.add("国語");
        genre_adapter.add("数学");
        genre_adapter.add("理科");
        genre_adapter.add("社会");
        genre_adapter.add("英語");
        genre_adapter.add("その他");

        // アダプターを設定します
        assert genre_spinner != null;
        genre_spinner.setAdapter(genre_adapter);
        // スピナーのアイテムが選択された時に呼び出されるコールバックリスナーを登録します
        genre_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(
                    AdapterView<?> parent, View view,
                    int position, long id
            ){
                Spinner spinner = (Spinner) parent;
                // 選択されたアイテムを取得します
                spinner.getSelectedItemId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        //Answer spinner
        ArrayAdapter<String> answer_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        answer_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        answer_adapter.add("選択してください");
        answer_adapter.add("A");
        answer_adapter.add("B");
        answer_adapter.add("C");
        answer_adapter.add("D");

        // アダプターを設定します
        assert answer_spinner != null;
        answer_spinner.setAdapter(answer_adapter);
        // スピナーのアイテムが選択された時に呼び出されるコールバックリスナーを登録します
        answer_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(
                    AdapterView<?> parent, View view,
                    int position, long id
            ){
                Spinner spinner = (Spinner) parent;
                // 選択されたアイテムを取得します
                spinner.getSelectedItemId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    private String getGalleryPath() {
        return Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DCIM + "/";
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {

        // The ACTION_OPEN_DOCUMENT intent was sent with the request code
        // READ_REQUEST_CODE. If the request code seen here doesn't match, it's the
        // response to some other intent, and the code below shouldn't run at all.
        if (requestCode == RESULT_PICK_IMAGEFILE && resultCode == Activity.RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.
            // Pull that URI using resultData.getData().
            uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                Log.i("", "Uri: " + uri.toString());

                try {
                    Bitmap bmp = getBitmapFromUri(uri);
                    imageView.setImageBitmap(bmp);
                    image_button.setText("");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Bitmap
    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        assert parcelFileDescriptor != null;
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

    //アクションバーの設定
    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        //menu.add( 1, 0, Menu.NONE, "設定" );

        //menu.findItem(R.id.menu_move_to_add_friend_button);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.menu_post, menu );
        setTitle("問題投稿");
        MenuItem a = menu.findItem( R.id.menu_detail_image_button );
        a.setTitle( "投稿" );;
        a.setCheckable( true );
        a.setOnMenuItemClickListener( new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick( MenuItem item ) {
                //    Intent callIntent = new Intent( DetailActivity.this, AddFriendActivity.class );
                //    startActivity( callIntent );
                Toast.makeText(PostActivity.this, "oni", Toast.LENGTH_SHORT).show();
                return false;
            }
        } );

        return super.onCreateOptionsMenu( menu );

    }

    //ハンバーガー押されたときの動作
    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {

        if ( item.getItemId() == 0 ) {
            //    Intent callIntent = new Intent( TopActivity.this, SettingMainActivity.class );
            //    startActivity( callIntent );
        } else if ( item.getItemId() == 1 ) {
            Toast.makeText( this, "Menu2", Toast.LENGTH_SHORT ).show();
        }

        return true;
    }
}
