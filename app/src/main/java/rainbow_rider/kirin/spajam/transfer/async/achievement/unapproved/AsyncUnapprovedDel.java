package rainbow_rider.kirin.spajam.transfer.async.achievement.unapproved;

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
public class AsyncUnapprovedDel extends Sender {

    public AsyncUnapprovedDel( Data data ) {
        allData = data;
        super.setPath( "/unapproveddel" );
    }

    public AsyncUnapprovedDel( Family family ) {

        ArrayList<Family> familyArrayList = new ArrayList<>();
        familyArrayList.add( family );
        allData = new Data();
        allData.setFamily( familyArrayList );
        super.setPath( "/unapproveddel" );
    }

}
