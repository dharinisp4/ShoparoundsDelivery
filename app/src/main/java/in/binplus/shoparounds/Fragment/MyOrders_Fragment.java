package in.binplus.shoparounds.Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import in.binplus.shoparounds.Adapter.My_Order_Adapter;
import in.binplus.shoparounds.Adapter.OrdersAdapter;
import in.binplus.shoparounds.AppController;
import in.binplus.shoparounds.Config.BaseURL;
import in.binplus.shoparounds.Models.OrderModel;
import in.binplus.shoparounds.OrderDetail;
import in.binplus.shoparounds.R;
import in.binplus.shoparounds.util.CustomVolleyJsonArrayRequest;
import in.binplus.shoparounds.util.RecyclerTouchListener;
import in.binplus.shoparounds.util.Session_management;

import static in.binplus.shoparounds.Config.BaseURL.KEY_ID;
import static in.binplus.shoparounds.Config.BaseURL.KEY_USER_ID;


public class MyOrders_Fragment extends Fragment {
    private static String TAG = MyOrders_Fragment.class.getSimpleName();
    private RecyclerView rv_myorder;
    private List<OrderModel> my_order_modelList = new ArrayList<>();
    private List<OrderModel> upcoming_list= new ArrayList<>();
    private List<OrderModel> delivered_list = new ArrayList<>();
    private List<OrderModel> todays_list = new ArrayList<>();

    private Session_management sessionManagement;
    String get_id;
    String type;
    String date ;
    My_Order_Adapter ordersAdapter ;

    public MyOrders_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_my_orders, container, false );
        sessionManagement = new Session_management(getActivity());
        get_id = sessionManagement.getUserDetails().get( KEY_ID);
        rv_myorder = (RecyclerView) view.findViewById(R.id.rv_myorder);
        rv_myorder.setLayoutManager(new LinearLayoutManager(getActivity()));
       // Toast.makeText( getActivity(),"id" +get_id,Toast.LENGTH_LONG ).show();

        type = getArguments().getString( "type" );
        date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        makeGetOrderRequest(get_id);

//        rv_myorder.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),rv_myorder, new RecyclerTouchListener.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(View view, int position) {
//
//
//                        String saleid = modelList.get(position).getSale_id();
//                        String placedon = modelList.get(position).getOn_date();
//                        String time = modelList.get(position).getDelivery_time_from() + "-" + modelList.get(position).getDelivery_time_to();
//                        String item = modelList.get(position).getTotal_items();
//                        String ammount = modelList.get(position).getTotal_amount();
//                        String status = modelList.get(position).getStatus();
//                        String society=modelList.get(position).getSocityname();
//                        String house=modelList.get(position).getHouse();
//                        String recivername=modelList.get(position).getRecivername();
//                        String recivermobile=modelList.get(position).getRecivermobile();
//                        Intent intent = new Intent(getActivity(), OrderDetail.class);
//                        intent.putExtra("sale_id", saleid);
//                        intent.putExtra("placedon", placedon);
//                        intent.putExtra("time", time);
//                        intent.putExtra("item", item);
//                        intent.putExtra("ammount", ammount);
//                        intent.putExtra("status", status);
//                        intent.putExtra("socity_name",society);
//                        intent.putExtra("house_no",house);
//                        intent.putExtra("receiver_name",recivername);
//                        intent.putExtra("receiver_mobile",recivermobile);
//                        getActivity().startActivity(intent);
//            }
//
//            @Override
//            public void onLongItemClick(View view, int position) {
//
//            }
//        }));

        return view;
    }

    private void makeGetOrderRequest( String id)
    {
        String tag_json_obj = "json_socity_req";
        Map<String, String> params = new HashMap<String, String>();
        params.put("d_id", id);

        CustomVolleyJsonArrayRequest jsonObjReq = new CustomVolleyJsonArrayRequest(Request.Method.POST,
                BaseURL.URL_GET_ORDERS, params, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                try {

                    for (int i = 0; i < response.length(); i++) {

                        JSONObject obj = response.getJSONObject(i);
                        String saleid = obj.getString("sale_id");
                        String placedon = obj.getString("on_date");
                        String timefrom = obj.getString("delivery_time_from");
                        String timeto=obj.getString("delivery_time_from");
                        String item = obj.getString("total_items");
                        String ammount = obj.getString("total_amount");
                        String status = obj.getString("status");

                        String society = obj.getString("socity_name");
                        String house = obj.getString("house_no");
                        String rename = obj.getString("receiver_name");
                        String renumber = obj.getString("receiver_mobile");
                        OrderModel my_order_model = new OrderModel();
                        my_order_model.setSocityname(society);
                        my_order_model.setHouse(house);
                        my_order_model.setRecivername(rename);
                        my_order_model.setRecivermobile(renumber);
                        my_order_model.setDelivery_time_from(timefrom);
                        my_order_model.setSale_id(saleid);
                        my_order_model.setOn_date(placedon);
                        my_order_model.setDelivery_time_to(timeto);
                        my_order_model.setTotal_amount(ammount);
                        my_order_model.setStatus(status);
                        my_order_model.setTotal_items(item);
                        my_order_modelList.add(my_order_model);
                        boolean sts=compareDate(obj.getString( "on_date" ));
                        int statuss = Integer.parseInt( status );
                        if (statuss == 2)
                        {
                            upcoming_list.add( my_order_model );
                        }
                        else if (statuss == 4 )
                        {
                            delivered_list.add( my_order_model );
                        }
                        if (sts==true)
                        {

                            todays_list.add(my_order_model );
                        }
                        if(type.equalsIgnoreCase( "upcoming" ))
                        {
                             ordersAdapter= new My_Order_Adapter( upcoming_list,getActivity());
                            rv_myorder.setAdapter( ordersAdapter );
                            ordersAdapter.notifyDataSetChanged();
                        }
                        else if (type.equalsIgnoreCase( "all" ))
                        {
                            ordersAdapter = new My_Order_Adapter( my_order_modelList,getActivity());
                            rv_myorder.setAdapter( ordersAdapter );
                            ordersAdapter.notifyDataSetChanged();
                        }
                        else if( type.equalsIgnoreCase( "todays" ))
                        {
                            //Toast.makeText(getActivity(),""+today_list.size(),Toast.LENGTH_LONG).show();
                            ordersAdapter = new My_Order_Adapter( todays_list,getActivity());
                            rv_myorder.setAdapter( ordersAdapter );
                            ordersAdapter.notifyDataSetChanged();
                        }
                        else if( type.equalsIgnoreCase( "delivered" ))
                        {
                            ordersAdapter = new My_Order_Adapter( delivered_list,getActivity());
                            rv_myorder.setAdapter( ordersAdapter );
                            ordersAdapter.notifyDataSetChanged();
                        }



                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(),"err:- "+e.getMessage(),Toast.LENGTH_LONG).show();
                }
                  if (my_order_modelList.isEmpty()) {
                    Toast.makeText(getActivity(), "No Record Found", Toast.LENGTH_SHORT).show();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());

                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.connection_time_out), Toast.LENGTH_SHORT).show();
                }
            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

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
