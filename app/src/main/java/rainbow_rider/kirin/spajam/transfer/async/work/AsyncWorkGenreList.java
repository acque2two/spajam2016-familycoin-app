package rainbow_rider.kirin.spajam.transfer.async.work;

import java.util.ArrayList;

import rainbow_rider.kirin.spajam.Data.Data;
import rainbow_rider.kirin.spajam.Data.Family;
import rainbow_rider.kirin.spajam.Data.Genre;
import rainbow_rider.kirin.spajam.Data.Work;
import rainbow_rider.kirin.spajam.transfer.async.Sender;

/**
 * Created by acq on 16/06/11.
 * Title:
 * Author:
 * Memo:
 * Todo:
 */
public class AsyncWorkGenreList extends Sender {

    public AsyncWorkGenreList( Family family ) {
        ArrayList<Family> familyList = new ArrayList<>();
        familyList.add( family );
        allData = new Data();
        allData.setFamily( familyList );
        super.setPath( "/workgenrelist" );
    }

    public AsyncWorkGenreList( Data data ) {
        allData = data;
        super.setPath( "/workgenrelist" );
    }

    public AsyncWorkGenreList( String g_name ) {
        Genre genre = new Genre();
        genre.setG_name( g_name );
        Work work = new Work();
        work.setGenre( genre );
        genre.setG_name( g_name );
        ArrayList<Work> workList = new ArrayList<>();
        workList.add( work );
        Family family = new Family();
        family.setWork( workList );
        ArrayList<Family> familyList = new ArrayList<>();
        familyList.add( family );
        allData = new Data();
        allData.setFamily( familyList );
        super.setPath( "/workgenrelist" );
    }
}
