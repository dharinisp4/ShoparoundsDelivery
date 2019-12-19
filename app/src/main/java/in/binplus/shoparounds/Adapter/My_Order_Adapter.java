package in.binplus.shoparounds.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.graphics.Color;
import android.opengl.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.binplus.shoparounds.Models.OrderModel;
import in.binplus.shoparounds.OrderDetail;
import in.binplus.shoparounds.R;

import static android.content.Context.MODE_PRIVATE;



public class My_Order_Adapter extends RecyclerView.Adapter<My_Order_Adapter.MyViewHolder> {

    private List<OrderModel> modelList;
    private LayoutInflater inflater;
    private Fragment currentFragment;
SharedPreferences preferences;

    Activity activity ;

    public My_Order_Adapter(List<OrderModel> modelList, Activity activity) {
        this.modelList = modelList;
        this.activity = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_orderno, tv_status, tv_date, tv_time, tv_price, tv_item, relativetextstatus, tv_tracking_date,tv_socity,
                tv_recivername,tv_recivernumber,tv_house ,txt_note ,txt_date_type;
        LinearLayout cardView ,linear_note ,linear_date;
         RelativeLayout rel_status;
        public MyViewHolder(View view) {
            super(view);
            tv_orderno = (TextView) view.findViewById( R.id.tv_order_no);
            tv_status = (TextView) view.findViewById(R.id.tv_order_status);
            relativetextstatus = (TextView) view.findViewById(R.id.status);
            tv_tracking_date = (TextView) view.findViewById(R.id.tracking_date);
            tv_date = (TextView) view.findViewById(R.id.tv_order_date);
            tv_time = (TextView) view.findViewById(R.id.tv_order_time);
            tv_price = (TextView) view.findViewById(R.id.tv_order_price);
            tv_item = (TextView) view.findViewById(R.id.tv_order_item);
            tv_socity=view.findViewById(R.id.tv_societyname);
            tv_house=view.findViewById(R.id.tv_house);
            tv_recivername=view.findViewById(R.id.tv_recivername);
            tv_recivernumber=view.findViewById(R.id.tv_recivernmobile);
            linear_note = view.findViewById( R.id.linear_note );
            txt_note = view.findViewById( R.id.txt_Note );
            txt_date_type = view.findViewById( R.id.txt_date_type );
            linear_date = view.findViewById( R.id.linear_date );


            cardView = view.findViewById(R.id.card_view);
            rel_status = (RelativeLayout) view.findViewById(R.id.rel_status);
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = getAdapterPosition();
//                    if (position != RecyclerView.NO_POSITION) {
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
//                        Intent intent = new Intent(context, OrderDetail.class);
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
//                        context.startActivity(intent);
////
//                    }
//                }
//            });


        }
    }



    @Override
    public My_Order_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(activity).inflate(R.layout.row_order_rv, parent, false);


           return new My_Order_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(My_Order_Adapter.MyViewHolder holder, int position) {
       final OrderModel mList = modelList.get(position);


        try {
            holder.tv_orderno.setText(mList.getSale_id());

            if (mList.getStatus().equals("0")) {
                holder.tv_status.setText(activity.getResources().getString(R.string.pending));
                holder.relativetextstatus.setText(activity.getResources().getString(R.string.pending));
                holder.rel_status.setBackgroundColor( activity.getResources().getColor(R.color.dark_gray));
                holder.tv_status.setTextColor(activity.getResources().getColor(R.color.dark_gray));
                holder.txt_date_type.setText( "Placed on :" );
                holder.tv_tracking_date.setText(mList.getPlaced_date());
            }
            else if (mList.getStatus().equals("1")) {
                holder.tv_status.setText(activity.getResources().getString(R.string.confirm));
                holder.tv_status.setTextColor(activity.getResources().getColor( R.color.orange )  );
                holder.txt_date_type.setText( "Order Confirmed on :" );
                holder.tv_tracking_date.setText(mList.getConfirm_date());
//                holder.relativetextstatus.setText(activity.getResources().getString(R.string.confirm));
//                holder.tv_status.setTextColor( Color.BLACK );
//                holder.relativetextstatus.setTextColor( Color.BLACK );
//                holder.rel_status.setBackgroundColor(activity.getResources().getColor(R.color.yelow));
//                holder.tv_status.setTextColor(activity.getResources().getColor(R.color.yelow));
            }

            else if (mList.getStatus().equals("2")) {
                holder.tv_status.setText("Out for Delivery");
                holder.tv_status.setTextColor(activity.getResources().getColor(R.color.text_color));
                holder.txt_date_type.setText( "Out for delivery on :" );
                holder.tv_tracking_date.setText(mList.getOut_date());
//                holder.relativetextstatus.setText(activity.getResources().getString(R.string.outfordeliverd));
//
//                holder.rel_status.setBackgroundColor( activity.getResources().getColor(R.color.text_color) );
            }
            else if (mList.getStatus().equals("3")) {
                holder.tv_status.setText("Cancelled");
                holder.tv_status.setTextColor(activity.getResources().getColor(R.color.color_3));
              //  holder.txt_date_type.setText( "Order Cancelled on :" );
                holder.linear_date.setVisibility( View.GONE );

             //   holder.tv_tracking_date.setText(mList.getOn_date());
//                holder.relativetextstatus.setText("Cancelled");
//
//                holder.rel_status.setBackgroundColor(activity.getResources().getColor(R.color.color_3));

                holder.cardView.setClickable( false );
                holder.cardView.setEnabled( false );
            }
            else if (mList.getStatus().equals("4")) {
                holder.tv_status.setText(activity.getResources().getString(R.string.delivered));
        //        holder.relativetextstatus.setText(activity.getResources().getString(R.string.delivered));
                holder.tv_status.setTextColor(activity.getResources().getColor(R.color.add_cart_img));
                holder.txt_date_type.setText( "Order Delivered on :" );
                holder.tv_tracking_date.setText(mList.getDelivered_date());
          //      holder.rel_status.setBackgroundColor( activity.getResources().getColor(R.color.add_cart_img));
                if (!(mList.getNote().equals( "" )))
                {
                    holder.linear_note.setVisibility(View.VISIBLE);
                    holder.txt_note.setText(mList.getNote());
                }
                else
                {
                    holder.linear_note.setVisibility(View.GONE);
                }
            }
            else if (mList.getStatus().equals("5")) {
                holder.tv_status.setText("Undelivered");
             //   holder.relativetextstatus.setText(activity.getResources().getString(R.string.delivered));
                holder.tv_status.setTextColor(activity.getResources().getColor(R.color.color_1));
                holder.txt_date_type.setText( "Out for delivery on :" );
                holder.tv_tracking_date.setText(mList.getOut_date());
            //    holder.rel_status.setBackgroundColor( activity.getResources().getColor(R.color.add_cart_img));
                if (!(mList.getNote() ==""))
                {
                    holder.linear_note.setVisibility(View.VISIBLE);
                    holder.txt_note.setText(mList.getNote());

                }
                else
                {
                    holder.linear_note.setVisibility( View.GONE );
                }

            }

        } catch (Exception e) {

        }

            holder.tv_date.setText(mList.getOn_date());

     //   preferences = activity.getSharedPreferences("lan", MODE_PRIVATE);

            String timefrom=mList.getDelivery_time_from();
            String timeto=mList.getDelivery_time_to();
            String time=timefrom + "-" + timeto;

            holder.tv_time.setText(time);


       holder.tv_price.setText(activity.getResources().getString(R.string.currency)+ mList.getTotal_amount());
            holder.tv_item.setText(activity.getResources().getString(R.string.tv_cart_item) + mList.getTotal_items());
            holder.tv_socity.setText(mList.getSocityname());
            holder.tv_house.setText(mList.getHouse());
            holder.tv_recivername.setText(modelList.get(position).getRecivername());
            holder.tv_recivernumber.setText(mList.getRecivermobile());

            holder.cardView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    String saleid = mList.getSale_id();
                    String placedon =mList.getOn_date();
                    String time = mList.getDelivery_time_from() + "-" + mList.getDelivery_time_to();
                    String item = mList.getTotal_items();
                    String ammount =mList.getTotal_amount();
                    String status = mList.getStatus();
                    String society=mList.getSocityname();
                    String house=mList.getHouse();
                    String recivername=mList.getRecivername();
                    String recivermobile=mList.getRecivermobile();
                    Intent intent = new Intent(activity, OrderDetail.class);
                    intent.putExtra("sale_id", saleid);
                    intent.putExtra("placedon", placedon);
                    intent.putExtra("time", time);
                    intent.putExtra("item", item);
                    intent.putExtra("ammount", ammount);
                    intent.putExtra("status", status);
                    intent.putExtra("socity_name",society);
                    intent.putExtra("house_no",house);
                    intent.putExtra("receiver_name",recivername);
                    intent.putExtra("receiver_mobile",recivermobile);
                  //  Toast.makeText( activity,"order detail" +ammount,Toast.LENGTH_LONG ).show();
                    activity.startActivity(intent);
                }
            } );

//        holder.rel_status.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                try
//                {
//                    Intent intent=new Intent( activity,OrderDetail.class );
//                    activity.startActivity( intent );
//
//                }
//                catch (Exception ex)
//                {
//                    Toast.makeText( activity,""+ex.getMessage(),Toast.LENGTH_LONG ).show();
//                }
//
//            }
//        } );
        }



    @Override
    public int getItemCount() {
        return modelList.size();

    }

}
