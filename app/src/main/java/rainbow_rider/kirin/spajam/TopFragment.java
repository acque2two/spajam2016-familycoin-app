package rainbow_rider.kirin.spajam;

import android.content.Context;
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
import java.util.Collection;

import rainbow_rider.kirin.spajam.Data.Data;
import rainbow_rider.kirin.spajam.Data.Family;
import rainbow_rider.kirin.spajam.Data.Genre;
import rainbow_rider.kirin.spajam.Data.Work;
import rainbow_rider.kirin.spajam.Data.arrayadapter.ItemListAdapter;
import rainbow_rider.kirin.spajam.transfer.async.work.AsyncWorkGenreList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnTopFragmentListener} interface
 * to handle interaction events.activity_top_button
 * Use the {@link TopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mGenreId;
    private String mFId;

    private OnTopFragmentListener mListener;

    public TopFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TopFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TopFragment newInstance(String param1, String param2) {
        TopFragment fragment = new TopFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        Log.d("-------","--------");
        Log.d(param1,param2);
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
        return inflater.inflate(R.layout.fragment_top, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toast.makeText( view.getContext() , mFId,Toast.LENGTH_SHORT ).show();

        Genre genre = new Genre();

        final ItemListAdapter mAdapter = new ItemListAdapter(view.getContext(), R.layout.activity_top);
        final AbsListView mListView = (AbsListView) view.findViewById(R.id.list_view);

        genre.setG_id(Integer.parseInt(mGenreId));
        Family family = new Family();
        family.setF_id(mFId);
        genre = new Genre();
        Work work = new Work();
        ArrayList<Work> works = new ArrayList<>();
        genre.setG_id(Integer.parseInt(mGenreId));
        work.setGenre(genre);
        works.add(work);
        family.setWork(works);

        Collection<Work> works_recv = new ArrayList<>();

        new AsyncWorkGenreList( family ) {
            @Override
            protected void onPostExecute( Data data ) {
                super.onPostExecute( data );
                Data reply = getReply();

                mAdapter.addAll( reply.getFamily().get( 0 ).getWork() );
                try {
                    mListView.setAdapter( mAdapter );
                } catch ( NullPointerException v ) {
                    Toast.makeText( view.getContext(), "データが空です", Toast.LENGTH_SHORT ).show();
                }
            }
        }.execute();


        Log.d("------------------", "Complete");


//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTopFragmentListener) {
            mListener = (OnTopFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnTopFragmentListener");
        }
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
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnTopFragmentListener {
        // TODO: Update argument type and name
        //void onTopFragmentItemClick(Question question);
    }
}
