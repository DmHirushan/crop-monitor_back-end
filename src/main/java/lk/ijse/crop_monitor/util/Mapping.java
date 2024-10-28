package lk.ijse.crop_monitor.util;

import lk.ijse.crop_monitor.dto.impl.CropDto;
import lk.ijse.crop_monitor.entity.Crop;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    public Crop convertToEntity(CropDto dto) {
        return modelMapper.map(dto, Crop.class);
    }
}
