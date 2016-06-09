package rainbow_rider.kirin.a0606.transfer.async.fav;

import rainbow_rider.kirin.a0606.Data.Data;
import rainbow_rider.kirin.a0606.Data.Multiple.Questions;
import rainbow_rider.kirin.a0606.Data.Multiple.Users;
import rainbow_rider.kirin.a0606.Data.Question;
import rainbow_rider.kirin.a0606.Data.User;
import rainbow_rider.kirin.a0606.transfer.async.Sender;

/**
 * Created by acq on 16/06/07.
 * Title:
 * Author:
 * Memo:
 * Todo:
 */

public class FavoriteAdd extends Sender {
    public FavoriteAdd( Data data ){
        allData = data;
        super.setPath("/favorite");
    }
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
