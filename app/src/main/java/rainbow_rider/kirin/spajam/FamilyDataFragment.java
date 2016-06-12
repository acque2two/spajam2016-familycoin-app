package rainbow_rider.kirin.spajam;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import net.arnx.jsonic.JSON;

import java.util.ArrayList;
import java.util.HashMap;

import rainbow_rider.kirin.spajam.Data.Data;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FamilyDataFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FamilyDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FamilyDataFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mGenreId;
    private String mFId;
    private Data allData = new Data();

    private OnFragmentInteractionListener mListener;

    public FamilyDataFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FamilyDataFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FamilyDataFragment newInstance(String param1, String param2) {
        FamilyDataFragment fragment = new FamilyDataFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mGenreId = getArguments().getString(ARG_PARAM1);
            mFId = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_family_data, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
/*            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");*/
        }
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toast.makeText(view.getContext(), "かぞくのじょうほう", Toast.LENGTH_SHORT).show();

        loadData(view.getContext());
        ArrayList<HashMap<String, String>> list_data = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> hashTmp = new HashMap<String, String>();

        Log.d("  allData  ", allData.getFamily().get(0).getUser().get(0).getU_name());

        for ( int i = 0; i < allData.getFamily().get(0).getUser().size(); i++ ){
            hashTmp.put("u_name" , allData.getFamily().get(0).getUser().get(i).getU_name());
            hashTmp.put("u_data" , allData.getFamily().get(0).getUser().get(i).getScore().toString());
            hashTmp.put("sub" , allData.getFamily().get(0).getUser().get(i).getU_name());
            list_data.add(new HashMap<String, String>(hashTmp));
            hashTmp.clear();
        }

        SimpleAdapter simp = new SimpleAdapter(view.getContext(), list_data, R.layout.two_line_list_item,
                new String[]{"u_name", "u_data", "sub"}, new int[]{R.id.item_right, R.id.item_main, R.id.item_sub});

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the AndroiImageView activity_main_imageView = (ImageView) findViewById(R.id.activity_main_imageView);
     * <p/>
     * AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
     * alphaAnimation.setDuration(1000);
     * alphaAnimation.setFillAfter(true);d Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private boolean loadData(Context context) {
        // アプリ標準の Preferences を取得する
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);

        allData = JSON.decode(sp.getString("DATA_JSON", "{}"), Data.class);

        boolean ans;
        ans = allData.getFamily() != null;

        return ans;
    }
}

