package com.ninjup.votosya.view.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.ninjup.votosya.data.model.Result;
import com.ninjup.votosya.interactor.ResultInteractor;
import com.ninjup.votosya.presenter.ResultPresenter;
import com.ninjup.votosya.view.adapter.ResultAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ninjup.votosya.view.activity.HomeActivity.inicio;

/**
 * Created by Njara on 13-07-2017.
 */

public class ResultFragment extends Fragment implements ResultPresenter.View {
    @BindView(R.id.rv_artists)
    RecyclerView rv_artist;
    @BindView(R.id.pv_artists)
    ProgressBar pv_artists;
    @BindView(R.id.iv_artists)
    ImageView iv_artists;
    @BindView(R.id.txt_line_artists)
    TextView txt_line_artists;

    @BindView(R.id.txt_mesas_totales)
    TextView txt_mesas_totales;
    @BindView(R.id.txt_mesas_reportadas)
    TextView txt_mesas_reportadas;
    @BindView(R.id.txt_porcentaje_mesas)
    TextView txt_porcentaje_mesas;
    @BindView(R.id.txt_votos_totales)
    TextView txt_votos_totales;
    @BindView(R.id.txt_subline_artists)
    TextView txt_sub_line_artists;

    ResultAdapter adapter;

    private ResultPresenter candidatesPresenter;

    public ResultFragment() {
        setHasOptionsMenu(true);
    }

    private CandidatesFragment.OnListFragmentInteractionListener mListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        candidatesPresenter = new ResultPresenter(new ResultInteractor(new ServerClient()));
        candidatesPresenter.setView(this);
        inicio = false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setupRecyclerView();
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
        inflater.inflate(R.menu.result, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_github) {
            startActivityActionView();
        }
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
    public void renderResult(Result result) {
        txt_mesas_totales.setText("Mesas Totales: " + String.valueOf(result.mesas_totales));
        txt_mesas_reportadas.setText("Mesas Reportadas: " + String.valueOf(result.mesas_reportadas));
        txt_porcentaje_mesas.setText("Porcentaje Mesas: " + String.valueOf(result.porcentaje_mesas));
        txt_votos_totales.setText("Votos Totales: " + String.valueOf(result.votos_totales));
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
        adapter = (ResultAdapter) rv_artist.getAdapter();
        adapter.setCandidates(candidates);
        adapter.notifyDataSetChanged();
    }

    private void setupRecyclerView() {
        ResultAdapter adapter = new ResultAdapter();
        adapter.setItemClickListener(new ResultAdapter.ItemClickListener() {
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
        candidatesPresenter.onSearchCandidates();
    }
}
