package lk.ijse.crop_monitor.controller;

import lk.ijse.crop_monitor.customObj.EquipmentResponse;
import lk.ijse.crop_monitor.dto.impl.EquipmentDto;
import lk.ijse.crop_monitor.exception.DataPersistFailedException;
import lk.ijse.crop_monitor.exception.NotFoundException;
import lk.ijse.crop_monitor.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/equipment")
@CrossOrigin
@RequiredArgsConstructor
public class EquipmentController {
    private final EquipmentService equipmentService;
    private static final Logger logger = LoggerFactory.getLogger(EquipmentController.class);

    @PostMapping
    public ResponseEntity<Void> saveEquipment(@RequestBody EquipmentDto equipmentDto) {
        logger.info("Attempting to save equipment: {}", equipmentDto);
        try {
            equipmentService.saveEquipment(equipmentDto);
            logger.info("Equipment saved successfully: {}", equipmentDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            logger.error("Failed to persist equipment: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Unexpected error occurred while saving equipment: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{equipmentId}")
    public EquipmentResponse getEquipment(@PathVariable("equipmentId") String equipmentId) {
        logger.info("Fetching equipment details for ID: {}", equipmentId);
        EquipmentResponse response = equipmentService.getEquipment(equipmentId);
        logger.info("Equipment details fetched successfully for ID: {}", equipmentId);
        return response;
    }

    @DeleteMapping("/{equipmentId}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable("equipmentId") String equipmentId) {
        logger.info("Attempting to delete equipment with ID: {}", equipmentId);
        try {
            equipmentService.deleteEquipment(equipmentId);
            logger.info("Equipment deleted successfully for ID: {}", equipmentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            logger.error("Equipment not found for deletion, ID: {}", equipmentId);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Unexpected error occurred while deleting equipment ID: {}", equipmentId);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public List<EquipmentDto> getAllEquipments() {
        logger.info("Fetching all equipment details.");
        List<EquipmentDto> equipmentList = equipmentService.getAllEquipments();
        logger.info("Fetched {} equipment records successfully.", equipmentList.size());
        return equipmentList;
    }

    @PatchMapping("/{equipmentId}")
    public ResponseEntity<Void> updateEquipment(@PathVariable("equipmentId") String equipmentId, @RequestBody EquipmentDto equipmentDto) {
        logger.info("Attempting to update equipment with ID: {}", equipmentId);
        try {
            equipmentDto.setEquipmentId(equipmentId);
            equipmentService.updateEquipment(equipmentDto);
            logger.info("Equipment updated successfully for ID: {}", equipmentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            logger.error("Equipment not found for update, ID: {}", equipmentId);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Unexpected error occurred while updating equipment ID: {}", equipmentId);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
