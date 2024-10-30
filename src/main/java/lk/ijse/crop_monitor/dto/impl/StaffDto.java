package lk.ijse.crop_monitor.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StaffDto {
    private String id;
    private String firstName;
    private String lastName;
    private String designation;
    private String gender;// Could be a String or Enum name for Gender
    private String joinedDate;
    private Date DOB;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String addressLine5;
    private String contactNo;
    private String email;
    private String role;                // Could be a String or Enum name for Role
//    private List<String> equipmentIds;  // List of equipment IDs associated with this staff
//    private List<String> fieldCodes;    // List of field codes associated with this staff
//    private List<String> vehicleCodes;  // List of vehicle codes associated with this staff
    private String cropDetailsLogCode;  // Log code for associated crop details, if any

}
