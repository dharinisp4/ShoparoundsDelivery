package in.binplus.shoparounds.Module;



import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

import in.binplus.shoparounds.R;


public class Module {

    Context context;

    public Module(Context context) {
        this.context = context;
    }

    public void detailsPickupDialog(final Context context)
    {

        final Dialog dialog=new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_pickup_details);

        Button btnOk=(Button)dialog.findViewById(R.id.btnOK);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();



        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    public String VolleyErrorMessage(VolleyError error)
    {
        String str_error ="";
        if (error instanceof TimeoutError) {
            str_error="Connection Timeout";
        } else if (error instanceof AuthFailureError) {
            str_error="Session Timeout";
            //TODO
        } else if (error instanceof ServerError) {
            str_error="Server not responding please try again later";
            //TODO
        } else if (error instanceof NetworkError) {
            str_error="Server not responding please try again later";
            //TODO
        } else if (error instanceof ParseError) {
            //TODO
            str_error="An Unknown error occur";
        }else if(error instanceof NoConnectionError){
            str_error="no Internet Connection";
        }

        return str_error;
    }

//    public void showToast(String msg, Activity activity)
//    {
//        LayoutInflater layoutInflater=activity.getLayoutInflater();
//
//        View layout=layoutInflater.inflate( R.layout.toast_layout,(ViewGroup)activity.findViewById(R.id.toast_root));
//
//        ImageView toast_img=(ImageView)layout.findViewById(R.id.toast_img);
//        TextView toast_text=(TextView) layout.findViewById(R.id.toast_text);
//        toast_text.setText(msg);
//        Toast toast=new Toast(activity.getApplicationContext());
//        toast.setGravity( Gravity.CENTER,0,0);
//        toast.setDuration( Toast.LENGTH_LONG);
//        toast.setView(layout);
//        toast.show();
//    }
//
//    public static void setIntoWish(Activity activity, String product_id, String product_images, String cat_id, String details_product_name,
//                                   String details_product_price, String details_product_desc, String details_in_stock, String details_status,
//                                   String details_product_rewards, String details_product_unit_value, String details_product_unit,
//                                   String details_product_increament, String details_product_inStock, String details_product_title,
//                                   String details_product_mrp, String seller_id, String book_class, String subject, String language)
//    {
//        DatabaseHandlerWishList db_cart=new DatabaseHandlerWishList(activity);
//        HashMap<String, String> mapProduct=new HashMap<String, String>();
//        mapProduct.put("product_id", product_id);
//        mapProduct.put("product_image",product_images);
//        mapProduct.put("category_id",cat_id);
//        mapProduct.put("product_name",details_product_name);
//        mapProduct.put("product_description",details_product_desc);
//        mapProduct.put("in_stock",details_in_stock);
//        mapProduct.put("status",details_status);
//        mapProduct.put("price", details_product_price);
//        mapProduct.put("rewards", details_product_rewards);
//        mapProduct.put("unit_unit",details_product_unit_value );
//        mapProduct.put("unit", details_product_unit);
//        mapProduct.put("increament",details_product_increament);
//        mapProduct.put("stock",details_product_inStock);
//        mapProduct.put("title",details_product_title);
//        mapProduct.put("mrp",details_product_mrp);
//        mapProduct.put("seller_id",seller_id);
//        mapProduct.put("book_class",book_class);
//        mapProduct.put("subject",subject);
//        mapProduct.put("language",language);
//
//
//        try {
//
//            boolean tr = db_cart.setwishlist(mapProduct);
//            if (tr == true) {
//                MainActivity mainActivity = new MainActivity();
//                mainActivity.setCartCounter("" + db_cart.getWishlistCount());
//
//                //   context.setCartCounter("" + holder.db_cart.getCartCount());
//                Toast.makeText(activity, "Added to WishList" , Toast.LENGTH_LONG).show();
//                int n = db_cart.getWishlistCount();
//                updatewish(activity);
//                //      txtTotal.setText("\u20B9"+String.valueOf(db_cart.getTotalAmount()));
//
//            } else if (tr == false) {
//                Toast.makeText(activity, "Something went wrong", Toast.LENGTH_LONG).show();
//                // txtTotal.setText("\u20B9"+String.valueOf(db_cart.getTotalAmount()));
//            }
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            // Toast.makeText(getActivity(), "" + ex.getMessage(), Toast.LENGTH_LONG).show();
//        }
//
//
//
//    }

    }

