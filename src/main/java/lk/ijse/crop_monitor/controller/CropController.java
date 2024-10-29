package lk.ijse.crop_monitor.controller;

import lk.ijse.crop_monitor.customObj.CropResponse;
import lk.ijse.crop_monitor.dto.impl.CropDto;
import lk.ijse.crop_monitor.exception.DataPersistFailedException;
import lk.ijse.crop_monitor.service.CropService;
import lk.ijse.crop_monitor.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/crop")
@CrossOrigin
@RequiredArgsConstructor
public class CropController {
    private final CropService cropService;
    @PostMapping
    public ResponseEntity<Void> saveCrop(@RequestPart ("cropCode") String cropCode,
                                         @RequestPart ("cropCommonName") String cropCommonName,
                                         @RequestPart ("cropScientificName") String cropScientificName,
                                         @RequestPart ("cropImage") MultipartFile cropImage,
                                         @RequestPart ("category") String category,
                                         @RequestPart ("cropSeason") String cropSeason,
                                         @RequestPart ("fieldCode") String fieldCode,
                                         @RequestPart ("cropDetailsCode") String cropDetailsCode

    ) {
        try {
            CropDto cropDto = new CropDto();
            byte[] crop = cropImage.getBytes();
            String base64Image = AppUtil.toBase64Image(crop);
            cropDto.setCropCode(cropCode);
            cropDto.setCropCommonName(cropCommonName);
            cropDto.setCropScientificName(cropScientificName);
            cropDto.setCropImage(base64Image);
            cropDto.setCategory(category);
            cropDto.setCropSeason(cropSeason);
            cropDto.setFieldCode(fieldCode);
            cropDto.setCropDetailsCode(cropDetailsCode);
            cropService.saveCrop(cropDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{cropCode}")
    public CropResponse getSelectedCrop(@PathVariable ("cropCode") String cropCode){
        return cropService.getCrop(cropCode);
    }
}
