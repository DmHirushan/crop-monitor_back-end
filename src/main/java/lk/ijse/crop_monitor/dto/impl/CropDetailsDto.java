package lk.ijse.crop_monitor.dto.impl;

import jakarta.validation.constraints.*;
import lk.ijse.crop_monitor.customObj.CropDetailResponse;
import lk.ijse.crop_monitor.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CropDetailsDto implements SuperDto, CropDetailResponse {
    @NotBlank(message = "Log code is mandatory")
    private String logCode;

    @NotNull(message = "Log date cannot be null")
    @PastOrPresent(message = "Log date must be in the past or present")
    private Date logDate;

    @NotBlank(message = "Log details are mandatory")
    @Size(max = 500, message = "Log details must not exceed 500 characters")
    private String logDetails;

    @NotBlank(message = "Observed image URL cannot be blank")
    private String observedImage;

    @NotEmpty(message = "Field codes cannot be empty")
    private List<@NotBlank(message = "Field code cannot be blank") String> fieldCodes;

    @NotEmpty(message = "Crop codes cannot be empty")
    private List<@NotBlank(message = "Crop code cannot be blank") String> cropCodes;

    @NotEmpty(message = "Staff IDs cannot be empty")
    private List<@NotBlank(message = "Staff ID cannot be blank") String> staffIds;      // List of staff member IDs associated with this log
}
