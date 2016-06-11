package rainbow_rider.kirin.spajam.transfer.sync.question;

import rainbow_rider.kirin.spajam.Data.Data;
import rainbow_rider.kirin.spajam.Data.Genre;
import rainbow_rider.kirin.spajam.Data.Multiple.Genres;
import rainbow_rider.kirin.spajam.transfer.sync.Sender;

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
