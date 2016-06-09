package rainbow_rider.kirin.a0606.transfer.async.fav;

import rainbow_rider.kirin.a0606.Data.Data;
import rainbow_rider.kirin.a0606.Data.Multiple.Questions;
import rainbow_rider.kirin.a0606.Data.Question;
import rainbow_rider.kirin.a0606.transfer.async.Sender;

/**
 * Created by acq on 16/06/07.
 * Title:
 * Author:
 * Memo:
 * Todo:
 */

public class FavoriteUserList extends Sender {
    public FavoriteUserList( Question question ) {
        Questions questionList = new Questions();
        questionList.set( question );
        allData = new Data();
        allData.setQuestion( questionList );
        super.setPath( "/favoriteuserlist" );
    }
}
