package rainbow_rider.kirin.spajam.transfer.sync.question;

import rainbow_rider.kirin.spajam.Data.Data;
import rainbow_rider.kirin.spajam.Data.Multiple.Anss;
import rainbow_rider.kirin.spajam.Data.Multiple.Questions;
import rainbow_rider.kirin.spajam.Data.Question;
import rainbow_rider.kirin.spajam.transfer.sync.Sender;

/**
 * Created by acq on 16/06/07.
 * Title:
 * Author:
 * Memo:
 * Todo:
 */

public class QuestionAdd extends Sender {
    public QuestionAdd( ) {
        super.setPath( "/questionadd" );
    }

    public QuestionAdd( Question question, Anss answer ) {
        Questions questionList = new Questions();
        questionList.set( question );
        allData = new Data();
        allData.setQuestion( questionList );
        allData.setAns( answer );
        super.setPath( "/questionadd" );
    }


}