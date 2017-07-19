package com.ninjup.votosya.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ninjup.votosya.R;
import com.ninjup.votosya.data.api.client.ServerClient;
import com.ninjup.votosya.data.model.Candidate;
import com.ninjup.votosya.data.model.Place;
import com.ninjup.votosya.interactor.PlacesInteractor;
import com.ninjup.votosya.presenter.PlacesPresenter;
import com.ninjup.votosya.view.adapter.PlacesAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ninjup.votosya.view.activity.HomeActivity.inicio;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class PlaceFragment extends Fragment implements PlacesPresenter.View {
    @BindView(R.id.rv_artists)
    RecyclerView rv_artist;
    @BindView(R.id.pv_artists)
    ProgressBar pv_artists;
    @BindView(R.id.iv_artists)
    ImageView iv_artists;
    @BindView(R.id.txt_line_artists)
    TextView txt_line_artists;
    @BindView(R.id.txt_subline_artists)
    TextView txt_sub_line_artists;

    private PlacesPresenter placesPresenter;
    private PlaceFragment.OnListFragmentInteractionListener mListener;

    public PlaceFragment() {
        setHasOptionsMenu(true);
    }

    public PlaceFragment(PlaceFragment.OnListFragmentInteractionListener mListener) {
        this.mListener = mListener;
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        placesPresenter = new PlacesPresenter(new PlacesInteractor(new ServerClient()));
        placesPresenter.setView(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_place, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setupRecyclerView();
        inicio = true;
    }

    @Override
    public void onDestroy() {
        placesPresenter.terminate();
        super.onDestroy();
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_music, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void showLoading() {
        pv_artists.setVisibility(View.VISIBLE);
        iv_artists.setVisibility(View.GONE);
        txt_line_artists.setVisibility(View.GONE);
        txt_sub_line_artists.setVisibility(View.GONE);
        rv_artist.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        pv_artists.setVisibility(View.GONE);
        rv_artist.setVisibility(View.VISIBLE);
    }

    @Override
    public void showCandidateNotFoundMessage() {
        pv_artists.setVisibility(View.GONE);
        txt_line_artists.setVisibility(View.VISIBLE);
        iv_artists.setVisibility(View.VISIBLE);
        txt_line_artists.setText(getString(R.string.error_candidate_not_found));
        iv_artists.setImageDrawable(ContextCompat.getDrawable(getContext(), R.mipmap.ic_not_found));

    }

    @Override
    public void showConnectionErrorMessage() {
        pv_artists.setVisibility(View.GONE);
        txt_line_artists.setVisibility(View.VISIBLE);
        iv_artists.setVisibility(View.VISIBLE);
        txt_line_artists.setText(getString(R.string.error_internet_connection));
        iv_artists.setImageDrawable(ContextCompat.getDrawable(getContext(), R.mipmap.ic_not_internet));

    }

    @Override
    public void showServerError() {
        pv_artists.setVisibility(View.GONE);
        txt_line_artists.setVisibility(View.VISIBLE);
        iv_artists.setVisibility(View.VISIBLE);
        txt_line_artists.setText(getString(R.string.error_server_internal));
        iv_artists.setImageDrawable(ContextCompat.getDrawable(getContext(), R.mipmap.ic_not_found));

    }

    @Override
    public void renderPlaces(List<Place> places) {
        PlacesAdapter adapter = (PlacesAdapter) rv_artist.getAdapter();
        adapter.setPlaces(places);
        adapter.notifyDataSetChanged();
    }

    private void setupRecyclerView() {
        PlacesAdapter adapter = new PlacesAdapter(mListener);
        rv_artist.setAdapter(adapter);
        placesPresenter.getPlaces();
    }

    @Override
    public void launchCandidateDetail(Candidate candidate) {
        //   Intent intent = new Intent(getContext(), TracksActivity.class);
        //     intent.putExtra(TracksActivity.EXTRA_REPOSITORY, artist);
        //    startActivity(intent);
    }

    private void startActivityActionView() {
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://github.com/erikcaffrey/Android-Spotify-MVP")));
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof TableFragment.OnListFragmentInteractionListener) {
            mListener = (PlaceFragment.OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
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
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Place item);
    }


}
