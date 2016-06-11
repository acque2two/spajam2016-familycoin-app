package rainbow_rider.kirin.spajam.transfer.sync.user;

import java.util.ArrayList;

import rainbow_rider.kirin.spajam.Data.Data;
import rainbow_rider.kirin.spajam.Data.Family;
import rainbow_rider.kirin.spajam.transfer.sync.Sender;

/**
 * Created by acq on 16/06/07.
 * Title:
 * Author:
 * Memo:
 * Todo:
 */

public class UserRevGet extends Sender {
    public UserRevGet( Data data ) {
        allData = data;
        super.setPath( "/userrevget" );
    }

    public UserRevGet( Family family ) {
        ArrayList<Family> familyArrayList = new ArrayList<>();
        familyArrayList.add( family );

        allData = new Data();
        allData.setFamily( familyArrayList );
        super.setPath( "/userrevget" );
    }
}
