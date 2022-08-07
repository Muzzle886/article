package ga.muzzle.pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;

/**
 * @author himear
 */
public class ReturnMessage {
    private final String date;
    private boolean success;
    private String message;

    public ReturnMessage() {
        this.date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
    }

    public ReturnMessage(boolean success, String message) {
        this.date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
        this.success = success;
        this.message = message;
    }

    public ReturnMessage(String date, boolean success, String message) {
        this.date = date;
        this.success = success;
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
