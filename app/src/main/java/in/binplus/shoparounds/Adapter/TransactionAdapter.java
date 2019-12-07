package in.binplus.shoparounds.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.binplus.shoparounds.Models.TransactionModel;
import in.binplus.shoparounds.R;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    Activity activity;
    ArrayList<TransactionModel> transactionList ;

    public TransactionAdapter(Activity activity, ArrayList<TransactionModel> transactionList)
    {
        this.activity = activity;
        this.transactionList = transactionList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( activity ).inflate( R.layout.row_transaction ,null);
        ViewHolder holder = new ViewHolder( view );
        return  holder ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TransactionModel tmodel = transactionList.get( position );


    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView trans_date ,trans_amt ,trans_mode ;
        public ViewHolder(@NonNull View itemView) {
            super( itemView );
//            trans_date =itemView.findViewById( R.id.date_time );
//            trans_amt =itemView.findViewById( R.id.amount );
//            trans_mode=itemView.findViewById( R.id.mode );


        }
    }
}
