package rainbow_rider.kirin.spajam.transfer.async.user;

import java.util.ArrayList;

import rainbow_rider.kirin.spajam.Data.Data;
import rainbow_rider.kirin.spajam.Data.Family;
import rainbow_rider.kirin.spajam.Data.User;
import rainbow_rider.kirin.spajam.transfer.async.Sender;

/**
 * Created by acq on 16/06/12.
 * Title:
 * Author:
 * Memo:
 * Todo:
 */
public class AsyncScoreChange extends Sender {

    public AsyncScoreChange( Data data ) {
        allData = data;
        super.setPath( "/scorechange" );
    }

    public AsyncScoreChange( Family family ) {
        ArrayList<Family> familyArrayList = new ArrayList<>();
        familyArrayList.add( family );

        allData = new Data();
        allData.setFamily( familyArrayList );
        super.setPath( "/scorechange" );
    }

    public AsyncScoreChange( User user ) {
        ArrayList<User> userArrayList = new ArrayList<>();
        userArrayList.add( user );

        Family family = new Family();
        family.setUsers( userArrayList );
        ArrayList<Family> familyArrayList = new ArrayList<>();
        familyArrayList.add( family );
        allData = new Data();
        allData.setFamily( familyArrayList );
        super.setPath( "/scorechange" );
    }
}
