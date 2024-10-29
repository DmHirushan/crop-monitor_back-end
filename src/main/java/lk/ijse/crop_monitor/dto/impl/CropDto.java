package lk.ijse.crop_monitor.dto.impl;

import lk.ijse.crop_monitor.customObj.CropResponse;
import lk.ijse.crop_monitor.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CropDto implements SuperDto, CropResponse {
    private String cropCode;
    private String cropCommonName;
    private String cropScientificName;
    private String cropImage;
    private String category;
    private String cropSeason;
    private String fieldCode;         // Reference to Field entity's field code
    private String cropDetailsCode;   // Reference to CropDetails entity's log code
}
