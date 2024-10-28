package lk.ijse.crop_monitor.controller;

import lk.ijse.crop_monitor.dto.impl.CropDto;
import lk.ijse.crop_monitor.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/crop")
@CrossOrigin
public class CropController {
    @Autowired
    private CropService cropService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCrop(@RequestBody CropDto cropDto){
        cropService.saveCrop(cropDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
