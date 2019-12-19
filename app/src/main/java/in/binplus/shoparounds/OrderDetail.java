package in.binplus.shoparounds;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

import com.android.volley.VolleyLog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.binplus.shoparounds.Adapter.My_order_detail_adapter;
import in.binplus.shoparounds.Config.BaseURL;
import in.binplus.shoparounds.Models.My_order_detail_model;
import in.binplus.shoparounds.Models.OrderModel;
import in.binplus.shoparounds.util.ConnectivityReceiver;
import in.binplus.shoparounds.util.CustomVolleyJsonArrayRequest;
import in.binplus.shoparounds.util.CustomVolleyJsonRequest;

public class OrderDetail extends AppCompatActivity {
    public TextView tv_orderno, tv_status, tv_date, tv_time, tv_price, tv_item, relativetextstatus, tv_tracking_date,txt_date_type;
    private String sale_id;
    RelativeLayout Mark_Delivered ,Mark_cancelled;
    private RecyclerView rv_detail_order;
    RelativeLayout rel_status ,rel_feedback;
    EditText et_remarks ;
    Button btn_submit ;
    ProgressDialog progressDialog ;


    private List<My_order_detail_model> my_order_detail_modelList = new ArrayList<>();
//    @Override
//    protected void attachBaseContext(Context newBase) {
//        newBase = LocaleChanger.configureBaseContext(newBase);
//        super.attachBaseContext(newBase);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.orderdetail));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderDetail.this, MainActivity.class);
                startActivity(intent);
            }
        });

        progressDialog=new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Loading...");

        rv_detail_order = (RecyclerView) findViewById(R.id.product_recycler);
        rv_detail_order.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv_detail_order.addItemDecoration(new DividerItemDecoration(getApplicationContext(), 0));
        tv_orderno = (TextView) findViewById(R.id.tv_order_no);
        tv_status = (TextView) findViewById(R.id.tv_order_status);
        relativetextstatus = (TextView) findViewById(R.id.status);
        tv_tracking_date = (TextView) findViewById(R.id.tracking_date);
        tv_date = (TextView) findViewById(R.id.tv_order_date);
        tv_time = (TextView) findViewById(R.id.tv_order_time);
        tv_price = (TextView) findViewById(R.id.tv_order_price);
        tv_item = (TextView) findViewById(R.id.tv_order_item);
        rel_status =findViewById( R.id.rel_status );
        btn_submit =findViewById( R.id.btn_submit );
        txt_date_type=findViewById( R.id.txt_date_type );

        Mark_cancelled = findViewById( R.id.btn_mark_cancelled );
        rel_feedback =findViewById( R.id.rel_feedback );
        et_remarks =findViewById( R.id.et_remarks );
        Mark_cancelled.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rel_feedback.setVisibility( View.VISIBLE );
                rel_feedback.requestFocus();
                rv_detail_order.setVisibility( View.GONE );
                Mark_Delivered.setVisibility( View.GONE );
               Mark_cancelled.setVisibility( View.GONE );

            }
        } );
        btn_submit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getRemark = et_remarks.getText().toString().trim();
                if (getRemark.isEmpty())
                {
                    et_remarks.setError( "Enter a valid reason" );
                    et_remarks.requestFocus();
                }
                else
                    {
                   makeCancelOrderRequest(sale_id,getRemark) ;
                }
            }
        } );

        sale_id = getIntent().getStringExtra("sale_id");
        if (ConnectivityReceiver.isConnected()) {
            makeGetOrderDetailRequest(sale_id);
        } else {
            Toast.makeText(getApplicationContext(), "Network Issue", Toast.LENGTH_SHORT).show();
        }
        String placed_on = getIntent().getStringExtra("placedon");
        String time = getIntent().getStringExtra("time");
        String item = getIntent().getStringExtra("item");
        String amount = getIntent().getStringExtra("ammount");
        String stats = getIntent().getStringExtra("status");

        Mark_Delivered = (RelativeLayout) findViewById(R.id.btn_mark_delivered);
        Mark_Delivered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderDetail.this, GetSignature.class);
                intent.putExtra("sale", sale_id);
                startActivity(intent);

            }
        });

        if (stats.equals("0")) {
            tv_status.setText(getResources().getString(R.string.pending));
            relativetextstatus.setText(getResources().getString(R.string.pending));
            rel_status.setBackgroundColor( this.getResources().getColor(R.color.dark_gray));


        } else if (stats.equals("1")) {
            tv_status.setText(getResources().getString(R.string.confirm));
            tv_status.setTextColor( Color.BLACK );
            relativetextstatus.setText(getResources().getString(R.string.confirm));
            relativetextstatus.setTextColor( Color.BLACK );
            txt_date_type.setText( "Confirmed On :" );
            rel_status.setBackgroundColor(this.getResources().getColor(R.color.yelow));
        } else if (stats.equals("2")) {
            tv_status.setText(getResources().getString(R.string.outfordeliverd));
            relativetextstatus.setText(getResources().getString(R.string.outfordeliverd));

            rel_status.setBackgroundColor( this.getResources().getColor(R.color.text_color));
        }
        else if (stats.equals("3")) {
           tv_status.setText("Cancelled");
           relativetextstatus.setText("Cancelled");
            txt_date_type.setText( "Cancelled On :" );
            rel_status.setBackgroundColor(OrderDetail.this.getResources().getColor(R.color.color_3));
            tv_status.setTextColor(OrderDetail.this.getResources().getColor(R.color.color_3));

        }
        else if (stats.equals("4")) {
            tv_status.setText(getResources().getString(R.string.delivered));
            txt_date_type.setText( "Delivered On :" );
            Mark_Delivered.setVisibility( View.GONE);
            Mark_cancelled.setVisibility( View.GONE );
            relativetextstatus.setText(getResources().getString(R.string.delivered));
            rel_status.setBackgroundColor(this.getResources().getColor(R.color.add_cart_img));
        }
        else if (equals("5")) {
            tv_status.setText("Undelivered");
            //   holder.relativetextstatus.setText(activity.getResources().getString(R.string.delivered));
         tv_status.setTextColor(OrderDetail.this.getResources().getColor(R.color.color_1));
            //    holder.rel_status.setBackgroundColor( activity.getResources().getColor(R.color.add_cart_img));

        }

        tv_orderno.setText(sale_id);
        tv_date.setText(placed_on);
        tv_time.setText(time);
        tv_item.setText(item);
        tv_tracking_date.setText(placed_on);
        tv_price.setText(getResources().getString(R.string.currency) + amount);


    }

    private void makeGetOrderDetailRequest(String sale_id) {
        String tag_json_obj = "json_order_detail_req";
        Map<String, String> params = new HashMap<String, String>();
        params.put("sale_id", sale_id);

        CustomVolleyJsonArrayRequest jsonObjReq = new CustomVolleyJsonArrayRequest( Request.Method.POST,
                BaseURL.OrderDetail, params, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<My_order_detail_model>>() {
                }.getType();

                my_order_detail_modelList = gson.fromJson(response.toString(), listType);

                My_order_detail_adapter adapter = new My_order_detail_adapter(my_order_detail_modelList);
                rv_detail_order.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                if (my_order_detail_modelList.isEmpty()) {
                    Toast.makeText(OrderDetail.this, getResources().getString(R.string.no_rcord_found), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // VolleyLog.d(TAG, "Error: " + error.getMessage());
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(OrderDetail.this, getResources().getString(R.string.connection_time_out), Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }

    private void makeCancelOrderRequest(String sale_id, String remark) {

        // Tag used to cancel the request
        String tag_json_obj = "json_delete_order_req";
        progressDialog.show();

        Map<String, String> params = new HashMap<String, String>();
        params.put("sale_id", sale_id);
        params.put("remark",remark);

        CustomVolleyJsonRequest jsonObjReq = new CustomVolleyJsonRequest(Request.Method.POST,
                BaseURL.CancelOrder, params, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("cancel order", response.toString());

                try {
                    Boolean status = response.getBoolean("responce");
                    if (status) {

                        String msg = response.getString("message");
                        Toast.makeText(OrderDetail.this, "" + msg, Toast.LENGTH_SHORT).show();

                        Intent intent=new Intent(OrderDetail.this,MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                     finish();


                    } else {
                        String error = response.getString("error");
                        Toast.makeText(OrderDetail.this, "" + error, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                VolleyLog.d("Cancel Order", "Error: " + error.getMessage());
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(OrderDetail.this, getResources().getString(R.string.connection_time_out), Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }


}
