package lk.ijse.crop_monitor.dto.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lk.ijse.crop_monitor.customObj.StaffResponse;
import lk.ijse.crop_monitor.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StaffDto implements SuperDto, StaffResponse {

    @NotBlank(message = "ID is mandatory")
    @Size(max = 50, message = "ID must not exceed 50 characters")
    private String id;

    @NotBlank(message = "First name is mandatory")
    @Size(max = 100, message = "First name must not exceed 100 characters")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Size(max = 100, message = "Last name must not exceed 100 characters")
    private String lastName;

    @NotBlank(message = "Designation is mandatory")
    @Size(max = 50, message = "Designation must not exceed 50 characters")
    private String designation;

    @NotBlank(message = "Gender is mandatory")
    @Pattern(regexp = "MALE|FEMALE|OTHER", message = "Gender must be MALE, FEMALE, or OTHER")
    private String gender;

    @NotBlank(message = "Joined date is mandatory")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String joinedDate;

    @NotBlank(message = "Date of Birth is mandatory")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String DOB;

    @NotBlank(message = "Address line 1 is mandatory")
    @Size(max = 255, message = "Address line 1 must not exceed 255 characters")
    private String addressLine1;

    @Size(max = 255, message = "Address line 2 must not exceed 255 characters")
    private String addressLine2;

    @Size(max = 255, message = "Address line 3 must not exceed 255 characters")
    private String addressLine3;

    @Size(max = 255, message = "Address line 4 must not exceed 255 characters")
    private String addressLine4;

    @Size(max = 255, message = "Address line 5 must not exceed 255 characters")
    private String addressLine5;

    @NotBlank(message = "Contact number is mandatory")
    @Pattern(regexp = "\\d{10}", message = "Contact number must be 10 digits")
    private String contactNo;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Role is mandatory")
    @Pattern(regexp = "ADMIN|MANAGER|SCIENTIST|STAFF", message = "Role must be ADMIN, MANAGER, SCIENTIST, or STAFF")
    private String role;

    @NotEmpty(message = "At least one field code must be provided")
    private List<@NotBlank(message = "Field code must not be blank") String> fieldCode;

    @NotEmpty(message = "At least one crop details log code must be provided")
    private List<@NotBlank(message = "Crop details log code must not be blank") String> cropDetailsLogCode;

    @NotBlank(message = "Equipment ID is mandatory")
    @Size(max = 50, message = "Equipment ID must not exceed 50 characters")
    private String equipmentId;

    @NotEmpty(message = "At least one vehicle code must be provided")
    private List<@NotBlank(message = "Vehicle code must not be blank") String> vehicleCode;
}
