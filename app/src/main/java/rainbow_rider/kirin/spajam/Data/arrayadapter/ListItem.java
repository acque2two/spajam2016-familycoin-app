package rainbow_rider.kirin.spajam.Data.arrayadapter;

import android.graphics.Bitmap;

public class ListItem {

    private Bitmap mIcon;
    private String mTitle;
    private Integer mPoint;

    public void setmPoint(Integer mPoint) {
        this.mPoint = mPoint;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }


    public void setmIcon(Bitmap icon){
        this.mIcon = icon;
    }

    public Bitmap getmIcon() {
        return mIcon;
    }

    public String getmTitle() {
        return mTitle;
    }

    public Integer getmPoint() {
        return mPoint;
    }


}
