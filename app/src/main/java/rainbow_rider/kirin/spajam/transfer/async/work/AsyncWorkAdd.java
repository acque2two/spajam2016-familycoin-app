package rainbow_rider.kirin.spajam.transfer.async.work;

import java.util.ArrayList;

import rainbow_rider.kirin.spajam.Data.Data;
import rainbow_rider.kirin.spajam.Data.Family;
import rainbow_rider.kirin.spajam.transfer.async.Sender;

/**
 * Created by acq on 16/06/11.
 * Title:
 * Author:
 * Memo:
 * Todo:
 */
public class AsyncWorkAdd extends Sender {

    public AsyncWorkAdd( Data data ) {
        allData = data;
        super.setPath( "/workadd" );
    }

    public AsyncWorkAdd( Family family ) {
        ArrayList<Family> familyArrayList = new ArrayList<>();
        familyArrayList.add( family );

        allData = new Data();
        allData.setFamily( familyArrayList );
        super.setPath( "/workadd" );
    }

}
