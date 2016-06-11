package rainbow_rider.kirin.spajam.transfer.sync.work;

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
public class WorkGet extends Sender {

    public WorkGet( Data data ) {
        allData = data;
        super.setPath( "/workget" );
    }

    public WorkGet( Family family ) {
        ArrayList<Family> familyArrayList = new ArrayList<>();
        familyArrayList.add( family );

        allData = new Data();
        allData.setFamily( familyArrayList );
        super.setPath( "/workget" );
    }


}
