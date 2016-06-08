package rainbow_rider.kirin.a0606.transfer.answer;

import rainbow_rider.kirin.a0606.Data.Data;
import rainbow_rider.kirin.a0606.Data.Multiple.Anss;
import rainbow_rider.kirin.a0606.transfer.Sender;

/**
 * Created by acq on 16/06/07.
 * Title:
 * Author:
 * Memo:
 * Todo:
 */
public class AnswerAdd extends Sender {
    public AnswerAdd( ) {
        super.setPath( "/ansadd" );
    }

    public AnswerAdd( Anss ans ) {
        allData = new Data();
        allData.setAns( ans );
        super.setPath( "/ansadd" );
    }

}
