package lk.ijse.crop_monitor.dto.impl;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lk.ijse.crop_monitor.customObj.CropResponse;
import lk.ijse.crop_monitor.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CropDto implements SuperDto, CropResponse {
    @NotBlank(message = "Crop code is mandatory")
    @Size(max = 50, message = "Crop code must not exceed 50 characters")
    private String cropCode;

    @NotBlank(message = "Crop common name is mandatory")
    @Size(max = 100, message = "Crop common name must not exceed 100 characters")
    private String cropCommonName;

    @NotBlank(message = "Crop scientific name is mandatory")
    @Size(max = 100, message = "Crop scientific name must not exceed 100 characters")
    private String cropScientificName;

    @NotBlank(message = "Crop image URL is mandatory")
    @Pattern(regexp = "^(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|jpeg|png|gif)$",
            message = "Crop image must be a valid URL ending with .jpg, .jpeg, .png, or .gif")
    private String cropImage;

    @NotBlank(message = "Category is mandatory")
    @Size(max = 50, message = "Category must not exceed 50 characters")
    private String category;

    @NotBlank(message = "Crop season is mandatory")
    @Size(max = 50, message = "Crop season must not exceed 50 characters")
    private String cropSeason;

    @NotBlank(message = "Field code is mandatory")
    @Size(max = 50, message = "Field code must not exceed 50 characters")
    private String fieldCode;        // Reference to Field entity's field code
}
