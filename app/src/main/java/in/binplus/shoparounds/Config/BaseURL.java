package in.binplus.shoparounds.Config;


public class BaseURL {
    static final String APP_NAME = "yourbooksstore";
    public static final String PREFS_NAME = "GroceryLoginPrefs";
    public static final String PREFS_NAME2 = "GroceryLoginPrefs2";
    public static final String IS_LOGIN = "isLogin";
    public static final String KEY_NAME = "user_fullname";
    public static final String KEY_EMAIL = "user_email";

    public static final String TOTAL_AMOUNT = "TOTAL_AMOUNT";
    public static final String WALLET_TOTAL_AMOUNT = "WALLET_TOTAL_AMOUNT";
    public static final String COUPON_TOTAL_AMOUNT = "COUPON_TOTAL_AMOUNT";
    public static final String KEY_ID = "id";
    public  static final String KEY_USER_ID ="user_id";
    public static final String KEY_MOBILE = "user_phone";
    public static final String KEY_IMAGE = "user_image";
    public static final String KEY_WALLET_Ammount = "wallet_ammount";
    public static final String KEY_REWARDS_POINTS = "rewards_points";
    public static final String KEY_PAYMENT_METHOD = "payment_method";
    public static final String KEY_PINCODE = "pincode";
    public static final String KEY_SOCITY_ID = "Socity_id";
    public static final String KEY_REWARDS = "rewards";
    public static final String KEY_SOCITY_NAME = "socity_name";
    public static final String KEY_HOUSE = "house_no";
    public static final String KEY_DATE = "date";
    public static final String KEY_TIME = "time";
    public static final String KEY_CAT = "cat_id";
    public  static final String KEY_ADHAR_ID="adhar_id";
    public static final String KEY_VEHICLE_NAME="vehicle_name";
    public static final String KEY_VEHICLE_NO="vehicle_no";
    public static final String KEY_ADDRESS="address";


    //Store Selection

    public static final String KEY_STORE_COUNT = "STORE_COUNT";
    public static final String KEY_NOTIFICATION_COUNT = "NOTIFICATION_COUNT";

    //Firebase
    public static final String SHARED_PREF = "ah_firebase";
    public static final String TOPIC_GLOBAL = "global";
    public static final int NOTIFICATION_ID = 100;
    public static final int NOTIFICATION_ID_BIG_IMAGE = 101;


    public static final String KEY_PASSWORD = "password";

    //City and Store Id
    public static final String CITY_ID = "CITY_ID";
    public static final String STORE_ID = "STORE_ID";

   // public static String BASE_URL = "https://yourbooksstore.com/admin/";
    public static String BASE_URL = "https://shoparounds.com/admin/";

    public static String IMG_PRODUCT_URL = BASE_URL + "uploads/products/";

    public static String IMG_PROFILE_URL = BASE_URL + "uploads/profile/";



    public static String LOGIN_URL = BASE_URL + "index.php/api/delivery_boy_login";

    public static String REGISTER_URL = BASE_URL + "index.php/api/delivery_signup";

    public static String FORGOT_URL = BASE_URL + "index.php/api/forgot_password_delivery";

    public static String URL_SEND_OTP = BASE_URL + "index.php/api/generate_delivery_otp";
    public static String URL_REG_OTP = BASE_URL + "index.php/api/verification_delivery_mobile";
    public static String URL_GET_ORDERS = BASE_URL + "index.php/api/delivery_boy_order";
    public static String URL_GET_ORDERS_ITEMS = BASE_URL + "index.php/api/order_details";
    public static String OrderDetail = BASE_URL + "index.php/api/order_details";
    public static  String UpdatePassword = BASE_URL+"index.php/api/update_password_delivery";

    public static final String urlUpload = BASE_URL+"index.php/api/mark_delivered2";

    // global topic to receive app wide push notifications

    // broadcast receiver intent filters
    public static final String REGISTRATION_COMPLETE = "registrationComplete";

    public static final String PUSH_NOTIFICATION = "pushNotification";

    //Filter Urls

    public static String GET_LANGUAGE_URL = BASE_URL + "index.php/api/get_language";
    public static String GET_SUBJECT_URL = BASE_URL + "index.php/api/get_subject";
    public static String GET_BOOK_CLASS_URL = BASE_URL + "index.php/api/get_book_class";
    public static String GET_BOOK_CLASS_LIST = BASE_URL + "index.php/api/getBookClass";
    public static String GET_PRODUCT_FILTER = BASE_URL + "index.php/api/get_products_filter";
    public static String GET_ORDER_WALLET = BASE_URL + "index.php/api/send_order_with_wallet";
    public static String GET_UPLOAD = BASE_URL + "index.php/api/upload_images";
    public static String GET_STANDARD_CHARGES = BASE_URL + "index.php/api/get_standard_delivery_charges";

}
