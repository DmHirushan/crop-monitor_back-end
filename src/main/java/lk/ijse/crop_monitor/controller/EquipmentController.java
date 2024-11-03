package lk.ijse.crop_monitor.controller;

import lk.ijse.crop_monitor.customObj.EquipmentResponse;
import lk.ijse.crop_monitor.customObj.StaffResponse;
import lk.ijse.crop_monitor.dto.impl.EquipmentDto;
import lk.ijse.crop_monitor.dto.impl.StaffDto;
import lk.ijse.crop_monitor.exception.DataPersistFailedException;
import lk.ijse.crop_monitor.exception.NotFoundException;
import lk.ijse.crop_monitor.service.EquipmentService;
import lombok.RequiredArgsConstructor;
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

    @PostMapping
    public ResponseEntity<Void> saveEquipment(@RequestBody EquipmentDto equipmentDto){
        try{
            equipmentService.saveEquipment(equipmentDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistFailedException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            throw e;
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{equipmentId}")
    public EquipmentResponse getEquipment(@PathVariable ("equipmentId") String equipmentId){
        return equipmentService.getEquipment(equipmentId);
    }

    @DeleteMapping("/{equipmentId}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable ("equipmentId") String equipmentId){
        try{
            equipmentService.deleteEquipment(equipmentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public List<EquipmentDto> getAllEquipments(){
        return equipmentService.getAllEquipments();
    }

    @PatchMapping("/{equipmentId}")
    public ResponseEntity<Void> updateEquipment(@PathVariable ("equipmentId") String equipmentId, @RequestBody EquipmentDto equipmentDto){
        try{
            equipmentDto.setEquipmentId(equipmentId);
            equipmentService.updateEquipment(equipmentDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
