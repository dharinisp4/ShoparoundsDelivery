package in.binplus.shoparounds;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import in.binplus.shoparounds.Config.BaseURL;
import in.binplus.shoparounds.Module.Module;
import in.binplus.shoparounds.util.CustomVolleyJsonRequest;
import in.binplus.shoparounds.util.Session_management;

public class LoginActivity extends AppCompatActivity {
    Button btnlogin;
    TextView txt_register , txt_forgot ;
    EditText user_name , user_pass ;
    Module module;
    ProgressDialog progressDialog ;

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("ShopArounds Delivery");
        builder.setIcon( R.drawable.delivery_boy );

        builder.setMessage("Are you sure want to exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                finish();
                //  getActivity().finishAffinity();


            }
        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );
        module=new Module(LoginActivity.this);
        btnlogin = findViewById( R.id.btnContinue );
        txt_register = findViewById( R.id.btnRegister );
        user_name = findViewById( R.id.et_login_email );
        txt_forgot =findViewById( R.id.btnForgot );
        user_pass=findViewById( R.id.et_login_pass );
        progressDialog=new ProgressDialog(LoginActivity.this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Loading...");
        btnlogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getname = user_name.getText().toString();
                String getpass = user_pass.getText().toString();
                if (getname.isEmpty())
                {
                    user_name.setError( "please enter user id" );
                    user_name.requestFocus();
                }

                else if (getpass.isEmpty())
                {
                    user_pass.setError( "please enter password" );
                    user_pass.requestFocus();
                }
                else
                {
                   // Toast.makeText( LoginActivity.this,""+getname+"\n"+getpass,Toast.LENGTH_LONG ).show();
                    getLogin( getname,getpass );
                }

            }
        } );
        txt_register.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( LoginActivity.this,MobileVerify.class );
                intent.putExtra( "type","r" );
                startActivity( intent );
            }
        } );
        txt_forgot.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( LoginActivity.this,MobileVerify.class );
                intent.putExtra( "type","f" );
                startActivity( intent );
            }
        } );
    }

    private  void getLogin(String number , String pass) {
        progressDialog.show();
        HashMap<String, String> params = new HashMap<>();
        params.put( "mobile", number );
        params.put( "password", pass );


        CustomVolleyJsonRequest jsonRequest = new CustomVolleyJsonRequest( Request.Method.POST, BaseURL.LOGIN_URL, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Boolean status = response.getBoolean( "responce" );
                            if (status == true) {

                            //    Toast.makeText( LoginActivity.this, "" + response.getString( "data" ), Toast.LENGTH_LONG ).show();
                                JSONObject object = response.getJSONObject( "data" );
                                String name =object.getString( "user_name" );
                                String id = object.getString( "id" );
                                String user_id = object.getString( "user_id" );
                                String mobile =object.getString( "user_phone" );
                                String adhar = object.getString( "adhar_id" );
                                String v_no = object.getString( "vehicle_no" );
                                String v_name=object.getString( "vehicle_name" );
                                String add =object.getString( "address" );
                                String pin = object.getString("pincode");
                               // String msg = response.getString( "message" );
                               Toast.makeText( LoginActivity.this,"Logged in Successfully" ,Toast.LENGTH_LONG).show();
                              //  Toast.makeText( LoginActivity.this, "" + response.getString( "data" ), Toast.LENGTH_LONG ).show();
                                Session_management session = new Session_management( LoginActivity.this );
                                session.createLoginSession(id,user_id,adhar,name,mobile,"",v_name,v_no,pin,add,"","",""  );
                                Intent i = new Intent( LoginActivity.this, MainActivity.class );

                                startActivity( i );
                            } else {
                             // String msg = response.getString( "message" );
                               // Toast.makeText( LoginActivity.this,"Wrong Username or Password" ,Toast.LENGTH_LONG).show();
                              Toast.makeText( LoginActivity.this, "" + response.getString( "data" ), Toast.LENGTH_LONG ).show();
                            }
                            progressDialog.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText( LoginActivity.this, "" + e.getMessage(), Toast.LENGTH_LONG ).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        String msg=module.VolleyErrorMessage(error);
                        if(!(msg.isEmpty() || msg.equals("")))
                        {
                            Toast.makeText(LoginActivity.this,""+msg.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                } );
            AppController.getInstance().addToRequestQueue( jsonRequest );
    }
}
