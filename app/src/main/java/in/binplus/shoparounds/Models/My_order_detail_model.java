package in.binplus.shoparounds.Models;



public class My_order_detail_model {

    String sale_item_id;
    String sale_id;
    String product_id;
    String product_name;
    String qty;
    String unit;
    String unit_value;
    String price;
    String qty_in_kg;
    String product_image;

    String user_id ;
    String user_name;
    String user_email;
    String user_phone ;
    String user_fullname ;

    String user_image;
    String user_address;
    String user_pincode;
    String city_name;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_fullname() {
        return user_fullname;
    }

    public void setUser_fullname(String user_fullname) {
        this.user_fullname = user_fullname;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getUser_pincode() {
        return user_pincode;
    }

    public void setUser_pincode(String user_pincode) {
        this.user_pincode = user_pincode;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getSale_item_id(){
        return sale_item_id;
    }

    public String getSale_id(){
        return sale_id;
    }

    public String getProduct_id(){
        return product_id;
    }

    public String getProduct_name(){
        return product_name;
    }

    public String getQty(){
        return qty;
    }

    public String getUnit(){
        return unit;
    }

    public String getUnit_value(){
        return unit_value;
    }

    public String getPrice(){
        return price;
    }

    public String getQty_in_kg(){
        return qty_in_kg;
    }

    public String getProduct_image(){
        return product_image;
    }

}
