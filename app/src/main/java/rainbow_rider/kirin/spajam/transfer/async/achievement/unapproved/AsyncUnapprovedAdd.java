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
public class AsyncUnapprovedAdd extends Sender {

    public AsyncUnapprovedAdd( Data data ) {
        allData = data;
        super.setPath( "/unapprovedadd" );
    }

    public AsyncUnapprovedAdd( Family family ) {

        ArrayList<Family> familyArrayList = new ArrayList<>();
        familyArrayList.add( family );
        allData = new Data();
        allData.setFamily( familyArrayList );
        super.setPath( "/unapprovedadd" );
    }

}
