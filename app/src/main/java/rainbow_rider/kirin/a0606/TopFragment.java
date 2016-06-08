package rainbow_rider.kirin.a0606;

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

import rainbow_rider.kirin.a0606.Data.Data;
import rainbow_rider.kirin.a0606.Data.Genre;
import rainbow_rider.kirin.a0606.Data.ItemListAdapter;
import rainbow_rider.kirin.a0606.Data.Multiple.Questions;
import rainbow_rider.kirin.a0606.transfer.question.QuestionGenreList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TopFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
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
        QuestionGenreList questionGenreList = new QuestionGenreList();

        Toast.makeText( view.getContext() ,mParam2,Toast.LENGTH_SHORT ).show();

        Genre genre = new Genre();

        final ItemListAdapter mAdapter = new ItemListAdapter(view.getContext(), R.layout.activity_top);
        final AbsListView mListView = (AbsListView) view.findViewById(R.id.list_view);

        genre.setGenre_id(Integer.parseInt(mParam1));

        new QuestionGenreList(genre) {
            @Override
            protected void onPostExecute(Data data) {
                Data reply = getReply();
                Questions listItemList = new Questions();
                Log.d("Dataサイズ", String.valueOf(new Integer(reply.getQuestion().size())));
                if ( reply.getQuestion() != null ) {
                    Log.d("Question_size","notnull");
//                    for (int i = 0; i < reply.getGenre().size(); i++) {
                    for (int i = 0; i < reply.getQuestion().size(); i++) {

                        Log.d("QId", String.valueOf(new Integer((int) reply.getQuestion().get(i).getQ_id())));
                        //imageをurlから画像に変換する処理を書く
                        listItemList.add(reply.getQuestion().get(i));

                    }
                }else{
                    Log.d("TOPFRAGMENT", "GENRE"+Long.toString(allData.getGenre().get(0).getGenre_id())+" IS NULL");
                }
                Log.d("Comp", "Leate");
                mAdapter.addAll(listItemList);

                try {
                    mListView.setAdapter( mAdapter );
                } catch (NullPointerException v) {
                    Toast.makeText( view.getContext() ,"データが空です" ,Toast.LENGTH_SHORT ).show();
                }


            }
        }.execute();

//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });

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
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
