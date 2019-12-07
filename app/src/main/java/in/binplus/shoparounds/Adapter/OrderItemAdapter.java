package in.binplus.shoparounds.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.binplus.shoparounds.Models.OrderItemModel;
import in.binplus.shoparounds.R;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.ViewHolder> {
        ArrayList<OrderItemModel> itemList ;
        Activity activity ;
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


    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_name , item_price ,item_qty ,item_total;
        TextView vendor_name ,vendor_add,vendor_mobile ;
        public ViewHolder(@NonNull View itemView) {
            super( itemView );

            item_name =itemView.findViewById( R.id.item_name );
            item_price=itemView.findViewById( R.id.item_mrp );
            item_total=itemView.findViewById( R.id.item_total );
            item_qty=itemView.findViewById( R.id.item_qty );
            vendor_name = itemView.findViewById( R.id.vendor_name );
            vendor_add=itemView.findViewById( R.id.vendor_add );
            vendor_mobile=itemView.findViewById( R.id.vendor_mobile );

        }
    }
}
