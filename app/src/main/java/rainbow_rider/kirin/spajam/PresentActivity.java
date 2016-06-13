package rainbow_rider.kirin.spajam;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AbsListView;

import rainbow_rider.kirin.spajam.Data.arrayadapter.ItemListAdapter;

public class PresentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_present);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(PresentActivity.this, PDActivity.class);
                startActivity(callIntent);
            }
        });

        final ItemListAdapter mAdapter = new ItemListAdapter( getApplicationContext(), R.layout.activity_top);
        final AbsListView mListView = (AbsListView) findViewById(R.id.fragment_top_list_view);



    }

}
