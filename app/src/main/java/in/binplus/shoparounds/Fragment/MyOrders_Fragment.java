package in.binplus.shoparounds.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.binplus.shoparounds.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrders_Fragment extends Fragment {


    public MyOrders_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_my_orders, container, false );
    }

}