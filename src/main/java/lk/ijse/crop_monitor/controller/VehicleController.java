package lk.ijse.crop_monitor.controller;

import lk.ijse.crop_monitor.customObj.VehicleResponse;
import lk.ijse.crop_monitor.dto.impl.VehicleDto;
import lk.ijse.crop_monitor.exception.DataPersistFailedException;
import lk.ijse.crop_monitor.exception.DuplicateLicensePlateException;
import lk.ijse.crop_monitor.exception.NotFoundException;
import lk.ijse.crop_monitor.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/vehicle")
@CrossOrigin
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;
    private static final Logger logger = LoggerFactory.getLogger(VehicleController.class);

    @PostMapping
    public ResponseEntity<Void> saveVehicle(@RequestBody VehicleDto vehicleDto) {
        logger.info("Request received to save vehicle: {}", vehicleDto);
        try {
            vehicleService.saveVehicle(vehicleDto);
            logger.info("Vehicle saved successfully: {}", vehicleDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DuplicateLicensePlateException e) {
            logger.error("Duplicate license plate found for vehicle: {}", vehicleDto, e);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (DataPersistFailedException e) {
            logger.error("Failed to persist vehicle: {}", vehicleDto, e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Unexpected error while saving vehicle: {}", vehicleDto, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{vehicleCode}")
    public ResponseEntity<VehicleResponse> getVehicle(@PathVariable("vehicleCode") String vehicleCode) {
        logger.info("Request received to fetch vehicle with code: {}", vehicleCode);
        try {
            VehicleResponse vehicleResponse = vehicleService.getVehicle(vehicleCode);
            logger.info("Vehicle fetched successfully: {}", vehicleResponse);
            return ResponseEntity.ok(vehicleResponse);
        } catch (NotFoundException e) {
            logger.error("Vehicle not found for code: {}", vehicleCode, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Unexpected error while fetching vehicle with code: {}", vehicleCode, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{vehicleCode}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable("vehicleCode") String vehicleCode) {
        logger.info("Request received to delete vehicle with code: {}", vehicleCode);
        try {
            vehicleService.deleteVehicle(vehicleCode);
            logger.info("Vehicle deleted successfully for code: {}", vehicleCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            logger.error("Vehicle not found for deletion with code: {}", vehicleCode, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Unexpected error while deleting vehicle with code: {}", vehicleCode, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{vehicleCode}")
    public ResponseEntity<Void> updateVehicle(@PathVariable("vehicleCode") String vehicleCode, @RequestBody VehicleDto vehicleDto) {
        logger.info("Request received to update vehicle with code: {}", vehicleCode);
        try {
            vehicleDto.setVehicleCode(vehicleCode);
            vehicleService.updateVehicle(vehicleDto);
            logger.info("Vehicle updated successfully: {}", vehicleDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DuplicateLicensePlateException e) {
            logger.error("Duplicate license plate during update for vehicle: {}", vehicleDto, e);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (NotFoundException e) {
            logger.error("Vehicle not found for update with code: {}", vehicleCode, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Unexpected error while updating vehicle with code: {}", vehicleCode, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<VehicleDto>> getAllVehicles() {
        logger.info("Request received to fetch all vehicles");
        try {
            List<VehicleDto> vehicles = vehicleService.getAllVehicle();
            logger.info("All vehicles fetched successfully: {}", vehicles);
            return ResponseEntity.ok(vehicles);
        } catch (Exception e) {
            logger.error("Unexpected error while fetching all vehicles", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
