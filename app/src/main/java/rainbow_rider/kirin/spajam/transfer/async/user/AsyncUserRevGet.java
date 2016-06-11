package rainbow_rider.kirin.spajam.transfer.async.user;

import java.util.ArrayList;

import rainbow_rider.kirin.spajam.Data.Data;
import rainbow_rider.kirin.spajam.Data.Family;
import rainbow_rider.kirin.spajam.transfer.async.Sender;

/**
 * Created by acq on 16/06/07.
 * Title:
 * Author:
 * Memo:
 * Todo:
 */

public class AsyncUserRevGet extends Sender {
    public AsyncUserRevGet( Data data ) {
        allData = data;
        super.setPath( "/userdel" );
    }

    public AsyncUserRevGet( Family family ) {
        ArrayList<Family> familyArrayList = new ArrayList<>();
        familyArrayList.add( family );

        allData = new Data();
        allData.setFamily( familyArrayList );
        super.setPath( "/userdel" );
    }
}
