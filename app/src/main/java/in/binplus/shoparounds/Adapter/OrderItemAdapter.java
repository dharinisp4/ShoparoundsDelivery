package in.binplus.shoparounds.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import in.binplus.shoparounds.R;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.ViewHolder> {
       List<OrderItemModel> itemList ;
        Activity activity ;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderItemModel model = itemList.get( position );
        holder.item_name.setText( model.getProduct_name() );
        holder.item_price.setText( model.getPrice() );
        holder.item_qty.setText( model.getQty() );
        double price = Double.parseDouble( model.getPrice() );
        double qty =Double.parseDouble( model.getQty() );
     //   double qty =Double.parseDouble( model.getQty() );
        double total = price*qty ;
        holder.item_total.setText( String.valueOf( total ));
        holder.vendor_name.setText( model.getSeller_id() );

        Glide.with(activity)
                .load(BaseURL.IMG_PRODUCT_URL + model.getProduct_img())
                .centerCrop()
                .placeholder(R.drawable.iconn)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(holder.item_img);


    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_name , item_price ,item_qty ,item_total;
        TextView vendor_name ,vendor_add,vendor_mobile ;
        ImageView item_img;
        public ViewHolder(@NonNull View itemView) {
            super( itemView );

            item_name =itemView.findViewById( R.id.item_name );
            item_price=itemView.findViewById( R.id.item_mrp );
            item_total=itemView.findViewById( R.id.item_total );
            item_qty=itemView.findViewById( R.id.item_qty );
            vendor_name = itemView.findViewById( R.id.vendor_name );
            vendor_add=itemView.findViewById( R.id.vendor_add );
            vendor_mobile=itemView.findViewById( R.id.vendor_mobile );
            item_img=itemView.findViewById( R.id.item_img);


        }
    }
}
