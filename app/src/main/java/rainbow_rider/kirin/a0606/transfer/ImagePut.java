package rainbow_rider.kirin.a0606.transfer;

import android.os.AsyncTask;
import android.util.Log;

import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rainbow_rider.kirin.a0606.Data.Data;

/**
 * Created by acq on 16/06/08.
 * Title:
 * Author:
 * Memo:
 * Todo:
 */

public class ImagePut extends AsyncTask<byte[], byte[], byte[]> {
    public static final MediaType jsontype = MediaType.parse( "application/json; charset=utf-8" );
    public String urlPath = "/index.html";
    private final String urlHosts = "http://" + ServerInfomation
            .getServerHostName() + ":" + ServerInfomation
                                            .getServerPort();

    // 入力データ群
    // 全データ
    public Data allData;
    // 受信したデータ
    Data reply;
    public OkHttpClient client = new OkHttpClient();
    public String url;
    public byte[] bitmap;
    public ImagePut( byte[] bmp ) {
        bitmap = bmp;
    }



    public void setPath( String path ) {
        this.url = this.urlHosts + path;
    }

    public Data getReply( ) {
        return reply;
    }

    public boolean isSuccess( ) {return reply.isStatus();}


    private String Post( byte[] json ) throws IOException {
        Log.d( "TRANS/POST/START", "Post function start." );
        RequestBody body = RequestBody.create( jsontype, json );
        Request request = new Request.Builder()
                .url( url )
                .post( body )
                .build();
        Response response = client.newCall( request ).execute();
        if( response.code() != 200 ) {
            Log.e( "TRANS/POST/ERROR", "Responce code is " + response.code() + "!" );
            return "{\"status\":false}";
        }
        Log.d( "TRANS/POST/SUCCESS", "POST Successful! " + response.code() + "! " + response.body().contentLength() );
        return response.body().string();
    }

    @Override
    protected byte[] doInBackground( byte[]... params ) {
        try {
            Log.d( "TRANSFER/START", "Download start..." );
            Log.d( "TRANSFER/START/URL", url );
            Log.d( "TRANSFER/START/DATA", "IMAGE" );

            reply = JSON.decode( Post( bitmap ), Data.class );
        } catch ( IOException e ) {
            Log.e( "TRANSFER/ERROR/EXCEPT", "IOException Error" );
            e.printStackTrace();
        } catch ( JSONException e){
            Log.e( "TRANSFER/ERROR/EXCEPT", "JSONException Error" );
        }

        Log.d( "TRANSFER/FINISH", "TRANSFER FINISHED." );
        return null;
    }
}
