package in.binplus.shoparounds.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


import java.util.List;

import in.binplus.shoparounds.Config.BaseURL;
import in.binplus.shoparounds.Models.My_order_detail_model;
import in.binplus.shoparounds.R;


public class My_order_detail_adapter extends RecyclerView.Adapter<My_order_detail_adapter.MyViewHolder> {

    private List<My_order_detail_model> modelList;
    private Context context;
    private Activity activity ;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_title, tv_price, tv_qty;
        public ImageView iv_img;
        Button btn_dialog;

        public MyViewHolder(View view) {
            super(view);
            tv_title = (TextView) view.findViewById( R.id.tv_order_Detail_title);
            tv_price = (TextView) view.findViewById(R.id.tv_order_Detail_price);
            tv_qty = (TextView) view.findViewById(R.id.tv_order_Detail_qty);
            iv_img = (ImageView) view.findViewById(R.id.iv_order_detail_img);
            btn_dialog=itemView.findViewById( R.id.btn_dialog);
        }
    }

    public My_order_detail_adapter(List<My_order_detail_model> modelList) {
        this.modelList = modelList;
    }

    @Override
    public My_order_detail_adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_my_order_detail_rv, parent, false);

        context = parent.getContext();

        return new My_order_detail_adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(My_order_detail_adapter.MyViewHolder holder, int position) {
        final My_order_detail_model mList = modelList.get(position);

        Glide.with(context)
                .load( BaseURL.IMG_PRODUCT_URL + mList.getProduct_image())
                .centerCrop()
                .placeholder(R.drawable.iconn)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(holder.iv_img);

        holder.tv_title.setText(mList.getProduct_name());
        holder.tv_price.setText(mList.getPrice());
        holder.tv_qty.setText(mList.getQty());
        holder.btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog=new Dialog(context);
                dialog.requestWindowFeature( Window.FEATURE_NO_TITLE);
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

                Glide.with(context)
                        .load(BaseURL.IMG_PROFILE_URL + mList.getUser_image())
                        .placeholder(R.drawable.iconn)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                        .into(vendor_img);
                txt_name.setText(mList.getUser_name().toUpperCase());
                txt_number.setText(mList.getUser_phone());
                txt_email.setText(mList.getUser_email());
                txt_address.setText(mList.getUser_address());
                txt_city.setText(mList.getCity_name());
                txt_pincode.setText(mList.getUser_pincode());
                btn_call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent=new Intent(Intent.ACTION_DIAL);
                        String number=mList.getUser_phone().toString().trim();
                        intent.setData( Uri.parse("tel: "+number));
                        context.startActivity(intent);
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
        return modelList.size();
    }

}