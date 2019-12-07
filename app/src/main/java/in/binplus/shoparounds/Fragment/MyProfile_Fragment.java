package in.binplus.shoparounds.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import in.binplus.shoparounds.R;
import in.binplus.shoparounds.util.Session_management;

import static in.binplus.shoparounds.Config.BaseURL.KEY_ADDRESS;
import static in.binplus.shoparounds.Config.BaseURL.KEY_ADHAR_ID;
import static in.binplus.shoparounds.Config.BaseURL.KEY_MOBILE;
import static in.binplus.shoparounds.Config.BaseURL.KEY_NAME;
import static in.binplus.shoparounds.Config.BaseURL.KEY_PINCODE;
import static in.binplus.shoparounds.Config.BaseURL.KEY_USER_ID;
import static in.binplus.shoparounds.Config.BaseURL.KEY_VEHICLE_NAME;
import static in.binplus.shoparounds.Config.BaseURL.KEY_VEHICLE_NO;


public class MyProfile_Fragment extends Fragment {

TextView user_id , user_name ,user_mobile, user_add ,user_vno,user_vname ,user_adhar;
ImageView edit_profile ;
Session_management session ;

    public MyProfile_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view = inflater.inflate( R.layout.fragment_my_profile, container, false );
      user_id = view.findViewById( R.id.user_id );
      user_add =view.findViewById( R.id.user_add );
      user_name=view.findViewById( R.id.user_name );
      user_adhar =view.findViewById( R.id.user_adhar );
      user_mobile = view.findViewById( R.id.user_phone );
      user_vname=view.findViewById( R.id.v_name );
      user_vno =view.findViewById( R.id.v_no );
      edit_profile=view.findViewById( R.id.img_edit );

      session = new Session_management( getActivity() );

       String name = session.getUserDetails().get(KEY_NAME );
        String id =  session.getUserDetails().get( KEY_USER_ID ) ;
        String add= session.getUserDetails().get( KEY_ADDRESS );
        String pincode=session.getUserDetails().get( KEY_PINCODE );
       String mobile= session.getUserDetails().get( KEY_MOBILE  );
        String adhar= session.getUserDetails().get( KEY_ADHAR_ID );
        String vname= session.getUserDetails().get( KEY_VEHICLE_NAME );
        String v_no =session.getUserDetails().get( KEY_VEHICLE_NO  );

      user_name.setText( session.getUserDetails().get(KEY_NAME ));
      user_id.setText( session.getUserDetails().get( KEY_USER_ID ) );
      user_add.setText( session.getUserDetails().get( KEY_ADDRESS ) +"\n" + session.getUserDetails().get( KEY_PINCODE ) );
      user_mobile.setText( session.getUserDetails().get( KEY_MOBILE ) );
      user_adhar.setText( session.getUserDetails().get( KEY_ADHAR_ID ) );
      user_vname.setText( session.getUserDetails().get( KEY_VEHICLE_NAME ) );
      user_vno.setText( session.getUserDetails().get( KEY_VEHICLE_NO ) );

       edit_profile.setOnClickListener( new View.OnClickListener() {
           @Override
           public void onClick(View view) {

           }
       } );
      return  view ;
    }

}
