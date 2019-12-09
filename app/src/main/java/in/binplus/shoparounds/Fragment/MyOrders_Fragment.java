package in.binplus.shoparounds.Fragment;


import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import in.binplus.shoparounds.Adapter.OrdersAdapter;
import in.binplus.shoparounds.AppController;
import in.binplus.shoparounds.Config.BaseURL;
import in.binplus.shoparounds.Models.OrderModel;
import in.binplus.shoparounds.R;
import in.binplus.shoparounds.util.CustomVolleyJsonArrayRequest;
import in.binplus.shoparounds.util.Session_management;

import static in.binplus.shoparounds.Config.BaseURL.KEY_ID;
import static in.binplus.shoparounds.Config.BaseURL.KEY_USER_ID;


public class MyOrders_Fragment extends Fragment {
    TextView order_id ,order_amt,order_date,order_time , vendor_name ,vendor_add,vendor_mobile ,user_name,user_add,user_mobile ;
    TextView heading ,recent ;
    CardView card_lastorder ;
    RelativeLayout rel_lastorder ;
    RecyclerView rv_orders ;
    Session_management session ;
    String user_id ,id ;
    OrdersAdapter ordersAdapter ;

    String type;
    String date;

    public MyOrders_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_my_orders, container, false );
        order_id = view.findViewById( R.id.order_id );
        order_amt=view.findViewById( R.id.order_amount );
        order_date=view.findViewById( R.id.delivery_date);
        order_time =view.findViewById( R.id.delivery_time );
        vendor_name =view.findViewById( R.id.vendor_name );
        vendor_add=view.findViewById( R.id.vendor_add );
        vendor_mobile=view.findViewById( R.id.vendor_mobile );
        user_name = view.findViewById( R.id.user_name );
        user_add=view.findViewById( R.id.user_add );
        user_mobile=view.findViewById( R.id.user_mobile );
        type = getArguments().getString( "type" );
        heading = view.findViewById( R.id.heading );
        recent =view.findViewById( R.id.recent );
        rv_orders=view.findViewById( R.id.orders_recycler );
        session = new Session_management( getActivity() );

        user_id = session.getUserDetails().get( KEY_USER_ID );

        id = session.getUserDetails().get( KEY_ID );
        date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getActivity(),LinearLayoutManager.VERTICAL,false );
        rv_orders.setLayoutManager( linearLayoutManager );
        rv_orders.setNestedScrollingEnabled( false );

       // Toast.makeText( getActivity(),"" +type +"" +id +date,Toast.LENGTH_LONG).show();
        if(type.equalsIgnoreCase( "upcoming" ))
        {
            heading.setText( "upcoming Orders:" );
            recent.setText( "Next Order" );
        }
        else if (type.equalsIgnoreCase( "all" ))
        {
            heading.setText( "Your Orders :" );
            recent.setText( "Last Order" );
        }
        else if( type.equalsIgnoreCase( "todays" ))
        {
            heading.setText( "Today's Orders" );
            recent.setText( "last Order" );
        }
        else if( type.equalsIgnoreCase( "delivered" ))
        {
            heading.setText( "Delivered Orders" );
            recent.setText( "last Delivered Order" );
        }

        getOrders( id );
        return view ;
    }

    private void getOrders( String id  )
    {
        HashMap<String ,String> params = new HashMap<>(  );
        params.put( "d_id",id );

        CustomVolleyJsonArrayRequest jsonArrayRequest = new CustomVolleyJsonArrayRequest( Request.Method.POST, BaseURL.URL_GET_ORDERS, params,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("on_date",response.toString());
                        ArrayList<OrderModel> alllist = new ArrayList<>(  );
                        ArrayList<OrderModel> pending_list = new ArrayList<>(  );
                        ArrayList<OrderModel> delivered_list= new ArrayList<>(  );
                        ArrayList<OrderModel> upcoming_list = new ArrayList<>(  );
                        ArrayList<OrderModel> today_list = new ArrayList<>(  );



                        try {
               //             Toast.makeText( getActivity(),""+response.toString(),Toast.LENGTH_LONG ).show();
                            for (int i =0 ;i<response.length();i++) {
                                JSONObject object = response.getJSONObject( i );
                                OrderModel model = new OrderModel(  );
                                model.setSale_id( object.getString( "sale_id" ) );
                                model.setUser_id( object.getString( "user_id" ) );
                                model.setOn_date( object.getString( "on_date" ) );
                                model.setDelivery_time_from( object.getString( "delivery_time_from" ) );
                                model.setDelivery_time_to( object.getString( "delivery_time_to" ) );
                                model.setStatus( object.getString( "status" ) );
                                model.setNote( object.getString( "note" ) );
                                model.setIs_paid( object.getString( "is_paid" ) );
                                model.setTotal_amount( object.getString( "total_amount" ) );
                                model.setTotal_rewards( object.getString( "total_rewards" ) );
                                model.setTotal_kg( object.getString( "total_kg" ) );
                                model.setTotal_items( object.getString( "total_items" ) );
                                model.setSociety_id( object.getString( "socity_id" ) );
                                model.setDelivery_address( object.getString( "delivery_address" ) );
                                model.setLocation_id( object.getString( "location_id") );
                                model.setNew_store_id( object.getString( "new_store_id" ) );
                                model.setDelivery_type( object.getString( "delivery_type" ) );
                                model.setAssign_to( object.getString( "assign_to" ) );
                                model.setPayment_method( object.getString( "payment_method" ) );
                                model.setSociety_name( object.getString( "socity_name" ) );
                                model.setPincode( object.getString( "pincode" ) );
                                model.setHouse_no( object.getString( "house_no" ) );
                                model.setRecivers_name( object.getString( "receiver_name" ) );
                                model.setReciver_mobile( object.getString( "receiver_mobile" ) );

                                alllist.add( model );
                              boolean sts=compareDate(object.get( "on_date" ).toString());
                                int status = Integer.parseInt( object.getString( "status" ) );
                                if (status == 2)
                                {
                                   upcoming_list.add( model );
                                }
                                else if (status ==4 )
                                {
                                    delivered_list.add( model );
                                }
                                if (sts==true)
                                {

                                    today_list.add( model );
                                }

                               // Toast.makeText(getActivity(),""+compareDate(object.get( "on_date" ).toString()),Toast.LENGTH_LONG).show();


                            }
                            if(type.equalsIgnoreCase( "upcoming" ))
                            {
                                ordersAdapter = new OrdersAdapter( upcoming_list,getActivity());
                                rv_orders.setAdapter( ordersAdapter );
                                ordersAdapter.notifyDataSetChanged();
                            }
                            else if (type.equalsIgnoreCase( "all" ))
                            {
                                ordersAdapter = new OrdersAdapter( alllist,getActivity());
                                rv_orders.setAdapter( ordersAdapter );
                                ordersAdapter.notifyDataSetChanged();
                            }
                            else if( type.equalsIgnoreCase( "todays" ))
                            {
                                //Toast.makeText(getActivity(),""+today_list.size(),Toast.LENGTH_LONG).show();
                                ordersAdapter = new OrdersAdapter( today_list,getActivity());
                                rv_orders.setAdapter( ordersAdapter );
                                ordersAdapter.notifyDataSetChanged();
                            }
                            else if( type.equalsIgnoreCase( "delivered" ))
                            {
                                ordersAdapter = new OrdersAdapter( delivered_list,getActivity());
                                rv_orders.setAdapter( ordersAdapter );
                                ordersAdapter.notifyDataSetChanged();
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText( getActivity(),error.getMessage(),Toast.LENGTH_LONG ).show();

            }
        } );

       AppController.getInstance().addToRequestQueue( jsonArrayRequest,"orders_tag" );

    }

    public boolean compareDate(String order_date)
    {
        boolean st=false;
        try
        {
            Date date=new Date();

            SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd");
            String cdt=format1.format(date);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = sdf.parse(cdt);
            Date date2 = sdf.parse(order_date);
            if (date1.compareTo(date2) == 0) {
                st=true;
            }
            else
            {
                st=false;
            }
        }
        catch (Exception ex)
        {
           Toast.makeText(getActivity(),""+ex.getMessage(),Toast.LENGTH_LONG).show();

        }

        return st;
    }
}
