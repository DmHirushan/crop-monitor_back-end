package lk.ijse.crop_monitor.dto.impl;

import lk.ijse.crop_monitor.customObj.FieldResponse;
import lk.ijse.crop_monitor.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

import java.awt.*;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FieldDto implements SuperDto, FieldResponse {
    private String fieldCode;
    private String fieldName;
    private Point fieldLocation; // Represented as a string (e.g., "latitude,longitude") or custom location format
    private double fieldSize;
    private String image1;
    private String image2;
//    private List<String> cropCodes;   // List of crop codes associated with this field
    private List<String> staffIds;
//    private List<String> equipmentIds; // List of equipment IDs assigned to this field
//    private List<String> cropDetailsLogCode; // Log code for associated crop details, if any
}
