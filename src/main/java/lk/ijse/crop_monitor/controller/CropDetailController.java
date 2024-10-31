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
    public ResponseEntity<Void> saveCropDetails(
            @RequestPart ("logDetails") String logDetails,
            @RequestPart ("observedImage")MultipartFile observedImage
    )
    {
        try {
            CropDetailsDto cropDetailsDto = new CropDetailsDto();
            cropDetailsDto.setLogDate(new Date());
            cropDetailsDto.setLogDetails(logDetails);
            byte[] bytes = observedImage.getBytes();
            String base64Image = AppUtil.toBase64Image(bytes);
            cropDetailsDto.setObservedImage(base64Image);
            cropDetailService.saveCropDetail(cropDetailsDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistFailedException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{logCode}")
    public CropDetailResponse getCropDetail(@PathVariable ("logCode") String logCode){
        return cropDetailService.getCropDetail(logCode);
    }

    @DeleteMapping("/{logCode}")
    public ResponseEntity<Void> deleteCropDetail(@PathVariable ("logCode") String logCode){
        try{
            cropDetailService.deleteCropDetail(logCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{logCode}")
    public ResponseEntity<Void> updateCropDetail(
            @PathVariable ("logCode") String logCode,
            @RequestPart ("logDetails") String logDetails,
            @RequestPart ("observedImage")MultipartFile observedImage
    ){
        try{
            CropDetailsDto cropDetailsDto = new CropDetailsDto();
            cropDetailsDto.setLogCode(logCode);
            cropDetailsDto.setLogDate(new Date());
            cropDetailsDto.setLogDetails(logDetails);
            byte[] bytes = observedImage.getBytes();
            String base64Image = AppUtil.toBase64Image(bytes);
            cropDetailsDto.setObservedImage(base64Image);
            cropDetailService.updateCropDetail(cropDetailsDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get_all")
    public List<CropDetailsDto> getAllCropDetails(){
        return cropDetailService.getAllCropDetails();
    }
}
