package in.binplus.shoparounds.Fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import in.binplus.shoparounds.AppController;
import in.binplus.shoparounds.Config.BaseURL;
import in.binplus.shoparounds.LoginActivity;
import in.binplus.shoparounds.MainActivity;
import in.binplus.shoparounds.Module.Module;
import in.binplus.shoparounds.R;
import in.binplus.shoparounds.ResetPassword;
import in.binplus.shoparounds.util.CustomVolleyJsonRequest;

import static com.android.volley.VolleyLog.TAG;


public class EditProfileFragment extends Fragment {
   EditText et_name ,et_add ,et_pin ,et_adhar,et_mobile ,et_vno ,et_vname ,et_uname ;
   EditText oldpass ,newpass ;
   Button UpdatePassword;
   Module module;
   String name , address , pincode , vehiclename ,vehicleno ,adharid  ,id , mobile;
Button update;
ProgressDialog progressDialog ;


    public EditProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate( R.layout.fragment_edit_profile, container, false );

        ((MainActivity) getActivity()).setTitle("Edit Profile");
       module=new Module(getActivity());
        et_name = view.findViewById( R.id.et_name );
        et_add =view.findViewById( R.id.et_address );
        et_adhar=view.findViewById( R.id.et_adharno );
        et_mobile=view.findViewById( R.id.et_phone );
        et_vno =view.findViewById( R.id.et_vehicle_no );
        et_vname=view.findViewById( R.id.et_vehicle );
        et_pin =view.findViewById( R.id.et_pin );
        et_uname=view.findViewById( R.id.et_user_name );
        oldpass= view.findViewById( R.id.up_oldpass );
        newpass =view.findViewById( R.id.up_newpass );

        update = view.findViewById( R.id.btnUpdate );

        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Loading...");

        name = getArguments().getString( "user_name" );
        id =getArguments().getString( "user_id" );
        address =getArguments().getString( "user_add" );
        adharid=getArguments().getString( "adhar_no" );
        pincode=getArguments().getString( "pincode" );
        vehiclename=getArguments().getString( "vehicle_name" );
        vehicleno =getArguments().getString( "vehicle_no" );
        mobile =getArguments().getString( "user_mobile" );

        et_name.setText( name );
        et_name.setEnabled( false );
        et_uname.setText( id );
        et_uname.setEnabled( false );
        et_add.setText( address );
        et_add.setEnabled( false );
        et_pin.setText( pincode );
        et_pin.setEnabled( false );
        et_vname.setText( vehiclename );
        et_vname.setEnabled( false );
        et_vno.setText( vehicleno );
        et_vno.setEnabled( false );
        et_mobile.setText( mobile );
        et_mobile.setEnabled( false );
        et_adhar.setEnabled( false );
        et_adhar.setText( adharid );

        update.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String get_oldpass = oldpass.getText().toString().trim();
              String get_newpass = newpass.getText().toString().trim();

              if (get_oldpass.isEmpty())
              {
                  oldpass.setError( "please enter your old password" );
                  oldpass.requestFocus();
              }
              else if (get_newpass.isEmpty())
              {
                  newpass.requestFocus();
                  newpass.setError( "please enter new passwrod" );
              }
              else
              {
                  update_password( mobile,get_oldpass,get_newpass );
              }

            }
        } );

        return  view ;
    }

    private void update_password(String number, String pass ,String npass) {
        progressDialog.show();
        String json_tag="json_reset_tag";
        HashMap<String, String> map=new HashMap<>();
        map.put("mobile",number);
        map.put("password",pass);
        map.put( "new_password",npass );

        CustomVolleyJsonRequest customVolleyJsonRequest=new CustomVolleyJsonRequest( Request.Method.POST, BaseURL.UpdatePassword, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                progressDialog.dismiss();
                try {
                    Boolean status = response.getBoolean("responce");
                    Toast.makeText( getActivity(), "" + status, Toast.LENGTH_SHORT).show();
                    if (status) {
                        String error = response.getString("error");

                        Toast.makeText( getActivity(), "" + error, Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                    } else {
                        String error = response.getString("error");

                        Toast.makeText(getActivity(), "" + error, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                String msg=module.VolleyErrorMessage(error);
                if(!(msg.isEmpty() || msg.equals("")))
                {
                    Toast.makeText(getActivity(),""+msg.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });

        AppController.getInstance().addToRequestQueue(customVolleyJsonRequest,json_tag);

    }

}
