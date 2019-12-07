package in.binplus.shoparounds.Fragment;


import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import in.binplus.shoparounds.Adapter.OrdersAdapter;
import in.binplus.shoparounds.AppController;
import in.binplus.shoparounds.Config.BaseURL;
import in.binplus.shoparounds.Models.OrderModel;
import in.binplus.shoparounds.R;
import in.binplus.shoparounds.util.CustomVolleyJsonArrayRequest;
import in.binplus.shoparounds.util.Session_management;


public class HomeFragment extends Fragment implements View.OnClickListener {

CardView card_delivered , card_upcoming ,card_earnings ,card_allorders ,card_ongoing ;
RelativeLayout rel_delivered ,rel_upcoming ,rel_earnings,rel_allorders ;
TextView order_id ,order_amt , vendor_name ,vendor_add,vendor_mobile ,user_name,user_add,user_mobile ;
ProgressBar delivered_prog ,pending_prog ,cancelled_prog ;
Session_management session ;
TextView del_percent ,can_percent ,pending_per ,del_tot , cancel_tot,pending_tot ;
    ArrayList<OrderModel> alllist = new ArrayList<>(  );
    ArrayList<OrderModel> pending_list = new ArrayList<>(  );
    ArrayList<OrderModel> delivered_list= new ArrayList<>(  );
    ArrayList<OrderModel> cancelled_list = new ArrayList<>(  );
    ArrayList<OrderModel> today_list = new ArrayList<>(  );

String id ;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    View view= inflater.inflate( R.layout.fragment_home, container, false );

    session = new Session_management( getActivity() );
    card_delivered =view.findViewById( R.id.card_delivered );
    card_upcoming = view.findViewById( R.id.card_upcoming );
    card_earnings = view.findViewById( R.id.card_income );
    card_allorders=view.findViewById( R.id.card_total_orders );
    card_ongoing=view.findViewById( R.id.card_current_order );
    order_amt =view.findViewById( R.id.order_amount );
    order_id=view.findViewById( R.id.order_id );
    vendor_name=view.findViewById( R.id.vendor_name );
    vendor_add=view.findViewById( R.id.vendor_add );
    vendor_mobile=view.findViewById( R.id.vendor_mobile );
    user_add=view.findViewById( R.id.user_add );
    user_mobile=view.findViewById( R.id.user_mobile );
    user_name=view.findViewById(R.id.vendor_name  );

    delivered_prog=view.findViewById( R.id.delivered );
    del_percent=view.findViewById( R.id.deliveredp );
    del_tot =view.findViewById( R.id.totald );


    pending_prog=view.findViewById( R.id.pending );
    pending_per=view.findViewById( R.id.pendingp );
    pending_tot =view.findViewById( R.id.totalp );

    cancelled_prog =view.findViewById( R.id.cancelled );
    can_percent =view.findViewById( R.id.cancelledp );
    cancel_tot=view.findViewById( R.id.totalc );

    card_ongoing.setOnClickListener( this );
    card_allorders.setOnClickListener( this );
    card_upcoming.setOnClickListener( this );
    card_earnings.setOnClickListener( this );
    card_delivered.setOnClickListener( this );

    id =session.getUserDetails().get( "id" );
    getOrders( id );

    String totpending = String.valueOf( pending_list.size() );

    cancelled_prog.setProgress(getPercentage( cancelled_list.size(),alllist.size() ) );
    delivered_prog.setProgress( getPercentage( delivered_list.size(),alllist.size() ) );
    pending_prog.setProgress( getPercentage( pending_list.size(),alllist.size() ) );

   return  view ;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        androidx.fragment.app.Fragment fm = null;
        Bundle args = new Bundle();
        if (id==R.id.card_current_order)
        {
            fm= new OrderDeatailsFragment();


        }
        else if (id == R.id.card_upcoming)
        {
            fm = new MyOrders_Fragment();
            args.putString( "type","upcoming" );
            fm.setArguments(args);
        }
        else if (id == R.id.card_income)
        {
            fm = new MyTransaction_Fragment();
            args.putString( "type", "todays" );
            fm.setArguments( args );
        }
        else if (id == R.id.card_delivered)
        {
            fm = new MyOrders_Fragment();
            args.putString( "type","delivered" );
            fm.setArguments(args);
        }
        else if (id == R.id.card_total_orders)
        {
            fm = new MyOrders_Fragment();
            args.putString( "type","todays" );
            fm.setArguments(args);
        }

        if (fm != null) {
            FragmentManager fragmentManager =getFragmentManager() ;
            fragmentManager.beginTransaction().replace(R.id.frame, fm)
                    .addToBackStack(null).commit();
        }


    }
    private void getOrders( String id  )
    {
        HashMap<String ,String> params = new HashMap<>(  );
        params.put( "d_id",id );

        CustomVolleyJsonArrayRequest jsonArrayRequest = new CustomVolleyJsonArrayRequest( Request.Method.POST, BaseURL.URL_GET_ORDERS, params,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {




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

                                int status = Integer.parseInt( object.getString( "status" ) );
                                if (status == 2)
                                {
                                    pending_list.add( model );
                                }
                                else if (status ==4 )
                                {
                                    delivered_list.add( model );
                                }

                                else if (status == 3)
                                {
                                    cancelled_list.add( model );
                                }


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
    int getPercentage(int initial ,int finals)
    {
        int p = (initial/finals)*100 ;
        return p ;
    }
}
