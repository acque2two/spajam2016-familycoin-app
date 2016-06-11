package rainbow_rider.kirin.spajam.transfer.async.achievement.achievement;

import java.util.ArrayList;

import rainbow_rider.kirin.spajam.Data.Data;
import rainbow_rider.kirin.spajam.Data.Family;
import rainbow_rider.kirin.spajam.transfer.async.Sender;

/**
 * Created by acq on 16/06/12.
 * Title:
 * Author:
 * Memo:
 * Todo:
 */
public class AsyncAchievementGenreList extends Sender {

    public AsyncAchievementGenreList( Data data ) {
        allData = data;
        super.setPath( "/achievementgenrelist" );
    }

    public AsyncAchievementGenreList( Family family ) {
        ArrayList<Family> familyArrayList = new ArrayList<>();
        familyArrayList.add( family );
        allData = new Data();
        allData.setFamily( familyArrayList );
        super.setPath( "/achievenmentgenrelist" );
    }
}
