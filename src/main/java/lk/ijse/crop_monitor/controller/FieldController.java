package lk.ijse.crop_monitor.controller;

import lk.ijse.crop_monitor.customObj.FieldResponse;
import lk.ijse.crop_monitor.dto.impl.FieldDto;
import lk.ijse.crop_monitor.exception.DataPersistFailedException;
import lk.ijse.crop_monitor.exception.NotFoundException;
import lk.ijse.crop_monitor.service.FieldService;
import lk.ijse.crop_monitor.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/field")
@CrossOrigin
@RequiredArgsConstructor
public class FieldController {
    private final FieldService fieldService;
    private static final Logger logger = LoggerFactory.getLogger(FieldController.class);

    @PostMapping
    public ResponseEntity<Void> saveField(
            @RequestParam("fieldName") String fieldName,
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude,
            @RequestParam("fieldSize") double fieldSize,
            @RequestParam("image1") MultipartFile image1,
            @RequestParam("image2") MultipartFile image2
    ) {
        logger.info("Request to save field received with name: {}", fieldName);
        try {
            byte[] image1Bytes = image1.getBytes();
            byte[] image2Bytes = image2.getBytes();
            String image1Base64 = AppUtil.toBase64Image(image1Bytes);
            String image2Base64 = AppUtil.toBase64Image(image2Bytes);

            FieldDto fieldDto = new FieldDto();
            fieldDto.setFieldName(fieldName);
            fieldDto.setFieldLocation(new Point(latitude, longitude));
            fieldDto.setFieldSize(fieldSize);
            fieldDto.setImage1(image1Base64);
            fieldDto.setImage2(image2Base64);

            fieldService.saveField(fieldDto);
            logger.info("Field saved successfully: {}", fieldDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            logger.error("Failed to persist field: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Unexpected error while saving field: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/{fieldCode}", params = "staffIds")
    public ResponseEntity<Void> updateField(
            @PathVariable("fieldCode") String fieldCode,
            @RequestParam("fieldName") String fieldName,
            @RequestParam("latitude") int latitude,
            @RequestParam("longitude") int longitude,
            @RequestParam("fieldSize") double fieldSize,
            @RequestParam("image1") MultipartFile image1,
            @RequestParam("image2") MultipartFile image2,
            @RequestParam("staffIds") List<String> staffIds
    ) {
        logger.info("Request to update field received with code: {}", fieldCode);
        try {
            FieldDto fieldDto = new FieldDto();
            fieldDto.setFieldCode(fieldCode);
            fieldDto.setFieldName(fieldName);
            fieldDto.setFieldLocation(new Point(latitude, longitude));
            fieldDto.setFieldSize(fieldSize);
            fieldDto.setImage1(AppUtil.toBase64Image(image1.getBytes()));
            fieldDto.setImage2(AppUtil.toBase64Image(image2.getBytes()));

            fieldService.updateField(fieldDto, staffIds);
            logger.info("Field updated successfully: {}", fieldDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            logger.error("Field not found for update, code: {}", fieldCode);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (DataPersistFailedException e) {
            logger.error("Failed to update field, code: {}", fieldCode);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Unexpected error while updating field, code: {}", fieldCode);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{fieldCode}")
    public FieldResponse getField(@PathVariable("fieldCode") String fieldCode) {
        logger.info("Fetching field details for code: {}", fieldCode);
        FieldResponse response = fieldService.getField(fieldCode);
        logger.info("Field details fetched successfully for code: {}", fieldCode);
        return response;
    }

    @DeleteMapping("/{fieldCode}")
    public ResponseEntity<Void> deleteField(@PathVariable("fieldCode") String fieldCode) {
        logger.info("Request to delete field received with code: {}", fieldCode);
        try {
            fieldService.deleteField(fieldCode);
            logger.info("Field deleted successfully, code: {}", fieldCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            logger.error("Field not found for deletion, code: {}", fieldCode);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Unexpected error while deleting field, code: {}", fieldCode);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public List<FieldDto> getAllFields() {
        logger.info("Fetching all fields.");
        List<FieldDto> fields = fieldService.getAllFields();
        logger.info("Fetched {} field records successfully.", fields.size());
        return fields;
    }
}
