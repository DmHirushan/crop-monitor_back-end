package lk.ijse.crop_monitor.controller;

import lk.ijse.crop_monitor.customObj.StaffResponse;
import lk.ijse.crop_monitor.customObj.VehicleResponse;
import lk.ijse.crop_monitor.dto.impl.StaffDto;
import lk.ijse.crop_monitor.dto.impl.VehicleDto;
import lk.ijse.crop_monitor.exception.DataPersistFailedException;
import lk.ijse.crop_monitor.exception.DuplicateLicensePlateException;
import lk.ijse.crop_monitor.exception.NotFoundException;
import lk.ijse.crop_monitor.service.VehicleService;
import lombok.RequiredArgsConstructor;
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

    @PostMapping
    public ResponseEntity<Void> saveVehicle(@RequestBody VehicleDto vehicleDto){
        try {
            vehicleService.saveVehicle(vehicleDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DuplicateLicensePlateException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (DataPersistFailedException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{vehicleCode}")
    public VehicleResponse getStaffMember(@PathVariable ("vehicleCode") String vehicleCode){
        return vehicleService.getVehicle(vehicleCode);
    }

    @DeleteMapping("/{vehicleCode}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable ("vehicleCode") String vehicleCode){
        try{
            vehicleService.deleteVehicle(vehicleCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{vehicleCode}")
    public ResponseEntity<Void> updateVehicle(@PathVariable ("vehicleCode") String vehicleCode, @RequestBody VehicleDto vehicleDto){
        try{
            vehicleDto.setVehicleCode(vehicleCode);
            vehicleService.updateVehicle(vehicleDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (DuplicateLicensePlateException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public List<VehicleDto> getAllCropDetails(){
        return vehicleService.getAllVehicle();
    }

}
