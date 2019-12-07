package in.binplus.shoparounds;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import in.binplus.shoparounds.Config.BaseURL;
import in.binplus.shoparounds.util.CustomVolleyJsonArrayRequest;
import in.binplus.shoparounds.util.CustomVolleyJsonRequest;

public class RegisterActivity extends Activity {

    EditText et_name,et_mobile,et_add,et_adhar,et_pass,et_cpass,et_vehicle,et_vehicle_no ,et_pin ;
    Button btn_register ;
    ProgressDialog progressDialog ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register);
        progressDialog=new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Loading...");

        et_name=findViewById( R.id.et_name );
        et_mobile=findViewById( R.id.et_phone );
        et_add=findViewById( R.id.et_address );
        et_adhar=findViewById( R.id.et_adharno );
        et_pass =findViewById( R.id.et_pass );
        et_cpass=findViewById( R.id.et_cpass );
        et_vehicle=findViewById( R.id.et_vehicle );
        et_vehicle_no=findViewById( R.id.et_vehicle_no );
        et_pin=findViewById( R.id.et_pin);

        btn_register=findViewById( R.id.btnRegister );

        String mobile_no = getIntent().getStringExtra( "mobile" );
        et_mobile.setText( mobile_no );
        et_mobile.setEnabled( false );
       // et_mobile.setTextColor( Color.parseColor( String.valueOf( getResources().getColor( R.color.dark_gray ) ) ) );

        btn_register.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getname = et_name.getText().toString() ;
                String getmobile =et_mobile.getText().toString();
                String getadd =et_mobile.getText().toString();
                String getpass =et_pass.getText().toString();
                String getcpass=et_cpass.getText().toString();
                String getvtype = et_vehicle.getText().toString();
                String getadhar =et_adhar.getText().toString();
                String getvnum =et_vehicle_no.getText().toString();
                String getpin = et_pin.getText().toString();

                if (getname.isEmpty())
                {
                    et_name.setError( "Please enter Name" );
                    et_name.requestFocus();
                }
                else if (getmobile.isEmpty())
                {
                    et_mobile.setError( "Please enter Mobile number" );
                    et_mobile.requestFocus();
                }
                else if( !(getmobile.length()==10))
                {
                    et_mobile.setError( "Please enter Mobile number" );
                    et_mobile.requestFocus();

                }
                else if (getmobile.startsWith( "+" ))
                {
                    et_mobile.setError( "Please enter valid Mobile number" );
                    et_mobile.requestFocus();
                }
                else if (getmobile.startsWith( "0" ))
                {
                    et_mobile.setError( "Please enter valid Mobile number" );
                    et_mobile.requestFocus();
                }

                else if( getpass.isEmpty())
                {
                    et_pass.setError( "please enter password" );
                    et_pass.requestFocus();
                }
                else if(getpass.length()<4)
                {
                    et_pass.setError( "password too short" );
                et_pass.requestFocus();
                }
                else if (getcpass.isEmpty())
                {
                    et_cpass.setError( "please re-enter password" );
                    et_cpass.requestFocus();

                }
                else if (getcpass.length()<4)
                {
                    et_cpass.setError( "password too short" );
                    et_cpass.requestFocus();
                }
                else if(getadhar.isEmpty())
                {
                    et_adhar.setError( "enter adhaar number" );
                    et_adhar.requestFocus();
                }
                else if (!(getadhar.length() == 12))
                {
                    et_adhar.setError( "invalid adhaar number" );
                    et_adhar.requestFocus();
                }
                else if (getvtype.isEmpty())
                {
                    et_vehicle.setError( "enter your vehicle model" );
                    et_vehicle.requestFocus();
                }
                else if (getvnum.isEmpty())
                {
                    et_vehicle_no.setError( "enter`your vehicle number" );
                    et_vehicle_no.requestFocus();
                }

                else if(getadd.isEmpty())
                {
                    et_add.setError( "Enter address" );
                    et_add.requestFocus();
                }
                else if (getpin.isEmpty())
                {
                    et_pin.setError( "enter pincode" );
                    et_pin.requestFocus();
                }
                else if(!(getpin.length()==6))
                {
                    et_pin.setError( "invalid pincode" );
                    et_pin.requestFocus();
                }
                else if (!(getpass.equals( getcpass )))
                    {
                        et_cpass.setError( "passwords dont match" );
                        et_cpass.requestFocus();

                    }
                else
                    {
                        registerUser( getmobile,getname,getpass,getadhar,getvnum,getvtype,getadd,getadd );
                    }
                }


        } );


    }

private void registerUser ( String mobile, String name,String password,
                            String adhar,String v_no,String v_name,String add ,String pin)
{
    HashMap<String ,String> params = new HashMap<>(  );
    params.put( "user_mobile",mobile );
    params.put( "user_name",name );
    params.put("password",password);
    params.put("adhar",adhar);
    params.put( "vehicle_no",v_no );
    params.put( "vehicle_name",v_name);
    params.put( "address",add );
    params.put( "pincode",pin );
    progressDialog.show();
    CustomVolleyJsonRequest jsonRequest = new CustomVolleyJsonRequest( Request.Method.POST, BaseURL.REGISTER_URL, params,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try
                    {
                        Boolean status = response.getBoolean( "responce" );
                        if (status == true)
                        {
                            String msg = response.getString( "message" );
                            Toast.makeText( RegisterActivity.this,""+msg ,Toast.LENGTH_LONG).show();
                            Intent intent = new Intent( RegisterActivity.this,LoginActivity.class );
                            startActivity( intent );
                        }
                        else
                        {
                            Toast.makeText( RegisterActivity.this,"Not registered" ,Toast.LENGTH_LONG).show();
                        }
                    progressDialog.dismiss();
                    }

                    catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText( RegisterActivity.this,""+e.getMessage() ,Toast.LENGTH_LONG).show();
                    }

                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText( RegisterActivity.this,""+error.getMessage() ,Toast.LENGTH_LONG).show();
        }
    } );
    AppController.getInstance().addToRequestQueue( jsonRequest );
}
}
