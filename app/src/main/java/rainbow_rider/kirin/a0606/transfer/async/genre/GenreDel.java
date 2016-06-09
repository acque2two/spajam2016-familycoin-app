package rainbow_rider.kirin.a0606.transfer.async.genre;

import rainbow_rider.kirin.a0606.Data.Data;
import rainbow_rider.kirin.a0606.Data.Genre;
import rainbow_rider.kirin.a0606.Data.Multiple.Genres;
import rainbow_rider.kirin.a0606.transfer.async.Sender;

/**
 * Created by acq on 16/06/07.
 * Title:
 * Author:
 * Memo:
 * Todo:
 */

public class GenreDel extends Sender {
    public GenreDel( Genre genre ) {
        Genres genreList = new Genres();
        genreList.set( genre );
        allData = new Data();
        allData.setGenre( genreList );
        super.setPath( "/genredel" );
    }
}
