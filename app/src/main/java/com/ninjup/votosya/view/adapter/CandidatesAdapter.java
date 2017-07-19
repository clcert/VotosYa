package com.ninjup.votosya.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninjup.votosya.R;
import com.ninjup.votosya.data.model.Candidate;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ninjup.votosya.data.api.Constants.VOTOSYA_API;

/**
 * Created by Njara on 12-07-2017.
 */

public class CandidatesAdapter extends RecyclerView.Adapter<CandidatesAdapter.CandidatesViewHolder> {
    private List<Candidate> candidates;
    private ItemClickListener itemClickListener;

    public CandidatesAdapter() {
        candidates = Collections.emptyList();
    }

    @Override
    public CandidatesAdapter.CandidatesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_candidate, parent, false);
        return new CandidatesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CandidatesViewHolder holder, int position) {
        Candidate candidate = candidates.get(position);

        holder.txt_candidate_title.setText("Id: " + candidate.id + " " + candidate.nombre);
        holder.txt_candidate_party.setText(candidate.partido);
        if (candidate.votos == 0) {
            holder.txt_candidate_votes.setText(String.valueOf("sin ingresar"));
        } else {
            holder.txt_candidate_votes.setText(String.valueOf(candidate.votos));
        }

        Picasso.with(holder.iv_candidate.getContext())
                .load(VOTOSYA_API + candidate.foto)
                .into(holder.iv_candidate);
        Log.d("TAG", "adapter");
        holder.itemView.setOnClickListener((View view) -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(candidate, position);
            }
        });
    }

    public List<Candidate> getArray() {
        return candidates;
    }

    public Candidate getItem(int position) {
        return candidates.get(position);
    }

    @Override
    public int getItemCount() {
        return candidates.size();
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(Candidate candidate, int position);
    }

    public static class CandidatesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_candidate)
        ImageView iv_candidate;
        @BindView(R.id.txt_candidate_title)
        TextView txt_candidate_title;
        @BindView(R.id.txt_candidate_party)
        TextView txt_candidate_party;
        @BindView(R.id.txt_candidate_votes)
        TextView txt_candidate_votes;
        View itemView;

        public CandidatesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }
    }
}
