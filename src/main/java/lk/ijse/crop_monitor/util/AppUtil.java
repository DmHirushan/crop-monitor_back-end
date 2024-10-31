package lk.ijse.crop_monitor.util;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String toBase64Image(byte [] profilePic){
        return Base64.getEncoder().encodeToString(profilePic);
    }

    public static String createCropCode(){
        return "CROP-"+ UUID.randomUUID();
    }

    public static String createCropDetailCode(){
        return "CROP_DETAIL-"+ UUID.randomUUID();
    }

    public static String createStaffId(){
        return "STAFF_MEMBER"+ UUID.randomUUID();
    }
}
