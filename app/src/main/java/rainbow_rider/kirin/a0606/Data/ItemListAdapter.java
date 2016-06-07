package rainbow_rider.kirin.a0606.Data;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import rainbow_rider.kirin.a0606.R;


public class ItemListAdapter extends GenericArrayAdapter<Question> {

    ViewHolder mViewHolder;

    public ItemListAdapter(Context context, int resource ) {
        super( context, resource );
    }

    public View getView(int position, View convertView, ViewGroup parent ) {

        Question listItem = getItem(position);

        if ( convertView == null ) {

            convertView = super.getView( position, convertView, parent );

            mViewHolder = new ViewHolder();

            mViewHolder.food_icon = (ImageView) convertView.findViewById( R.id.item_list_view_food_icon );

            mViewHolder.title = (TextView) convertView.findViewById( R.id.item_list_view_title );

            convertView.setTag( mViewHolder );

        } else {

            mViewHolder = ( ViewHolder ) convertView.getTag();
        }

        //mViewHolder.food_icon.setBackground();

        //mViewHolder.food_icon.setBackground( listitem.getFoodIcon() );
        mViewHolder.title.setText( listItem.getQ_name() );

        return convertView;
    }

    static class ViewHolder {
        ImageView food_icon;
        TextView title;
    }
}
