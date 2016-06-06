package rainbow_rider.kirin.a0606.transfer;

import android.os.AsyncTask;

import rainbow_rider.kirin.a0606.Data.Data;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by acq on 16/05/30.
 * Title:
 * Author:
 * Memo:
 * Todo:
 */
public class Transfer extends AsyncTask<Data, Data, Data> {
    public static final MediaType jsontype = MediaType.parse( "application/json; charset=utf-8" );
    private OkHttpClient client = new OkHttpClient();

    private String Post( String url, String json ) throws IOException {
        RequestBody body = RequestBody.create( jsontype, json );
        Request request = new Request.Builder()
                .url( url )
                .post( body )
                .build();
        Response response = client.newCall( request ).execute();
        return response.body().string();
    }

    @Override
    protected Data doInBackground( Data... params ) {

        return null;
    }
}
