package lk.ijse.crop_monitor.controller;

import lk.ijse.crop_monitor.customObj.CropDetailResponse;
import lk.ijse.crop_monitor.dto.impl.CropDetailsDto;
import lk.ijse.crop_monitor.exception.DataPersistFailedException;
import lk.ijse.crop_monitor.exception.NotFoundException;
import lk.ijse.crop_monitor.service.CropDetailService;
import lk.ijse.crop_monitor.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/crop_detail")
@CrossOrigin
@RequiredArgsConstructor
public class CropDetailController {
    private final CropDetailService cropDetailService;
    private static final Logger logger = LoggerFactory.getLogger(CropDetailController.class);

    @PostMapping
    public ResponseEntity<?> saveCropDetails(
            @RequestPart(value = "logDetails") String logDetails,
            @RequestPart(value = "observedImage") MultipartFile observedImg,
            @RequestParam(value = "fieldCode") List<String> fieldCodes,
            @RequestParam(value = "cropCode") List<String> cropCodes,
            @RequestParam(value = "staffId") List<String> staffIds
    ) {
        logger.info("Saving crop details: logDetails={}, fieldCodes={}, cropCodes={}, staffIds={}",
                logDetails, fieldCodes, cropCodes, staffIds);
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
            logger.info("Crop details saved successfully.");
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            logger.error("Failed to persist crop details: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            logger.error("IO exception occurred while saving crop details: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @PatchMapping(value = "/{logCode}")
    public ResponseEntity<?> updateCropDetails(
            @RequestPart(value = "logDetails") String logDetails,
            @RequestPart(value = "observedImg") MultipartFile observedImg,
            @PathVariable(value = "logCode") String logCode
    ) {
        logger.info("Updating crop details for logCode={}", logCode);
        try {
            String updateBase64ProfilePic = AppUtil.toBase64Image(observedImg.getBytes());
            CropDetailsDto cropDetailsDTO = new CropDetailsDto();
            cropDetailsDTO.setLogDetails(logDetails);
            cropDetailsDTO.setObservedImage(updateBase64ProfilePic);

            cropDetailService.updateCropDetails(cropDetailsDTO, logCode);
            logger.info("Crop details updated successfully for logCode={}", logCode);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            logger.error("Crop details not found for logCode={}: {}", logCode, e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            logger.error("IO exception occurred while updating crop details for logCode={}: {}", logCode, e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{logCode}")
    public ResponseEntity<?> findCropDetailsByLogCode(@PathVariable String logCode) {
        logger.info("Fetching crop details for logCode={}", logCode);
        try {
            CropDetailResponse cropDetailsByLogCode = cropDetailService.findCropDetailsByLogCode(logCode);
            logger.info("Crop details fetched successfully for logCode={}", logCode);
            return new ResponseEntity<>(cropDetailsByLogCode, HttpStatus.OK);
        } catch (NotFoundException e) {
            logger.error("Crop details not found for logCode={}: {}", logCode, e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{logCode}")
    public ResponseEntity<?> deleteCropDetailsByLogCode(@PathVariable String logCode) {
        logger.info("Deleting crop details for logCode={}", logCode);
        try {
            cropDetailService.deleteCropDetailsByLogCode(logCode);
            logger.info("Crop details deleted successfully for logCode={}", logCode);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            logger.error("Crop details not found for deletion, logCode={}: {}", logCode, e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllCropDetails() {
        logger.info("Fetching all crop details.");
        try {
            return new ResponseEntity<>(cropDetailService.getAllCropDetails(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occurred while fetching all crop details: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
