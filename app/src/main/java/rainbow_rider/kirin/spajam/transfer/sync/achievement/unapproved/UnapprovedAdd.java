package rainbow_rider.kirin.spajam.transfer.sync.achievement.unapproved;

import java.util.ArrayList;

import rainbow_rider.kirin.spajam.Data.Data;
import rainbow_rider.kirin.spajam.Data.Family;
import rainbow_rider.kirin.spajam.transfer.sync.Sender;

/**
 * Created by acq on 16/06/11.
 * Title:
 * Author:
 * Memo:
 * Todo:
 */
public class UnapprovedAdd extends Sender {


    public UnapprovedAdd( Data data ) {
        allData = data;
        super.setPath( "/unapprovedadd" );
    }

    public UnapprovedAdd( Family family ) {

        ArrayList<Family> familyArrayList = new ArrayList<>();
        familyArrayList.add( family );
        allData = new Data();
        allData.setFamily( familyArrayList );
        super.setPath( "/unapprovedadd" );
    }



}
