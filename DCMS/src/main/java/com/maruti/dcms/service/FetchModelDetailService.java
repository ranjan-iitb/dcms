package com.maruti.dcms.service;

import com.maruti.dcms.dto.request.*;
import com.maruti.dcms.entity.MilestoneActivityRel;
import com.maruti.dcms.entity.ModelRecord;
import com.maruti.dcms.entity.Subactivity;
import com.maruti.dcms.repository.MilestoneActivityRelRepository;
import com.maruti.dcms.repository.ModelRecordRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class FetchModelDetailService {

    ModelRecordRepository modelRecordRepository;
    MilestoneActivityRelRepository milestoneActivityRelRepository;
    public ModelRecordDTO combineModelData(String modelName){


        GeneralInfo generalInfo = generalInfoFetch(modelName);
        ModelRecord modelRecord = new ModelRecord();
        modelRecord.setModelNumber(modelName);
        List<MilestoneActivityRel> milestoneActivityRel = milestoneActivityRelRepository.findAllByModelNumber_ModelNumber(modelName);


        ModelRecordDTO modelRecordDTO = new ModelRecordDTO();

        ModelData modelData = new ModelData();
        modelData.setGeneralInfo(generalInfo);
        Map<String, Milestone> milestoneMap = new HashMap<>();

                for(MilestoneActivityRel m : milestoneActivityRel)
                {
                    Milestone milestone = new Milestone();
                    milestone.setDeadline(String.valueOf(m.getMilestoneEndDate()));
                    Map<String, SubTask> subTaskMap = new HashMap<>();
                    SubTask subTask = new SubTask();

                    if(milestoneMap.containsKey(m.getMilestoneId().getMilestoneName()))
                    {
                        subTask.setStartTime(String.valueOf(m.getActivityStartDate()));
                        subTask.setEndTime(String.valueOf(m.getActivityEndDate()));

                        if(!subTaskMap.containsKey(m.getActivityId().getActivityName()))
                        {
                            subTaskMap.put(m.getActivityId().getActivityName(),subTask);
                        }
                        else{

                        }
                        milestone.setSubTask(subTaskMap);
                    }
                    else{
                        milestoneMap.put(m.getMilestoneId().getMilestoneName(),milestone);
                    }
                    //milestone.setSubTask(m.getActivityId().getActivityName());
                    //milestoneMap.put(m.getMilestoneId().getMilestoneName(),m.g)
                }
        modelData.setMilestones(milestoneMap);
                modelRecordDTO.setModelData(modelData);
                return  modelRecordDTO;

        //modelRecordDTO.setModelData(new ModelData(generalInfo, milestoneActivityRel));
    }
// general info fetch
    public GeneralInfo generalInfoFetch(String modelName){
        ModelRecord modelRecord =  modelRecordRepository.findByModelNumber(modelName);
        GeneralInfo generalInfo = new GeneralInfo();
        generalInfo.setType(modelRecord.getType().getName());
        generalInfo.setDescription(modelRecord.getDescription());
        generalInfo.setModelName(modelRecord.getModelNumber());
        generalInfo.setUserEmail(modelRecord.getCreatedBy());
        generalInfo.setDesignResponsibility(modelRecord.getDesignResponsibilty());
        generalInfo.setDevelopmentResponsibility(modelRecord.getDevelopmentResponsibilty());
        return generalInfo;
    }



// milestone name fetch

// activity name fetch




}
