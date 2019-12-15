package in.binplus.shoparounds.Fragment;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import in.binplus.shoparounds.AppController;
import in.binplus.shoparounds.Config.BaseURL;
import in.binplus.shoparounds.LoginActivity;
import in.binplus.shoparounds.MainActivity;
import in.binplus.shoparounds.Module.Module;
import in.binplus.shoparounds.R;
import in.binplus.shoparounds.util.ConnectivityReceiver;
import in.binplus.shoparounds.util.CustomVolleyJsonRequest;
import in.binplus.shoparounds.util.Session_management;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactAdminFragmnet extends Fragment {

    String language;
    SharedPreferences preferences;
    EditText et_name , et_phone , et_message ;
    Session_management sessionManagement;
    Button submit ;

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

        ((MainActivity) getActivity()).setTitle("Contact Admin");

        sessionManagement = new Session_management(getActivity());
        Button submit = view.findViewById( R.id.btnSubmit );
        et_phone = (EditText) view.findViewById(R.id.et_phone);
        et_name = (EditText) view.findViewById(R.id.et_userid);
        et_message=(EditText)view.findViewById( R.id.et_msg );
        String getemail = sessionManagement.getUserDetails().get( BaseURL.KEY_EMAIL);
        String getname = sessionManagement.getUserDetails().get(BaseURL.KEY_NAME);
        String getphone = sessionManagement.getUserDetails().get(BaseURL.KEY_MOBILE);
        String getmessage= et_message.getText().toString();
        et_name.setText(getname);
        et_name.setEnabled( false );
        et_phone.setText(getphone);
        et_phone.setEnabled( false );


        submit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptRegister();

                //   Toast.makeText( getActivity(),"you response is accepted :" +names +""+emails+""+msg+""+phones,Toast.LENGTH_LONG ).show();

                // fetchEnquiry();
            }
        } );


        return view ;
    }

    private void attemptRegister() {
        if (ConnectivityReceiver.isConnected()) {

            String getphone = et_phone.getText().toString();
            String getname = et_name.getText().toString();
            String getmessage = et_message.getText().toString();


            if(getmessage.isEmpty())
            {
                et_message.setError("please enter some suggestion");
            }
            else
            {
                makeRegisterRequest(getname, getphone,getmessage);
            }
            //          Toast.makeText( getActivity(),"you response is accepted :" +getname +""+getemail+""+getmessage+""+getphone,Toast.LENGTH_LONG ).show();



        }
    }

    @Override
    public void onStart() {
        super.onStart();

    }
    private void makeRegisterRequest(String name, String mobile,
                                      String message) {

        progressDialog.show();

        String getUsserId=sessionManagement.getUserDetails().get( BaseURL.KEY_ID );
        // Tag used to cancel the request
        String tag_json_obj = "json_suggest_req";

        Map<String, String> params = new HashMap<String, String>();

        params.put("user_id", getUsserId );
        params.put("mobile", mobile);
        params.put("message", message);

        CustomVolleyJsonRequest jsonObjReq = new CustomVolleyJsonRequest( Request.Method.POST, BaseURL.PUT_SUGGESTION_URL, params, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("contact admin", response.toString());

                try {
                    boolean status = response.getBoolean("responce");
                    if (status) {
                        progressDialog.dismiss();
                        String msg = response.getString("message");
                        Toast.makeText( getActivity(), "" + msg, Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(getActivity(),MainActivity.class);
                        startActivity(i);
                        //  finish();
                        // submit.setEnabled(false);

                    } else {
                       progressDialog.dismiss();
                        String error = response.getString("error");
                        submit.setEnabled(true);
                        Toast.makeText(getActivity(), "error" + error, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    progressDialog.dismiss();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
               progressDialog.dismiss();
                String errormsg = Module.VolleyErrorMessage(error);
                Toast.makeText( getActivity(),""+ errormsg,Toast.LENGTH_LONG ).show();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }


    public void fetchEnquiry() {
        String Url ="https://www.trolleyxpress.com/index.php/api/put_suggestion";
        final String names = et_name.getText().toString();

        final String phones = et_phone.getText().toString();
        final String msg = et_message.getText().toString();

        //Toast.makeText( ContactUs.this, "name" + names+"\n email "+emails, Toast.LENGTH_LONG ).show();
        Toast.makeText( getActivity(),"you response is accepted :" +names +""+msg+""+phones,Toast.LENGTH_LONG ).show();

        StringRequest stringRequest =  new StringRequest( Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject( response );
                    String status = jsonObject.getString("responce");
                    if(status.equals( ("true") )) {
                        Toast.makeText( getActivity(), "response saved" + response, Toast.LENGTH_LONG ).show();
                    }
                    else
                    {
                        Toast.makeText( getActivity(), "response failed" + response, Toast.LENGTH_LONG ).show();
                    }



                } catch (Exception e) {
                    e.printStackTrace();
                }




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText( getActivity() ,"error"+ error.getMessage(),Toast.LENGTH_LONG ).show();
            }
        } ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String ,String> map = new HashMap<>(  );
                map.put( "user_name" ,names );

                map.put("user_phone",phones);
                map.put("message" ,msg);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue( getActivity() );
        requestQueue.add( stringRequest );
    }

}
