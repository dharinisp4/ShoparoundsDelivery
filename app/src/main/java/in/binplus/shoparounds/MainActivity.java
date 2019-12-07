package in.binplus.shoparounds;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.android.volley.NetworkError;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;

import in.binplus.shoparounds.Fragment.HomeFragment;
import in.binplus.shoparounds.Fragment.MyOrders_Fragment;
import in.binplus.shoparounds.Fragment.MyProfile_Fragment;
import in.binplus.shoparounds.Fragment.MyTransaction_Fragment;
import in.binplus.shoparounds.util.ConnectivityReceiver;
import in.binplus.shoparounds.util.Session_management;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ConnectivityReceiver.ConnectivityReceiverListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private TextView totalBudgetCount, totalBudgetCountwish, totalBudgetCount3, tv_name, powerd_text;
    private ImageView iv_profile;

   private Session_management sessionManagement;
    private Menu nav_menu;
    ImageView imageView;
    TextView mTitle;
    LinearLayout viewpa;
    Toolbar toolbar;
    String language = "";
    LinearLayout My_Order, My_Reward, My_Walllet, My_Cart;
    int padding = 0;
    private TextView txtRegId;
    NavigationView navigationView;
    LinearLayout Change_Store;
    String Store_Count;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    ProgressDialog progressDialog ;

    @Override
    protected void attachBaseContext(Context newBase) {


        newBase = LocaleHelper.onAttach(newBase);

        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_main);
        String token;
        progressDialog=new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Loading...");

//

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setPadding(padding, toolbar.getPaddingTop(), padding, toolbar.getPaddingBottom());


//        setSupportActionBar(toolbar);
        for (int i = 0; i < toolbar.getChildCount(); i++) {

            View view = toolbar.getChildAt(i);

            if (view instanceof TextView) {
                TextView textView = (TextView) view;

                Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "Font/Bold.ttf");
                textView.setTypeface(myCustomFont);
            }


        }



        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }





        checkConnection();

       sessionManagement = new Session_management(MainActivity.this);


        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu m = navigationView.getMenu();
        for (int i = 0; i < m.size(); i++) {
            MenuItem mi = m.getItem(i);

            //for aapplying a font to subMenu ...
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu != null && subMenu.size() > 0) {
                for (int j = 0; j < subMenu.size(); j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                  //  applyFontToMenuItem(subMenuItem);
                }
            }

            //the method we have create in activity
          //  applyFontToMenuItem(mi);
        }

        View headerView = navigationView.getHeaderView(0);
//        navigationView.getBackground().setColorFilter(0x80000000, PorterDuff.Mode.MULTIPLY);
        navigationView.setNavigationItemSelectedListener(this);
        nav_menu = navigationView.getMenu();
        View header = ((NavigationView) findViewById(R.id.nav_view)).getHeaderView(0);





        iv_profile = (ImageView) header.findViewById(R.id.iv_header_img);
        iv_profile.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        } );

//        String getimage=sessionManagement.getUpdateProfile().get(BaseURL.KEY_IMAGE);
//        if (!TextUtils.isEmpty(getimage)) {
//
//
//            Glide.with( MainActivity.this )
//                    .load( BaseURL.IMG_PROFILE_URL + getimage)
//                    .fitCenter()
//                    .placeholder( R.drawable.user )
//                    .crossFade()
//                    .diskCacheStrategy( DiskCacheStrategy.ALL )
//                    .dontAnimate()
//                    .into( iv_profile );
//        }

        tv_name = (TextView) header.findViewById(R.id.tv_header_name);
//        My_Order = (LinearLayout) header.findViewById(R.id.my_orders);
//        My_Reward = (LinearLayout) header.findViewById(R.id.my_reward);
//        My_Walllet = (LinearLayout) header.findViewById(R.id.my_wallet);
//        My_Cart = (LinearLayout) header.findViewById(R.id.my_cart);
//
//        My_Order.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, My_Order_activity.class);
//                startActivity(intent);
//
//            }
//        });
//        My_Reward.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Fragment fm = new Reward_fragment();
//                FragmentManager fragmentManager = getFragmentManager();
//                fragmentManager.beginTransaction().replace(R.id.contentPanel, fm)
//                        .addToBackStack(null).commit();
//            }
//        });
//        My_Walllet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Fragment fm = new Wallet_fragment();
//                FragmentManager fragmentManager = getFragmentManager();
//                fragmentManager.beginTransaction().replace(R.id.contentPanel, fm)
//                        .addToBackStack(null).commit();
//            }
//        });
//
//        My_Cart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (dbcart.getCartCount() > 0) {
//                    Fragment fm = new Cart_fragment();
//                    FragmentManager fragmentManager = getFragmentManager();
//                    fragmentManager.beginTransaction().replace(R.id.contentPanel, fm)
//                            .addToBackStack(null).commit();
//
//                } else {
//                    Toast.makeText(MainActivity.this, "No Item in Cart", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        iv_profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (sessionManagement.isLoggedIn()) {
//                    Fragment fm = new Edit_profile_fragment();
//                    FragmentManager fragmentManager = getFragmentManager();
//                    fragmentManager.beginTransaction().replace(R.id.contentPanel, fm)
//                            .addToBackStack(null).commit();
//                } else {
//                    Intent i = new Intent(MainActivity.this, LoginActivity.class);
//                    startActivity(i);
//                    overridePendingTransition(0, 0);
//                }
//            }
//        });

        //updateHeader();

        sideMenu();


           androidx.fragment.app.Fragment fm = new HomeFragment();
            androidx.fragment.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame, fm, "Home_fragment")
                    .setTransition( FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();




//        if (sessionManagement.getUserDetails().
//                get(BaseURL.KEY_ID) != null && !sessionManagement.getUserDetails().
//                get(BaseURL.KEY_ID).equalsIgnoreCase())
//
//        {
//            MyFirebaseRegister fireReg = new MyFirebaseRegister(this);
//            fireReg.RegisterUser(sessionManagement.getUserDetails().get(BaseURL.KEY_ID));
//        }

    }


//    public void updateHeader() {
//        if (sessionManagement.isLoggedIn()) {
//            viewpa.setVisibility( View.VISIBLE );
//            String getname = sessionManagement.getUserDetails().get(BaseURL.KEY_NAME);
//            String getimage = sessionManagement.getUserDetails().get(BaseURL.KEY_IMAGE);
//            String getemail = sessionManagement.getUserDetails().get(BaseURL.KEY_EMAIL);
//            SharedPreferences shre = PreferenceManager.getDefaultSharedPreferences(this);
//            String previouslyEncodedImage = shre.getString("image_data", "");
//            if (!previouslyEncodedImage.equalsIgnoreCase("")) {
//                byte[] b = Base64.decode(previouslyEncodedImage, Base64.DEFAULT);
//                Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
//                iv_profile.setImageBitmap(bitmap);
//            }
////            Glide.with(this)
////                    .load(BaseURL.IMG_PROFILE_URL + getimage)
////                    .placeholder(R.drawable.icon)
////                    .crossFade()
////                    .into(iv_profile);
//            tv_name.setText(getname);
//
//        }
//    }




    public void sideMenu() {

//        if (sessionManagement.isLoggedIn()) {
//            //  tv_number.setVisibility(View.VISIBLE);
//            nav_menu.findItem(R.id.nav_logout).setVisible(true);
////            nav_menu.findItem(R.id.nav_powerd).setVisible(true);
//
////            nav_menu.findItem(R.id.nav_user).setVisible(true);
//        } else {
//
//            //tv_number.setVisibility(View.GONE);
//
//            nav_menu.findItem(R.id.nav_logout).setVisible(false);
//
//            //            nav_menu.findItem(R.id.nav_user).setVisible(false);
//        }
    }

    public void setFinish() {
        finish();
    }

    public void setCartCounter(String totalitem) {
        try {
            totalBudgetCount.setText(totalitem);
        } catch (Exception e) {

        }
    }

    public void setWishCounter(String totalitem) {
        try {
            totalBudgetCountwish.setText(totalitem);
        } catch (Exception e) {

        }
    }

    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate( R.menu.main, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();




        return super.onOptionsItemSelected(item);
    }


    @SuppressLint("ResourceType")
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
       androidx.fragment.app.Fragment fm = null;
        Bundle args = new Bundle();
        if (id == R.id.nav_home) {
            Toast.makeText(MainActivity.this,"home",Toast.LENGTH_LONG).show();
            fm = new HomeFragment();
        } else if (id == R.id.nav_profile)
        {
            if (sessionManagement.isLoggedIn())
            {

           Toast.makeText(MainActivity.this,"profile",Toast.LENGTH_LONG).show();
              fm=new MyProfile_Fragment();
            }
//        else {
//                Intent i = new Intent(MainActivity.this, LoginActivity.class);
//                startActivity(i);
//                overridePendingTransition(0, 0);
           }

        else if (id == R.id.nav_trans) {
            Toast.makeText(MainActivity.this,"Transactions",Toast.LENGTH_LONG).show();
            fm = new MyTransaction_Fragment();
            args.putString( "type", "all" );
            fm.setArguments( args );
        }
        else if (id == R.id.nav_contact_admin) {
//            String smsNumber = "91958/4267640";
//            Intent sendIntent = new Intent("android.intent.action.MAIN");
//            sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
//            sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(smsNumber) + "@s.whatsapp.net");//phone number without "+" prefix
//            startActivity(sendIntent);

        }
        else if (id == R.id.nav_orders) {
            toolbar.setTitle("Orders");
            Toast.makeText(MainActivity.this,"orders",Toast.LENGTH_LONG).show();
            fm = new MyOrders_Fragment();
            args.putString( "type","all" );
            fm.setArguments(args);

        }
         else if (id == R.id.nav_logout) {
             sessionManagement.logoutSession();
           Intent intent = new Intent( MainActivity.this, LoginActivity.class );
           startActivity( intent );
            finish();
       }
        if (fm != null) {
            FragmentManager fragmentManager =getSupportFragmentManager() ;
            fragmentManager.beginTransaction().replace(R.id.frame, fm)
                    .addToBackStack(null).commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer( GravityCompat.START);
        return true;
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        try {
            super.startActivityForResult(intent, requestCode);
        } catch (Exception ignored) {
        }

    }



    public void shareApp() {
        Intent sendIntent = new Intent();
        sendIntent.setAction( Intent.ACTION_SEND);
        sendIntent.putExtra( Intent.EXTRA_TEXT, "Hi friends i am using ." + " http://play.google.com/store/apps/details?id=" + getPackageName() + " APP");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }


    public void reviewOnApp() {
        Uri uri = Uri.parse("market://details?id=" + getPackageName());
        Intent goToMarket = new Intent( Intent.ACTION_VIEW, uri);
        goToMarket.addFlags( Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent( Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
        }
    }

    // Method to manually check connection status
    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnack(isConnected);
    }


    /**
     * Callback will be triggered when there is change in
     * network connection
     */
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }

    private void showSnack(boolean isConnected) {
        String message;
        int color;

        if (!isConnected) {
            Intent intent = new Intent(MainActivity.this, NetworkError.class);
            startActivity(intent);
        }
    }


    // Fetches reg id from shared preferences
    // and displays on the screen



//    @Override
//    protected void onResume() {
//        super.onResume();
//        // register connection status listener
//        AppController.getInstance().setConnectivityListener(this);
//        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
//                new IntentFilter(BaseURL.REGISTRATION_COMPLETE));
//        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
//                new IntentFilter(BaseURL.PUSH_NOTIFICATION));
//        NotificationUtils.clearNotifications(getApplicationContext());
//    }


    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        // register reciver

    }



}
