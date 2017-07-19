package com.ninjup.votosya.view.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ninjup.votosya.R;
import com.ninjup.votosya.data.api.client.ServerClient;
import com.ninjup.votosya.data.model.Candidate;
import com.ninjup.votosya.interactor.CandidatesInteractor;
import com.ninjup.votosya.presenter.CandidatesPresenter;
import com.ninjup.votosya.view.adapter.CandidatesAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ninjup.votosya.view.activity.HomeActivity.inicio;

/**
 * Created by Njara on 12-07-2017.
 */

public class CandidatesFragment extends Fragment implements CandidatesPresenter.View{
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
    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    CandidatesAdapter adapter;

    private CandidatesPresenter candidatesPresenter;

    public CandidatesFragment() {
        setHasOptionsMenu(true);
    }
    private CandidatesFragment.OnListFragmentInteractionListener mListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        candidatesPresenter = new CandidatesPresenter(new CandidatesInteractor(new ServerClient()));
        candidatesPresenter.setView(this);
        inicio = false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_candidates, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setupRecyclerView();
        floatingActionButton.setVisibility(View.VISIBLE);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Candidate> candidates = new ArrayList<Candidate>();
                for (int i =0; i<adapter.getItemCount();i++){
                    candidates.add(adapter.getItem(i));
                }
                Log.d("TAG","envio candidatos");
                mListener.onListFragmentInteraction(candidates);

            }
        });
    }

    @Override
    public void onDestroy() {
        candidatesPresenter.terminate();
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
    public void renderCandidates(List<Candidate> candidates) {
        Log.d("TAG", "renderCandidates: " + candidates.size() + "__");
        adapter = (CandidatesAdapter) rv_artist.getAdapter();
        adapter.setCandidates(candidates);
        adapter.notifyDataSetChanged();
    }

    private void setupRecyclerView() {
        CandidatesAdapter adapter = new CandidatesAdapter();
        adapter.setItemClickListener(new CandidatesAdapter.ItemClickListener() {
            @Override
            public void onItemClick(Candidate candidate, int position) {

            }

        });
        adapter.setItemClickListener(
                (Candidate candidate, int position) -> candidatesPresenter.launchCandidateDetail(candidate));
        rv_artist.setAdapter(adapter);
        candidatesPresenter.onSearchCandidates();
    }

    @Override
    public void launchCandidateDetail(Candidate candidate) {
        //   Intent intent = new Intent(getContext(), TracksActivity.class);
        //     intent.putExtra(TracksActivity.EXTRA_REPOSITORY, artist);
        //    startActivity(intent);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Ingrese la cantidad de votos.");

// Set up the input
        final EditText input = new EditText(getContext());
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (input.getText().toString().length() > 0) {
                    candidate.votos = Integer.parseInt(input.getText().toString());
                    adapter.notifyDataSetChanged();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void startActivityActionView() {
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://github.com/erikcaffrey/Android-Spotify-MVP")));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CandidatesFragment.OnListFragmentInteractionListener) {
            mListener = (CandidatesFragment.OnListFragmentInteractionListener) context;
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
        void onListFragmentInteraction(List<Candidate> candidates);
    }
}
