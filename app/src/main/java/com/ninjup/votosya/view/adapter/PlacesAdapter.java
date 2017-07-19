package com.ninjup.votosya.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ninjup.votosya.R;
import com.ninjup.votosya.data.model.Place;
import com.ninjup.votosya.view.fragment.PlaceFragment.OnListFragmentInteractionListener;
import com.ninjup.votosya.view.fragment.dummy.DummyContent.DummyItem;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.ViewHolder> {

    private List<Place> places= Collections.emptyList();;
    private final OnListFragmentInteractionListener mListener;

    public PlacesAdapter(OnListFragmentInteractionListener listener) {
        places = Collections.emptyList();
        mListener = listener;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place, parent, false);
        return new PlacesAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.place = places.get(position);
        holder.id.setText(String.valueOf(holder.place.id));
        holder.content.setText(holder.place.nombre);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.place);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_id)
        TextView id;
        @BindView(R.id.txt_nombre)
        TextView content;
        View itemView;
        public Place place;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }
    }
}
