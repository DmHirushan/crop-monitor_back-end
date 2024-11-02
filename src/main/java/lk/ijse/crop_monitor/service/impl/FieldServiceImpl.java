package lk.ijse.crop_monitor.service.impl;

import lk.ijse.crop_monitor.customObj.CropResponse;
import lk.ijse.crop_monitor.dto.impl.CropDto;
import lk.ijse.crop_monitor.repository.FieldRepository;
import lk.ijse.crop_monitor.service.FieldService;
import lk.ijse.crop_monitor.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FieldServiceImpl implements FieldService {
    private final FieldRepository fieldRepository;
    private final Mapping mapping;

    @Override
    public void saveField(CropDto cropDto) {

    }

    @Override
    public CropResponse getField(String cropCode) {
        return null;
    }

    @Override
    public void deleteField(String cropCode) {

    }

    @Override
    public void updateField(CropDto cropDto) {

    }

    @Override
    public List<CropDto> getAllFields() {
        return List.of();
    }
}
