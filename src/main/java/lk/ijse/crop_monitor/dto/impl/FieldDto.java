package lk.ijse.crop_monitor.dto.impl;

import jakarta.validation.constraints.*;
import lk.ijse.crop_monitor.customObj.FieldResponse;
import lk.ijse.crop_monitor.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.format.annotation.NumberFormat;

import java.awt.*;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FieldDto implements SuperDto, FieldResponse {
    @NotBlank(message = "Field code is mandatory")
    @Size(max = 50, message = "Field code must not exceed 50 characters")
    private String fieldCode;

    @NotBlank(message = "Field name is mandatory")
    @Size(max = 100, message = "Field name must not exceed 100 characters")
    private String fieldName;

    @NotNull(message = "Field location is mandatory")
    private Point fieldLocation; // Custom type, ensure it's non-null

    @Positive(message = "Field size must be greater than 0")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private double fieldSize;

    @NotBlank(message = "Image 1 URL is mandatory")
    @Size(max = 255, message = "Image 1 URL must not exceed 255 characters")
    private String image1;

    @Size(max = 255, message = "Image 2 URL must not exceed 255 characters")
    private String image2;

    @NotEmpty(message = "At least one staff ID must be provided")
    private List<@NotBlank(message = "Staff ID must not be blank") String> staffIds;
}
