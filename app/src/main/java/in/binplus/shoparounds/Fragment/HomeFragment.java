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
import android.widget.RelativeLayout;
import android.widget.TextView;

import in.binplus.shoparounds.R;


public class HomeFragment extends Fragment implements View.OnClickListener {

CardView card_delivered , card_upcoming ,card_earnings ,card_allorders ,card_ongoing ;
RelativeLayout rel_delivered ,rel_upcoming ,rel_earnings,rel_allorders ;
TextView order_id ,order_amt , vendor_name ,vendor_add,vendor_mobile ,user_name,user_add,user_mobile ;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    View view= inflater.inflate( R.layout.fragment_home, container, false );
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
    card_ongoing.setOnClickListener( this );
    card_allorders.setOnClickListener( this );
    card_upcoming.setOnClickListener( this );
    card_earnings.setOnClickListener( this );
    card_delivered.setOnClickListener( this );
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
}
