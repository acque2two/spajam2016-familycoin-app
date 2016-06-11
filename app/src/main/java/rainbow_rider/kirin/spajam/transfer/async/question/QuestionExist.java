package rainbow_rider.kirin.spajam.transfer.async.question;

import rainbow_rider.kirin.spajam.Data.Data;
import rainbow_rider.kirin.spajam.Data.Multiple.Questions;
import rainbow_rider.kirin.spajam.Data.Question;
import rainbow_rider.kirin.spajam.transfer.async.Sender;

/**
 * Created by acq on 16/06/07.
 * Title:
 * Author:
 * Memo:
 * Todo:
 */

public class QuestionExist extends Sender {
    public QuestionExist( Question question ) {
        Questions questionList = new Questions();
        questionList.set( question );
        allData = new Data();
        allData.setQuestion( questionList );
        super.setPath( "/questionexist" );
    }
}
