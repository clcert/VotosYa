package com.ninjup.votosya.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ninjup.votosya.R;
import com.ninjup.votosya.data.model.Table;
import com.ninjup.votosya.view.fragment.TableFragment;
import com.ninjup.votosya.view.fragment.TableFragment.OnListFragmentInteractionListener;
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
public class TablesAdapter extends RecyclerView.Adapter<TablesAdapter.ViewHolder> {

    private List<Table> tables = Collections.emptyList();
    ;
    private final TableFragment.OnListFragmentInteractionListener mListener;

    public TablesAdapter(TableFragment.OnListFragmentInteractionListener listener) {
        tables = Collections.emptyList();
        mListener = listener;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    @Override
    public TablesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place, parent, false);
        return new TablesAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final TablesAdapter.ViewHolder holder, int position) {
        holder.table = tables.get(position);
        holder.id.setText(String.valueOf(holder.table.id));
        holder.content.setText("Mesa: " + holder.table.numero);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    Log.d("TAG","PRESIONO MESA");
                    mListener.onListFragmentInteraction(holder.table);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return tables.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_id)
        TextView id;
        @BindView(R.id.txt_nombre)
        TextView content;
        View itemView;
        public Table table;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }
    }
}