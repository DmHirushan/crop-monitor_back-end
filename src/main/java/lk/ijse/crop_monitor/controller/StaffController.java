package lk.ijse.crop_monitor.controller;

import lk.ijse.crop_monitor.customObj.StaffResponse;
import lk.ijse.crop_monitor.dto.impl.CropDetailsDto;
import lk.ijse.crop_monitor.dto.impl.CropDto;
import lk.ijse.crop_monitor.dto.impl.StaffDto;
import lk.ijse.crop_monitor.exception.DataPersistFailedException;
import lk.ijse.crop_monitor.exception.NotFoundException;
import lk.ijse.crop_monitor.service.StaffService;
import lk.ijse.crop_monitor.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/staff")
@CrossOrigin
@RequiredArgsConstructor
public class StaffController {
    private final StaffService staffService;

    @PostMapping
    public ResponseEntity<Void> saveStaffMember(@RequestBody StaffDto staffDto){
        try{
            staffService.saveStaffMember(staffDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistFailedException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public StaffResponse getStaffMember(@PathVariable ("id") String id){
        return staffService.getStaffMember(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaffMember(@PathVariable ("id") String id){
        try{
            staffService.deleteStaffMember(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public List<StaffDto> getAllCropDetails(){
        return staffService.getAllStaffMembers();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateStaffMember(@PathVariable ("id") String id, @RequestBody StaffDto staffDto){
        try{
            staffDto.setId(id);
            staffService.updateStaffMember(staffDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
