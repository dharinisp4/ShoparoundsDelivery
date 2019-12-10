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
public class ContactAdminFragmnet extends Fragment {

ProgressDialog progressDialog ;
    public ContactAdminFragmnet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate( R.layout.fragment_contact_admin, container, false );

        progressDialog=new ProgressDialog( getActivity());
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Loading...");
       return view ;
    }

}
