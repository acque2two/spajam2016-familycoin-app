package rainbow_rider.kirin.a0606.transfer.genre;

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

public class GenreGet extends Sender {
    public GenreGet( Genre genre ) {
        Genres genreList = new Genres();
        genreList.set( genre );
        allData = new Data();
        allData.setGenre( genreList );
        super.setPath( "/genreget" );
    }
}
