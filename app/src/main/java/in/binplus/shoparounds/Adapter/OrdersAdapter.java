package in.binplus.shoparounds.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.binplus.shoparounds.Fragment.OrderDeatailsFragment;
import in.binplus.shoparounds.Models.OrderModel;
import in.binplus.shoparounds.R;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {
    ArrayList<OrderModel> orderList = new ArrayList<>(  );
    Activity activity ;

    public OrdersAdapter(ArrayList<OrderModel> orderList, Activity activity) {
        this.orderList = orderList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from( activity ).inflate( R.layout.row_orders,null);
       ViewHolder holder = new ViewHolder( view );
       return  holder ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final OrderModel olist = orderList.get( position );
        holder.order_id.setText( olist.getSale_id() );
        holder.user_name.setText( olist.getRecivers_name() );
        holder.payment_method.setText( olist.getPayment_method() );
        holder.order_date_time.setText(olist.getOn_date());
        holder.order_total.setText(olist.getTotal_amount());
       // holder.vendor_name.setText( olist.get );
        int status = Integer.parseInt( olist.getStatus() );
        if (status ==0)
        {
            holder.order_status.setText("pending");
            holder.order_status.setTextColor( Color.BLUE );
        }
       else if (status ==1)
        {
            holder.order_status.setText("confirmed");
            holder.order_status.setTextColor( Color.GREEN );
        }
       else if (status ==2)
        {
            holder.order_status.setText("out for delivery");
            holder.order_status.setTextColor( Color.CYAN );
        }
       else if (status ==3)
        {
            holder.order_status.setText("cancelled");
            holder.order_status.setTextColor( Color.DKGRAY );
        }
       else if (status ==4)
        {
            holder.order_status.setText( "Delivered" );
            holder.order_status.setTextColor( Color.RED );
        }
       int ispaid = Integer.parseInt( olist.getIs_paid() );
       if (ispaid == 0)
       {
           holder.payment.setText( "Unpaid" );

       }
       else if (ispaid ==1)
       {
           holder.payment.setText( "paid" );
       }

       holder.card_orders.setOnClickListener( new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               OrderDeatailsFragment fm = new OrderDeatailsFragment();
               AppCompatActivity activity = (AppCompatActivity) view.getContext();
               Bundle args = new Bundle();
               args.putString( "sale_id",olist.getSale_id() );
               args.putString( "user_id",olist.getUser_id() );
               args.putString( "on_date",olist.getOn_date() );
               args.putString( "delivery_time_from",olist.getDelivery_time_from() );
               args.putString( "delivery_time_to",olist.getDelivery_time_to() );
               args.putString( "status",olist.getStatus() );
               args.putString( "note",olist.getNote() );
               args.putString( "is_paid",olist.getIs_paid() );
               args.putString( "total_amount",olist.getTotal_amount() );
               args.putString( "total_rewards",olist.getTotal_rewards() );
               args.putString( "total_kg",olist.getTotal_kg() );
               args.putString( "total_items",olist.getTotal_items() );
               args.putString( "socity_id",olist.getSociety_id() );
               args.putString( "delivery_address",olist.getDelivery_address() );
               args.putString( "location_id",olist.getLocation_id() );
               args.putString( "delivery_charge",olist.getDelivery_charge() );
               args.putString( "new_store_id",olist.getNew_store_id() );
               args.putString( "delivery_type",olist.getDelivery_type() );
               args.putString( "assign_to",olist.getAssign_to() );
               args.putString( "payment_method",olist.getPayment_method() );
               args.putString( "socity_name",olist.getSociety_id() );
               args.putString( "pincode",olist.getPincode() );
               args.putString( "house_no",olist.getHouse_no() );
               args.putString( "receiver_name" ,olist.getRecivers_name());
               args.putString( "receiver_mobile",olist.getReciver_mobile() );
                fm.setArguments( args );
              FragmentManager fragmentManager = activity.getSupportFragmentManager();
               fragmentManager.beginTransaction().replace( R.id.frame,fm )
                       .addToBackStack( null ).commit();
           }
       } );

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView order_id ,order_total,order_date_time ,vendor_name , user_name ,order_status ,payment_method , payment;
        CardView card_orders;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            order_id = itemView.findViewById( R.id.order_id );
            order_date_time=itemView.findViewById( R.id.order_time );
            order_total=itemView.findViewById( R.id.order_total );
            vendor_name=itemView.findViewById( R.id.vendor_name );
            user_name=itemView.findViewById( R.id.user_name );

            order_status=itemView.findViewById( R.id.order_status );
            card_orders = itemView.findViewById( R.id.card_orders );
            payment_method=itemView.findViewById( R.id.order_payment );
            payment = itemView.findViewById( R.id.paid );

        }
    }
}
