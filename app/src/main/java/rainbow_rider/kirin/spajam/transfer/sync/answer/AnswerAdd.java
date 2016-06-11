package rainbow_rider.kirin.spajam.transfer.sync.answer;

import rainbow_rider.kirin.spajam.Data.Data;
import rainbow_rider.kirin.spajam.Data.Multiple.Anss;
import rainbow_rider.kirin.spajam.transfer.sync.Sender;

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
