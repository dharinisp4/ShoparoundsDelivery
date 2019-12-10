package in.binplus.shoparounds.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.binplus.shoparounds.Models.NotificationModel;
import in.binplus.shoparounds.R;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    Activity activity;
    ArrayList<NotificationModel> list ;

    public NotificationAdapter(Activity activity, ArrayList<NotificationModel> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( activity ).inflate( R.layout.row_notification,null );
        ViewHolder holder = new ViewHolder( view );
        return holder ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NotificationModel mlis = list.get( position );

        holder.txt_date.setText( mlis.getDate() );
        holder.txt_msg.setText( mlis.getMessage() );

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_date ,txt_msg ;
        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            txt_date = itemView.findViewById( R.id.txt_date );
            txt_msg =itemView.findViewById( R.id.txt_notification );
        }
    }
}
