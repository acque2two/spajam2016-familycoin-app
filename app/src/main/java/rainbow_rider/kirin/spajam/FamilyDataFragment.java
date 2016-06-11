package rainbow_rider.kirin.spajam;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Toast;

import java.util.ArrayList;

import rainbow_rider.kirin.spajam.Data.Data;
import rainbow_rider.kirin.spajam.Data.Family;
import rainbow_rider.kirin.spajam.Data.Work;
import rainbow_rider.kirin.spajam.Data.arrayadapter.ItemListAdapter;
import rainbow_rider.kirin.spajam.transfer.async.work.AsyncWorkGenreList;


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

        Toast.makeText( view.getContext() , "かぞくのじょうほう",Toast.LENGTH_SHORT ).show();

        final ItemListAdapter mAdapter = new ItemListAdapter(view.getContext(), R.layout.activity_top);
        final AbsListView mListView = (AbsListView) view.findViewById(R.id.list_view);

        Family family = new Family();
        family.setF_id(mFId);
        //genre = new Genre();
        Work work = new Work();
        ArrayList<Work> works = new ArrayList<>();
        works.add(work);
        family.setWork(works);

        new AsyncWorkGenreList( family ) {
            @Override
            protected void onPostExecute( Data data ) {
                super.onPostExecute( data );
                Data reply = getReply();

                if ( reply == null ){
                    Log.d("reply","is null");
                } else {
                    mAdapter.addAll(reply.getFamily().get(0).getWork());
                }
                try {
                    mListView.setAdapter( mAdapter );
                } catch ( NullPointerException v ) {
                    Toast.makeText( view.getContext(), "データが空です", Toast.LENGTH_SHORT ).show();
                }
            }
        }.execute();

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

        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(1000);
        alphaAnimation.setFillAfter(true);d Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
