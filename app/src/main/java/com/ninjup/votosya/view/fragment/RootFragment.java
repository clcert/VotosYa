package com.ninjup.votosya.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninjup.votosya.R;

/**
 * Created by Njara on 12-07-2017.
 */

public class RootFragment extends Fragment {

    private static final String TAG = "RootFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /* Inflate the layout for this fragment */
        View view = inflater.inflate(R.layout.fragment_root, container, false);

        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
        /*
         * When this container fragment is created, we fill it with our first
		 * "real" fragment
		 */

        Fragment fragment = new PlaceFragment();

        transaction.replace(R.id.root_frame, fragment);
        transaction.commit();
        Log.d("TAG", "ROOT___________");

        return view;
    }

}
