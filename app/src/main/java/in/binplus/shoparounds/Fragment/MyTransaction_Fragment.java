package in.binplus.shoparounds.Fragment;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.binplus.shoparounds.LoginActivity;
import in.binplus.shoparounds.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyTransaction_Fragment extends Fragment {

    ProgressDialog progressDialog ;

    public MyTransaction_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        progressDialog=new ProgressDialog( getActivity());
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Loading...");
        return inflater.inflate( R.layout.fragment_my_transaction, container, false );
    }

}
