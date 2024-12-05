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
        return "STAFF" + "001";
    }

    public static String createVehicleCode() {
        return "VEHICLE"+ UUID.randomUUID();
    }

    public static String createFieldCode() {
        return "FIELD"+ "001";
    }

    public static String createEquipmentId() {
        return "EQUIPMENT"+ UUID.randomUUID();
    }
}
