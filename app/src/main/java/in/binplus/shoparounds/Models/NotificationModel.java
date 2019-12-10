package in.binplus.shoparounds.Models;

public class NotificationModel {
    String date ;
    String message ;

    public NotificationModel(String date, String message) {
        this.date = date;
        this.message = message;
    }

    public NotificationModel() {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
