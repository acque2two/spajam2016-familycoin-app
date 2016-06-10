package rainbow_rider.kirin.spajam.transfer.sync.fav;

import rainbow_rider.kirin.spajam.Data.Data;
import rainbow_rider.kirin.spajam.Data.Multiple.Questions;
import rainbow_rider.kirin.spajam.Data.Multiple.Users;
import rainbow_rider.kirin.spajam.Data.Question;
import rainbow_rider.kirin.spajam.Data.User;
import rainbow_rider.kirin.spajam.transfer.sync.Sender;

/**
 * Created by acq on 16/06/07.
 * Title:
 * Author:
 * Memo:
 * Todo:
 */

public class FavoriteAdd extends Sender {
    public FavoriteAdd( User user, Question question ) {

        Users userList = new Users();
        userList.set( user );
        Questions questionList = new Questions();
        questionList.set( question );
        allData = new Data();
        allData.setUser( userList );
        allData.setQuestion( questionList );
        super.setPath( "/favorite" );
    }
}
