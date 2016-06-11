package rainbow_rider.kirin.spajam.transfer.sync.product;

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
public class AsyncProductList extends Sender {
    public AsyncProductList( Family family ) {
        ArrayList<Family> familyArrayList = new ArrayList<>();
        familyArrayList.add( family );

        allData = new Data();
        allData.setFamily( familyArrayList );
        super.setPath( "/productlist" );
    }
}
