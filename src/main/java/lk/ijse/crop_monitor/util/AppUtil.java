package lk.ijse.crop_monitor.util;

import java.util.Base64;

public class AppUtil {
    public static String toBase64ProfilePic(byte [] profilePic){
        return Base64.getEncoder().encodeToString(profilePic);
    }
}