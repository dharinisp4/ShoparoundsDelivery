package in.binplus.shoparounds.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import in.binplus.shoparounds.R;


public class EditProfileFragment extends Fragment {
   EditText et_name ,et_add ,et_pin ,et_adhar,et_mobile ,et_vno ,et_vname ,et_uname ;
   EditText oldpass ,newpass ;

   String name , address , pincode , vehiclename ,vehicleno ,adharid  ,id , mobile;
Button update;



    public EditProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate( R.layout.fragment_edit_profile, container, false );
        et_name = view.findViewById( R.id.et_name );
        et_add =view.findViewById( R.id.et_address );
        et_adhar=view.findViewById( R.id.et_adharno );
        et_mobile=view.findViewById( R.id.et_phone );
        et_vno =view.findViewById( R.id.et_vehicle_no );
        et_vname=view.findViewById( R.id.et_vehicle );
        et_pin =view.findViewById( R.id.et_pin );
        et_uname=view.findViewById( R.id.et_user_name );

        name = getArguments().getString( "user_name" );
        id =getArguments().getString( "id" );
        address =getArguments().getString( "user_add" );
        adharid=getArguments().getString( "adhar_no" );
        pincode=getArguments().getString( "pincode" );
        vehiclename=getArguments().getString( "vehicle_name" );
        vehicleno =getArguments().getString( "vehicle_no" );
        mobile =getArguments().getString( "mobile" );

        et_name.setText( name );
        et_uname.setText( id );
        et_uname.setEnabled( false );
        et_add.setText( address );
        et_pin.setText( pincode );
        et_vname.setText( vehiclename );
        et_vname.setEnabled( false );
        et_vno.setText( vehicleno );
        et_vno.setEnabled( false );
        et_mobile.setText( mobile );
        et_mobile.setEnabled( false );
        et_adhar.setEnabled( false );
        et_adhar.setText( adharid );



        return  view ;
    }

}
