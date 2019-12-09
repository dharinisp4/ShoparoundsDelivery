package in.binplus.shoparounds.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import in.binplus.shoparounds.Config.BaseURL;
import in.binplus.shoparounds.Models.OrderItemModel;
import in.binplus.shoparounds.Module.Module;
import in.binplus.shoparounds.R;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.ViewHolder> {
       List<OrderItemModel> itemList ;
        Activity activity ;

        Module module;

    public OrderItemAdapter(List<OrderItemModel> itemList, Activity activity) {
        this.itemList = itemList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from( activity ).inflate( R.layout.row_order_items,null );
        ViewHolder holder = new ViewHolder( view );
        return  holder ;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final OrderItemModel model = itemList.get( position );
        holder.item_name.setText( model.getProduct_name() );
        holder.item_price.setText( model.getPrice() );
        holder.item_qty.setText( model.getQty() );
        double price = Double.parseDouble( model.getPrice() );
        double qty =Double.parseDouble( model.getQty() );
     //   double qty =Double.parseDouble( model.getQty() );
        double total = price*qty ;
        holder.item_total.setText( String.valueOf( total ));
   // holder.vendor_name.setText( model.getUser_fullname() );

        Glide.with(activity)
                .load(BaseURL.IMG_PRODUCT_URL + model.getProduct_image())

                .placeholder(R.drawable.iconn)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(holder.item_img);

        holder.btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog=new Dialog(activity);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_pickup_details);
                ImageView vendor_img=(ImageView)dialog.findViewById(R.id.vendor_img);
                TextView txt_name=(TextView)dialog.findViewById(R.id.txt_name);
                TextView txt_number=(TextView)dialog.findViewById(R.id.txt_number);
                TextView txt_email=(TextView)dialog.findViewById(R.id.txt_email);
                TextView txt_address=(TextView)dialog.findViewById(R.id.txt_address);
                TextView txt_city=(TextView)dialog.findViewById(R.id.txt_city);
                TextView txt_pincode=(TextView)dialog.findViewById(R.id.txt_pincode);
                Button btnOk=(Button)dialog.findViewById(R.id.btnOK);
                Button btn_call=(Button)dialog.findViewById(R.id.btn_call);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

                Glide.with(activity)
                        .load(BaseURL.IMG_PROFILE_URL + model.getUser_image())
                         .placeholder(R.drawable.iconn)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                        .into(vendor_img);
                txt_name.setText(model.getUser_name().toUpperCase());
                txt_number.setText(model.getUser_phone());
                txt_email.setText(model.getUser_email());
                txt_address.setText(model.getUser_address());
                txt_city.setText(model.getCity_name());
                txt_pincode.setText(model.getUser_pincode());
                btn_call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent=new Intent(Intent.ACTION_DIAL);
                        String number=model.getUser_phone().toString().trim();
                        intent.setData(Uri.parse("tel: "+number));
                        activity.startActivity(intent);
                    }
                });

                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });



    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_name , item_price ,item_qty ,item_total;
        //TextView vendor_name ,vendor_add,vendor_mobile ;
        ImageView item_img;
        Button btn_dialog;
        public ViewHolder(@NonNull View itemView) {
            super( itemView );

            module=new Module(activity);
            item_name =itemView.findViewById( R.id.item_name );
            item_price=itemView.findViewById( R.id.item_mrp );
            item_total=itemView.findViewById( R.id.item_total );
            item_qty=itemView.findViewById( R.id.item_qty );
//            vendor_name = itemView.findViewById( R.id.vendor_name );
//            vendor_add=itemView.findViewById( R.id.vendor_add );
//            vendor_mobile=itemView.findViewById( R.id.vendor_mobile );
            item_img=itemView.findViewById( R.id.item_img);
            btn_dialog=itemView.findViewById( R.id.btn_dialog);


        }
    }
}
