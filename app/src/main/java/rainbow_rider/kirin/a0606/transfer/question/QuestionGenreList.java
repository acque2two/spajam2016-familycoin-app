package rainbow_rider.kirin.a0606.transfer.question;

import rainbow_rider.kirin.a0606.Data.Data;
import rainbow_rider.kirin.a0606.Data.Genre;
import rainbow_rider.kirin.a0606.Data.Multiple.Genres;
import rainbow_rider.kirin.a0606.transfer.Sender;

/**
 * Created by acq on 16/06/07.
 * Title:
 * Author:
 * Memo:
 * Todo:
 */
public class QuestionGenreList extends Sender {
    public QuestionGenreList( ) {
        super.setPath( "/questiongenre" );
    }


    public QuestionGenreList( Genre genre ) {
        Genres genreList = new Genres();
        genreList.set( genre );
        allData = new Data();
        allData.setGenre( genreList );
        super.setPath( "/questiongenre" );
    }
}
