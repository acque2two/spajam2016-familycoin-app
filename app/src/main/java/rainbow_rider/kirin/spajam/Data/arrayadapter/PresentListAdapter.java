package rainbow_rider.kirin.spajam.Data.arrayadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import rainbow_rider.kirin.spajam.Data.Work;
import rainbow_rider.kirin.spajam.R;

public class PresentListAdapter extends GenericArrayAdapter<Work> {

    ViewHolder mViewHolder;

    public PresentListAdapter(Context context, int resource ) {
        super( context, resource );
    }

    public View getView( int position, View convertView, ViewGroup parent ) {

        Work listItem = getItem( position );

        if ( convertView == null ) {

            convertView = super.getView( position, convertView, parent );

            mViewHolder = new ViewHolder();

            mViewHolder.icon = (ImageView) convertView.findViewById( R.id.item_list_view_icon );

            mViewHolder.title = (TextView) convertView.findViewById( R.id.item_list_view_title );

            mViewHolder.illust = ( ImageView ) convertView.findViewById( R.id.item_list_view_illust );

            convertView.setTag( mViewHolder );

        } else {
            mViewHolder = ( ViewHolder ) convertView.getTag();
        }

        //mViewHolder.food_icon.setBackground();

        //mViewHolder.food_icon.setBackground( listitem.getFoodIcon() );
        //Log.d("q",listItem.getQ_name());

        mViewHolder.title.setText( listItem.getW_name() );
        //mViewHolder.icon.setImageResource( listItem.getImage() );

        return convertView;
    }

    static class ViewHolder {
        ImageView icon;
        ImageView illust;
        TextView title;
    }
}
