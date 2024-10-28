package lk.ijse.crop_monitor.dto.impl;

import lk.ijse.crop_monitor.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CropDetailsDto implements SuperDto {
    private String logCode;
    private Date logDate;
    private String logDetails;
    private String observedImage;

    private List<String> fieldCodes;    // List of field codes associated with this log
    private List<String> cropCodes;     // List of crop codes associated with this log
    private List<String> staffIds;      // List of staff member IDs associated with this log
}
