package lk.ijse.crop_monitor.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.crop_monitor.customObj.VehicleResponse;
import lk.ijse.crop_monitor.customObj.impl.CropDetailErrorResponse;
import lk.ijse.crop_monitor.customObj.impl.VehicleErrorResponse;
import lk.ijse.crop_monitor.dto.impl.CropDetailsDto;
import lk.ijse.crop_monitor.dto.impl.VehicleDto;
import lk.ijse.crop_monitor.entity.CropDetails;
import lk.ijse.crop_monitor.entity.Staff;
import lk.ijse.crop_monitor.entity.Vehicle;
import lk.ijse.crop_monitor.exception.DataPersistFailedException;
import lk.ijse.crop_monitor.exception.DuplicateLicensePlateException;
import lk.ijse.crop_monitor.exception.NotFoundException;
import lk.ijse.crop_monitor.repository.StaffRepository;
import lk.ijse.crop_monitor.repository.VehicleRepository;
import lk.ijse.crop_monitor.service.VehicleService;
import lk.ijse.crop_monitor.util.AppUtil;
import lk.ijse.crop_monitor.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;
    private final Mapping mapping;
    private final StaffRepository staffRepository;

    @Override
    public void saveVehicle(VehicleDto vehicleDto) {
        List<VehicleDto> allVehicle = getAllVehicle();
        for (int i=0; i<allVehicle.size(); i++){
            if (allVehicle.get(i).getLicensePlateNumber().equals(vehicleDto.getLicensePlateNumber())){
                throw new DuplicateLicensePlateException("This license plate number already exists :" + vehicleDto.getLicensePlateNumber());
            }
        }
        vehicleDto.setVehicleCode(AppUtil.createVehicleCode());
        Vehicle savedVehicle = vehicleRepository.save(mapping.convertToEntity(vehicleDto, Vehicle.class));
        if (savedVehicle == null && savedVehicle.getVehicleCode() == null){
            throw new DataPersistFailedException("Can't save vehicle");
        }
    }

    @Override
    public VehicleResponse getVehicle(String vehicleCode) {
        if (vehicleRepository.existsById(vehicleCode)){
            return mapping.convertToDto(vehicleRepository.getVehicleByVehicleCode(vehicleCode), VehicleDto.class);
        }else {
            return new VehicleErrorResponse(0, "Vehicle not found");
        }
    }

    @Override
    public void deleteVehicle(String vehicleCode) {
        Optional<Vehicle> selectedVehicle = vehicleRepository.findById(vehicleCode);
        if (!selectedVehicle.isPresent()){
            throw new NotFoundException("Vehicle Not Found");
        }else {
            vehicleRepository.deleteById(vehicleCode);
        }
    }

    @Override
    public void updateVehicle(VehicleDto vehicleDto) {
        Vehicle vehicle = vehicleRepository.findById(vehicleDto.getVehicleCode())
                .orElseThrow(() -> new NotFoundException("vehicle not found"));

        List<VehicleDto> allVehicle = getAllVehicle();
        for (int i=0; i<allVehicle.size(); i++){
            if (allVehicle.get(i).getLicensePlateNumber().equals(vehicleDto.getLicensePlateNumber())){
                throw new DuplicateLicensePlateException("This license plate number already exists :" + vehicleDto.getLicensePlateNumber());
            }
        }
        vehicle.setStatus(vehicleDto.getStatus());
        vehicle.setRemarks(vehicleDto.getRemarks());
        vehicle.setStaff(staffRepository.getStaffMemberById(vehicleDto.getStaffId()));
        vehicleRepository.save(vehicle);
    }

    @Override
    public List<VehicleDto> getAllVehicle() {
        return mapping.convertToDto(vehicleRepository.findAll(), VehicleDto.class);
    }
}
