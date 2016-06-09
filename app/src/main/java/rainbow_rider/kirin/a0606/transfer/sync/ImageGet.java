package rainbow_rider.kirin.a0606.transfer.sync;

import android.util.Log;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by acq on 16/06/08.
 * Title:
 * Author:
 * Memo:
 * Todo:
 */
public class ImageGet extends Sender {

    String fname;
    ImageGet(String fhash) {
        super.setPath( "/image/get" );
        fname = fhash;
    }

    public Boolean isReply( ) {
        return reply;
    }

    Boolean reply;

    private boolean Post( String fhash ) throws IOException {
        Log.d( "TRANS/POST/START", "Post function start." );
        RequestBody body = RequestBody.create( jsontype, fhash );
        Request request = new Request.Builder()
                .url( url )
                .post( body )
                .build();
        Response response = client.newCall( request ).execute();
        if( response.code() != 200 ) {
            Log.e( "TRANS/POST/ERROR", "ResJSON.encode( allData )ponce code is " + response.code() + "!" );
            return false;
        }
        Log.d( "TRANS/POST/SUCCESS", "POST Successful! " + response.code() + "! " + response.body().contentLength() );
        return response.isSuccessful();
    }

}

