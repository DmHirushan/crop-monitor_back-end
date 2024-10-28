package lk.ijse.crop_monitor.controller;

import lk.ijse.crop_monitor.dto.impl.CropDto;
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
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCrop(@RequestPart ("cropCode") String cropCode,
                                         @RequestPart ("cropCommonName") String cropCommonName,
                                         @RequestPart ("cropScientificName") String cropScientificName,
                                         @RequestPart ("cropImage") MultipartFile cropImage,
                                         @RequestPart ("category") String category,
                                         @RequestPart ("cropSeason") String cropSeason,
                                         @RequestPart ("fieldCode") String fieldCode,
                                         @RequestPart ("cropDetailsCode") String cropDetailsCode

    ) throws IOException {
        CropDto cropDto = new CropDto();
        byte[] crop = cropImage.getBytes();
        String base64ProfilePic = AppUtil.toBase64ProfilePic(crop);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
