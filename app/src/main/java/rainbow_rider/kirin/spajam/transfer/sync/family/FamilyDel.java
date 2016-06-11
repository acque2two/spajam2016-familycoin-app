package rainbow_rider.kirin.spajam.transfer.sync.family;

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
public class FamilyDel extends Sender {
    public FamilyDel( Family family ) {
        ArrayList<Family> familyList = new ArrayList<>();
        familyList.add( family );
        allData = new Data();
        allData.setFamily( familyList );
        super.setPath( "/familydel" );
    }
}
