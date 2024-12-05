package lk.ijse.crop_monitor.controller;

import lk.ijse.crop_monitor.customObj.CropDetailResponse;
import lk.ijse.crop_monitor.dto.impl.CropDetailsDto;
import lk.ijse.crop_monitor.exception.DataPersistFailedException;
import lk.ijse.crop_monitor.exception.NotFoundException;
import lk.ijse.crop_monitor.service.CropDetailService;
import lk.ijse.crop_monitor.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/crop_detail")
@CrossOrigin
@RequiredArgsConstructor
public class CropDetailController {
    private final CropDetailService cropDetailService;

    @PostMapping
    public ResponseEntity<?> saveCropDetails(
            @RequestPart(value = "logDetails") String logDetails,
            @RequestPart(value = "observedImage") MultipartFile observedImg,
            @RequestParam(value = "fieldCode") List<String> fieldCodes,
            @RequestParam(value = "cropCode") List<String> cropCodes,
            @RequestParam(value = "staffId") List<String> staffIds
    ) {
        try {
            String updateBase64ProfilePic = AppUtil.toBase64Image(observedImg.getBytes());
            CropDetailsDto cropDetailsDTO = new CropDetailsDto();
            cropDetailsDTO.setLogDate(new Date());
            cropDetailsDTO.setLogDetails(logDetails);
            cropDetailsDTO.setObservedImage(updateBase64ProfilePic);
            cropDetailsDTO.setFieldCodes(fieldCodes);
            cropDetailsDTO.setCropCodes(cropCodes);
            cropDetailsDTO.setStaffIds(staffIds);
            cropDetailService.saveCropDetails(cropDetailsDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistFailedException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PatchMapping(value = "/{logCode}")
    public ResponseEntity<?> updateCropDetails(
            @RequestPart(value = "logDetails") String logDetails,
            @RequestPart(value = "observedImg") MultipartFile observedImg,
            @PathVariable(value = "logCode") String logCode
    ){
        try {
            String updateBase64ProfilePic = AppUtil.toBase64Image(observedImg.getBytes());
            CropDetailsDto cropDetailsDTO = new CropDetailsDto();
            cropDetailsDTO.setLogDetails(logDetails);
            cropDetailsDTO.setObservedImage(updateBase64ProfilePic);
            cropDetailService.updateCropDetails(cropDetailsDTO,logCode);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{logCode}")
    public ResponseEntity<?> findCropDetailsByLogCode(@PathVariable String logCode){
        try {
            CropDetailResponse cropDetailsByLogCode = cropDetailService.findCropDetailsByLogCode(logCode);
            return new ResponseEntity<>(cropDetailsByLogCode, HttpStatus.OK);
        }catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{logCode}")
    public ResponseEntity<?> deleteCropDetailsByLogCode(@PathVariable String logCode){
        try {
            cropDetailService.deleteCropDetailsByLogCode(logCode);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllCropDetails(){
        return new ResponseEntity<>(cropDetailService.getAllCropDetails(), HttpStatus.OK);
    }
}
