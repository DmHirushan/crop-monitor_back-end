package lk.ijse.crop_monitor.controller;

import lk.ijse.crop_monitor.customObj.CropDetailResponse;
import lk.ijse.crop_monitor.customObj.FieldResponse;
import lk.ijse.crop_monitor.dto.impl.FieldDto;
import lk.ijse.crop_monitor.exception.DataPersistFailedException;
import lk.ijse.crop_monitor.exception.NotFoundException;
import lk.ijse.crop_monitor.service.FieldService;
import lk.ijse.crop_monitor.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @PostMapping
    public ResponseEntity<Void> saveField(
            @RequestParam ("fieldName") String fieldName,
            @RequestParam ("latitude") double latitude,
            @RequestParam ("longitude") double longitude,
            @RequestParam ("fieldSize") double fieldSize,
            @RequestParam ("image1")MultipartFile image1,
            @RequestParam ("image2") MultipartFile image2
//            @RequestPart ("cropCodes") List<String> cropCodes,
//            @RequestPart ("staffIds") List<String> staffIds
//            @RequestPart ("equipmentIds") List<String> equipmentIds,
//            @RequestPart ("cropDetailsLogCode") List<String> cropDetailsLogCode
    )
    {
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
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
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
    )
    {
        try{
            FieldDto fieldDTO = new FieldDto();
            fieldDTO.setFieldCode(fieldCode);
            fieldDTO.setFieldName(fieldName);
            fieldDTO.setFieldLocation(new Point(latitude, longitude));
            fieldDTO.setFieldSize(fieldSize);
            fieldDTO.setImage1(AppUtil.toBase64Image(image1.getBytes()));
            fieldDTO.setImage2(AppUtil.toBase64Image(image2.getBytes()));
//        logger.info("Request received to update field with staff IDs {}: {}", staffIds, fieldDTO);

            fieldService.updateField(fieldDTO,staffIds);
//            logger.info("Field updated successfully: {}", fieldDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
//            logger.error("Field not found for update: {}", fieldDTO, e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (DataPersistFailedException e) {
//            logger.error("Failed to update field: {}", fieldDTO, e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
//            logger.error("Internal server error while updating field: {}", fieldDTO, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{fieldCode}")
    public FieldResponse getField(@PathVariable ("fieldCode") String fieldCode){
        return fieldService.getField(fieldCode);
    }

    @DeleteMapping("/{fieldCode}")
    public ResponseEntity<Void> deleteField(@PathVariable ("fieldCode") String fieldCode){
        try{
            fieldService.deleteField(fieldCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public List<FieldDto> getAllFields(){
        return fieldService.getAllFields();
    }
}
