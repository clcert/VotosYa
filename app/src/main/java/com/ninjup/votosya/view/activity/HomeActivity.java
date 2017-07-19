package com.ninjup.votosya.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.ninjup.votosya.R;
import com.ninjup.votosya.data.api.retrofit.ServerRetrofitService;
import com.ninjup.votosya.data.api.retrofit.ServiceGenerator;
import com.ninjup.votosya.data.model.Candidate;
import com.ninjup.votosya.data.model.Place;
import com.ninjup.votosya.data.model.Reporte;
import com.ninjup.votosya.data.model.Table;
import com.ninjup.votosya.data.model.Votes;
import com.ninjup.votosya.view.fragment.CandidatesFragment;
import com.ninjup.votosya.view.fragment.PlaceFragment;
import com.ninjup.votosya.view.fragment.ResultFragment;
import com.ninjup.votosya.view.fragment.RootFragment;
import com.ninjup.votosya.view.fragment.TableFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements PlaceFragment.OnListFragmentInteractionListener, TableFragment.OnListFragmentInteractionListener, CandidatesFragment.OnListFragmentInteractionListener {


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private Reporte reporte;
    public static boolean inicio = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        reporte = new Reporte();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListFragmentInteraction(Place item) {
        Toast.makeText(getApplicationContext(), "Recinto: " + item.nombre, Toast.LENGTH_LONG).show();
        FragmentTransaction trans = getSupportFragmentManager()
                .beginTransaction();
                /*
                 * IMPORTANT: We use the "root frame" defined in
				 * "root_fragment.xml" as the reference to replace fragment
				 */
        trans.replace(R.id.root_frame, new TableFragment(null, item.mesas));

				/*
                 * IMPORTANT: The following lines allow us to add the fragment
				 * to the stack and return to it later, by pressing back
				 */
        trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        trans.addToBackStack(null);

        trans.commit();
    }

    @Override
    public void onListFragmentInteraction(Table item) {
        Toast.makeText(getApplicationContext(), "Mesa: " + item.numero, Toast.LENGTH_LONG).show();
        reporte.mesa = item.numero;
        FragmentTransaction trans = getSupportFragmentManager()
                .beginTransaction();
                /*
                 * IMPORTANT: We use the "root frame" defined in
				 * "root_fragment.xml" as the reference to replace fragment
				 */
        trans.replace(R.id.root_frame, new CandidatesFragment());

				/*
                 * IMPORTANT: The following lines allow us to add the fragment
				 * to the stack and return to it later, by pressing back
				 */
        trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        trans.addToBackStack(null);

        trans.commit();

    }


    public void init() {
        FragmentTransaction trans = getSupportFragmentManager()
                .beginTransaction();
                /*
                 * IMPORTANT: We use the "root frame" defined in
				 * "root_fragment.xml" as the reference to replace fragment
				 */
        trans.replace(R.id.root_frame, new PlaceFragment());

				/*
                 * IMPORTANT: The following lines allow us to add the fragment
				 * to the stack and return to it later, by pressing back
				 */
        trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        trans.addToBackStack(null);

        trans.commit();
    }

    private void sendReporte(JsonObject reporte) {
        Log.d("TAG", " sendReporte");
        ServerRetrofitService taskService = ServiceGenerator.createServiceLogin(ServerRetrofitService.class);
        Call<JsonObject> call = taskService.postReporteOld(reporte);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Datos Enviados", Toast.LENGTH_SHORT).show();
                    init();
                }

            }


            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error de Internet!" + t.getCause(), Toast.LENGTH_SHORT).show();
                Log.d("TAG", " onFailure");

            }
        });
        Log.d("TAG", " END");

    }


    @Override
    public void onListFragmentInteraction(List<Candidate> candidates) {


        reporte.conteos = new ArrayList<>();
        for (Candidate candidate : candidates) {
            reporte.conteos.add(new Votes(candidate.id, candidate.votos));
        }
        sendReporte(reporte.toJson());
        //   ServerService serverService = new ServerClient();
        //  Observable<Reporte> re = serverService.postReporte(reporte);
        //  Log.d("TAG", "" + re.toString());
        Log.d("TAG", "onListFragmentInteraction");
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_home, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new RootFragment();
                case 1:
                    return new ResultFragment();
            }
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return new PlaceFragment();
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "REPORTES";
                case 1:
                    return "RESULTADOS";
            }
            return null;
        }
    }

    @Override
    public void onBackPressed() {

        if (inicio) {
            finish();
        } else {
            super.onBackPressed();
        }
    }
}
