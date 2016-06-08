package rainbow_rider.kirin.a0606.transfer.question;

import rainbow_rider.kirin.a0606.Data.Data;
import rainbow_rider.kirin.a0606.Data.Multiple.Anss;
import rainbow_rider.kirin.a0606.Data.Multiple.Questions;
import rainbow_rider.kirin.a0606.Data.Question;
import rainbow_rider.kirin.a0606.transfer.Sender;

/**
 * Created by acq on 16/06/07.
 * Title:
 * Author:
 * Memo:
 * Todo:
 */

public class QuestionDel extends Sender {
    public QuestionDel( Question question, Anss answer ) {
        Questions questionList = new Questions();
        questionList.set( question );
        allData = new Data();
        allData.setQuestion( questionList );
        allData.setAns( answer );
        super.setPath( "/questiondel" );
    }
}
