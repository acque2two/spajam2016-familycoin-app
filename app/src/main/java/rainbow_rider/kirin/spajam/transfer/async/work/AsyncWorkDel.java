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
public class AsyncWorkDel extends Sender {

    public AsyncWorkDel( Data data ) {
        allData = data;
        super.setPath( "/workdel" );
    }

    public AsyncWorkDel( Family family ) {
        ArrayList<Family> familyArrayList = new ArrayList<>();
        familyArrayList.add( family );

        allData = new Data();
        allData.setFamily( familyArrayList );
        super.setPath( "/workdel" );
    }

}
