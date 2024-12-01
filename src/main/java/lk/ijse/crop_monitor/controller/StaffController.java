package lk.ijse.crop_monitor.controller;

import lk.ijse.crop_monitor.customObj.StaffResponse;
import lk.ijse.crop_monitor.dto.impl.StaffDto;
import lk.ijse.crop_monitor.exception.DataPersistFailedException;
import lk.ijse.crop_monitor.exception.NotFoundException;
import lk.ijse.crop_monitor.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(StaffController.class);

    @PostMapping
    public ResponseEntity<Void> saveStaffMember(@RequestBody StaffDto staffDto) {
        logger.info("Request to save staff member: {}", staffDto);
        try {
            staffService.saveStaffMember(staffDto);
            logger.info("Staff member saved successfully: {}", staffDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            logger.error("Failed to persist staff member: {}", staffDto, e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Unexpected error while saving staff member: {}", staffDto, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public StaffResponse getStaffMember(@PathVariable("id") String id) {
        logger.info("Fetching staff member with ID: {}", id);
        StaffResponse response = staffService.getStaffMember(id);
        logger.info("Fetched staff member details: {}", response);
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaffMember(@PathVariable("id") String id) {
        logger.info("Request to delete staff member with ID: {}", id);
        try {
            staffService.deleteStaffMember(id);
            logger.info("Staff member deleted successfully, ID: {}", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            logger.error("Staff member not found for deletion, ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Unexpected error while deleting staff member, ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public List<StaffDto> getAllStaffMembers() {
        logger.info("Fetching all staff members.");
        List<StaffDto> staffMembers = staffService.getAllStaffMembers();
        logger.info("Fetched {} staff members successfully.", staffMembers.size());
        return staffMembers;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateStaffMember(@PathVariable("id") String id, @RequestBody StaffDto staffDto) {
        logger.info("Request to update staff member with ID: {}", id);
        try {
            staffDto.setId(id);
            staffService.updateStaffMember(staffDto);
            logger.info("Staff member updated successfully: {}", staffDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            logger.error("Staff member not found for update, ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Unexpected error while updating staff member, ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
