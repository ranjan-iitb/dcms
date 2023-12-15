package com.maruti.dcms.service;

import com.maruti.dcms.dto.response.ModelDataDTO;
import com.maruti.dcms.entity.MilestoneActivityRel;
import com.maruti.dcms.entity.ModelRecord;
import com.maruti.dcms.repository.MilestoneActivityRelRepository;
import com.maruti.dcms.repository.ModelRecordRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ModelRecordServiceNew {
    @Autowired
    private ModelRecordRepository modelRecordRepository;

    @Autowired
    private MilestoneActivityRelRepository milestoneActivityRelRepository;

    public ModelDataDTO getModelData(String modelNumber) {
        // Fetch model record entity
        ModelRecord modelRecordEntity = modelRecordRepository.findById(modelNumber)
                .orElseThrow(() -> new EntityNotFoundException("Model record not found"));

        // Fetch related milestone activity relationships
        List<MilestoneActivityRel> milestoneActivityRelEntities = milestoneActivityRelRepository
                .findAllByModelNumber_ModelNumber(modelNumber);

        // Convert entities to DTO
        ModelDataDTO modelDataDTO = convertToModelDataDTO(modelRecordEntity, milestoneActivityRelEntities);

        return modelDataDTO;
    }

    private ModelDataDTO convertToModelDataDTO(ModelRecord modelRecordEntity,
                                               List<MilestoneActivityRel> milestoneActivityRelEntities) {
        // Convert entities to DTO format
        ModelDataDTO modelDataDTO = new ModelDataDTO();
        modelDataDTO.setUserEmail(modelRecordEntity.getCreatedBy());
        modelDataDTO.setModelName(modelRecordEntity.getModelNumber());
        modelDataDTO.setType(String.valueOf(modelRecordEntity.getType()));
        modelDataDTO.setDesignResponsibility(modelRecordEntity.getDesignResponsibilty());
        modelDataDTO.setDevelopmentResponsibility(modelRecordEntity.getDevelopmentResponsibilty());
        modelDataDTO.setDescription(modelRecordEntity.getDescription());

        // Populate milestones and subtasks
        Map<String, Map<String, Map<String, String>>> milestonesMap = new LinkedHashMap<>();
        for (MilestoneActivityRel relEntity : milestoneActivityRelEntities) {
            Map<String, Map<String, String>> milestoneData = milestonesMap
                    .computeIfAbsent(relEntity.getMilestoneId().getMilestoneName(), k -> new LinkedHashMap<>());

            milestoneData.put("Deadline", formatDate(relEntity.getMilestoneEndDate()));

            Map<String, String> subtaskData = milestoneData
                    .computeIfAbsent(relEntity.getActivityId().getActivityName(), k -> new LinkedHashMap<>());
            subtaskData.put("start_time", String.valueOf(relEntity.getActivityStartDate()));
            subtaskData.put("end_time", String.valueOf(relEntity.getActivityEndDate()));
        }

        modelDataDTO.setMilestones(milestonesMap);

        return modelDataDTO;
    }

    private Map<String, String> formatDate(LocalDate date) {
        // Format LocalDate as needed
        // For simplicity, using default toString() method
        Map<String, String> formattedDate = new LinkedHashMap<>();
        formattedDate.put("date", date.toString());
        return formattedDate;
    }
}
