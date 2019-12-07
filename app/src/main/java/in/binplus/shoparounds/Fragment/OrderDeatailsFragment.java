package in.binplus.shoparounds.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import in.binplus.shoparounds.AppController;
import in.binplus.shoparounds.Config.BaseURL;
import in.binplus.shoparounds.Models.OrderItemModel;
import in.binplus.shoparounds.R;
import in.binplus.shoparounds.util.CustomVolleyJsonArrayRequest;
import in.binplus.shoparounds.util.CustomVolleyJsonRequest;
import in.binplus.shoparounds.util.Session_management;

import static in.binplus.shoparounds.Config.BaseURL.KEY_ID;


public class OrderDeatailsFragment extends Fragment {
    TextView order_id, order_total ,order_time , order_items ,order_payment ,order_status ;
    TextView user_name , user_add ,user_mobile ;
    TextView remarks ;
    RecyclerView rv_items ;

    String sale_id,user_id,on_date,time_from,time_to,status,note,is_paid,tot_amt,tot_rewrds,tot_kg,tot_items,society_id,del_add,location_id;
         String  del_charge, new_store,deliver_type,assign_to,payment_method,socity_name,pincode,house_no,r_name,r_no;

    String id ;
    Session_management session ;
    public OrderDeatailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate( R.layout.fragment_order_details, container, false );
       order_id = view.findViewById( R.id.order_id );
       order_total = view.findViewById( R.id.order_total );
       order_time = view.findViewById( R.id.order_time );
       order_items=view.findViewById( R.id.order_items );
       order_payment=view.findViewById( R.id.order_payment );
       order_status = view.findViewById( R.id.order_status);
       user_name =view.findViewById( R.id.user_name );
       user_add =view.findViewById( R.id.user_add );
       user_mobile =view.findViewById( R.id.user_mobile );
       remarks =view.findViewById( R.id.tv_remarks );
       rv_items = view.findViewById( R.id.rv_order_items );
       session = new Session_management( getActivity() );
       id = session.getUserDetails().get( KEY_ID );

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getActivity(),LinearLayoutManager.VERTICAL,false );
        rv_items.setNestedScrollingEnabled( false );
        rv_items.setLayoutManager( linearLayoutManager );
       sale_id= String.valueOf( getArguments().get( "sale_id") );
      user_id= String.valueOf( getArguments().get( "user_id" ) );
       on_date= String.valueOf( getArguments().get( "on_date" ) );
      time_from= String.valueOf( getArguments().get( "delivery_time_from" ) );
      time_to= String.valueOf( getArguments().get( "delivery_time_to" ) );
       status= String.valueOf( getArguments().get( "status") );
      note= String.valueOf( getArguments().get( "note" ) );
      is_paid= String.valueOf( getArguments().get( "is_paid" ) );
      tot_amt= String.valueOf( getArguments().get( "total_amount" ) );
      tot_rewrds= String.valueOf( getArguments().get( "total_rewards" ) );
      tot_kg= String.valueOf( getArguments().get( "total_kg" ) );
       tot_items= String.valueOf( getArguments().get( "total_items" ) );
      society_id= String.valueOf( getArguments().get( "socity_id" ) );
        del_add= String.valueOf( getArguments().get( "delivery_address" ) );
        location_id= String.valueOf( getArguments().get( "location_id" ) );
        del_charge= String.valueOf( getArguments().get( "delivery_charge" ) );
        new_store= String.valueOf( getArguments().get( "new_store_id" ) );
        deliver_type= String.valueOf( getArguments().get( "delivery_type" ) );
        assign_to= String.valueOf( getArguments().get( "assign_to" ) );
        payment_method= String.valueOf( getArguments().get( "payment_method" ) );
        socity_name= String.valueOf( getArguments().get( "socity_name" ) );
        pincode= String.valueOf( getArguments().get( "pincode" ) );
        house_no= String.valueOf( getArguments().get( "house_no" ) );
        r_name= String.valueOf( getArguments().get( "receiver_name" ) );
        r_no= String.valueOf( getArguments().get( "receiver_mobile") );

        order_id.setText( sale_id );
        order_total.setText( tot_amt );
        order_time.setText( on_date + time_to );
        order_items.setText( tot_items );

        order_payment.setText( payment_method );
        user_name.setText( r_name );
        user_mobile.setText( r_no );
        user_add.setText( del_add + "\n" +pincode );
        remarks.setText( note );

        int s = Integer.parseInt( status);
        if (s ==0)
        {
           order_status.setText("pending");
        }
        else if (s ==1)
        {
           order_status.setText("confirmed");
        }
        else if (s ==2)
        {
            order_status.setText("out for delivery");
        }
        else if (s ==3)
        {
            order_status.setText("cancelled");
        }
        else if (s ==4)
        {
           order_status.setText( "Delivered" );
        }
        getItems( sale_id);
       return  view ;
    }

        public  void getItems(String order_id )
        {
            HashMap<String,String> params = new HashMap<>(  );
            params.put( "sale_id",order_id );

            String json_tag ="order_items";

            CustomVolleyJsonArrayRequest jsonArrayRequest = new CustomVolleyJsonArrayRequest( Request.Method.POST, BaseURL.URL_GET_ORDERS_ITEMS, params,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            ArrayList<OrderItemModel> itemlist = new ArrayList<>(  );

                            for (int i = 0 ;i<response.length();i++)
                            {
                                JsonObject object = new JsonObject();

                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    } );

        }
}
