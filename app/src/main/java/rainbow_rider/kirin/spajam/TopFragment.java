package rainbow_rider.kirin.spajam;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Toast;

import net.arnx.jsonic.JSON;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import rainbow_rider.kirin.spajam.Data.Data;
import rainbow_rider.kirin.spajam.Data.Family;
import rainbow_rider.kirin.spajam.Data.Genre;
import rainbow_rider.kirin.spajam.Data.Work;
import rainbow_rider.kirin.spajam.Data.arrayadapter.ItemListAdapter;
import rainbow_rider.kirin.spajam.Data.arrayadapter.ListItem;
import rainbow_rider.kirin.spajam.transfer.async.work.AsyncWorkGenreList;

public class TopFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mGenreId;
    private String mFId;
    private Data allData = new Data();

    private OnTopFragmentListener mListener;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private ItemListAdapter mAdapter;
    private AbsListView mListView;
    public TopFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param genreId Parameter 1.
     * @param fId Parameter 2.
     * @return A new instance of fragment TopFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TopFragment newInstance(String genreId, String fId) {
        TopFragment fragment = new TopFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, genreId);
        args.putString(ARG_PARAM2, fId);
        fragment.setArguments(args);
        Log.d("-------","--------");
        Log.d(genreId, String.valueOf(fId));
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

        loadData(view.getContext());

        String genreName = new String();
        Integer genreId = Integer.valueOf(mGenreId);

        switch (Integer.valueOf(genreId)) {
            case 1:
                genreName = "そうじクエスト";
                break;
            case 2:
                genreName = "りょうりクエスト";
                break;
            case 3:
                genreName = "せんたくクエスト";
                break;
            case 4:
                genreName = "べんきょうクエスト";
                break;
            case 5:
                genreName = "かいものクエスト";
                break;
            case 6:
                genreName = "そのほかクエスト";
                break;
        }

        Toast.makeText(view.getContext(), genreName, Toast.LENGTH_SHORT).show();

        Genre genre = new Genre();
        genre.setG_id(genreId);

        Family family = new Family();
        family.setF_id(mFId);

        Work work = new Work();
        work.setGenre(genre);

        ArrayList<Work> works = new ArrayList<>();
        works.add(work);
        family.setWork(works);

        mAdapter = new ItemListAdapter(view.getContext(), R.layout.item_list_view);

        mListView = (AbsListView) view.findViewById(R.id.fragment_top_list_view);

        mListView.setAdapter(mAdapter);

        new AsyncWorkGenreList(family) {
            @Override
            protected void onPostExecute(Data data) {
                super.onPostExecute(data);
                Data reply = getReply();

                if (reply == null) {
                    Log.d("-------------", "NotComplete");
                } else {
                    ListItem listItem = new ListItem();
                    for (int i = 0 ; i < reply.getFamily().size(); i ++){
                        listItem.setmTitle(reply.getFamily().get(0).getWork().get(i).getW_name());
                        listItem.setmPoint(reply.getFamily().get(0).getWork().get(i).getPoint());
                        String imageName = reply.getFamily().get(0).getWork().get(i).getImage();
                        try {
                            InputStream inputStream = getResources().getAssets().open("images/" + imageName);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            listItem.setmIcon(bitmap);
                        } catch (IOException e) {
                            Log.d("---- Assets ----","---- Error ----");
                        }
                        mAdapter.add(listItem);
                    }

                    Log.d(String.valueOf(reply.getFamily().get(0).getWork().size()) , " ------ replay get size ------ ");
                    Log.d(reply.getFamily().get(0).getWork().get(0).getW_text(), " W text !!!------------");

                }

                try {
                    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            mListener.onTopFragmentItemClick(position);
                        }
                    });
                } catch (NullPointerException e) { }
            }
        }.execute();


        Log.d("------------------", "Complete");

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

    public interface OnTopFragmentListener {
        // TODO: Update argument type and name
        void onTopFragmentItemClick(int wId);
    }

    private boolean loadData(Context context) {
        // アプリ標準の Preferences を取得する
        SharedPreferences sp =  context.getSharedPreferences("allData",Context.MODE_PRIVATE);

        allData = JSON.decode(sp.getString("DATA_JSON", "{}"), Data.class);

        boolean ans;
        ans = allData.getFamily() != null;

        return ans;
    }
}
