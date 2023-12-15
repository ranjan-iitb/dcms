package com.maruti.dcms.service;

import com.maruti.dcms.dto.request.Milestone;
import com.maruti.dcms.dto.request.ModelRecordDTO;
import com.maruti.dcms.dto.request.SubTask;
import com.maruti.dcms.entity.*;
import com.maruti.dcms.exceptions.IncorrectDateFormatException;
import com.maruti.dcms.exceptions.ModelServiceActivityNotFoundException;
import com.maruti.dcms.exceptions.ModelServiceMilestoneNotFoundException;
import com.maruti.dcms.repository.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class ModelRecordService {
    private ActivityRepository activityRepository;
    private MilestoneActivityRelRepository milestoneActivityRelRepository;
    private MilestoneMasterRepository milestoneMasterRepository;
    private ModelRecordRepository modelRecordRepository;
    private SubActivityRepository subActivityRepository;
    private TypeRepository typeRepository;


    // method to persist the new model data in db

    @Transactional
    public void createNewModel(ModelRecordDTO modelRecordDTO) throws Exception {

            TypeMaster typeMaster = typeRepository.findByName(modelRecordDTO.getModelData().getGeneralInfo().getType());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            ModelRecord modelRecord = new ModelRecord();
            modelRecord.setModelNumber(modelRecordDTO.getModelData().getGeneralInfo().getModelName());
            modelRecord.setCreatedBy(modelRecordDTO.getModelData().getGeneralInfo().getUserEmail());
            modelRecord.setType(typeMaster);
            modelRecord.setDesignResponsibilty(modelRecordDTO.getModelData().getGeneralInfo().getDesignResponsibility());
            modelRecord.setDevelopmentResponsibilty(modelRecordDTO.getModelData().getGeneralInfo().getDevelopmentResponsibility());
            modelRecord.setDescription(modelRecordDTO.getModelData().getGeneralInfo().getDescription());
            modelRecord.setEditedBy(modelRecordDTO.getModelData().getGeneralInfo().getUserEmail());
            modelRecord.setEditedOn(LocalDate.now());
            modelRecord.setActivate(Boolean.TRUE);
            modelRecord.setRowState(1);
            modelRecordRepository.save(modelRecord);
            List<MilestoneMaster> milestoneMastersList = new ArrayList<>();
            Map<String, Milestone> milestoneMap = modelRecordDTO.getModelData().getMilestones();

            for (Map.Entry<String, Milestone> milestoneEntry : milestoneMap.entrySet()) {
                MilestoneMaster milestoneMaster = milestoneMasterRepository.findByMilestoneName(milestoneEntry.getKey());
                if (milestoneMaster == null) {
                    throw new Exception("This milestone " + milestoneEntry.getKey() + " does not exist or there is difference in name");
                }

                List<Activity> activityList = new ArrayList<>();
                for (Map.Entry<String, SubTask> activityEntry : milestoneEntry.getValue().getSubTask().entrySet()) {
                    Activity activity = activityRepository.findByActivityName(activityEntry.getKey());
                    if (activity == null) {
                        throw new Exception("This activity " + activityEntry.getKey() + " does not exist or there is difference in name");
                    }
                    Subactivity subActivity = subActivityRepository.findBySubactivityName("Type1");
                    MilestoneActivityRel milestoneActivityRel = new MilestoneActivityRel();
                    milestoneActivityRel.setMilestoneId(milestoneMaster);
                    milestoneActivityRel.setActivityId(activity);
                    milestoneActivityRel.setModelNumber(modelRecord);
                    milestoneActivityRel.setSubactivityId(subActivity);
                    milestoneActivityRel.setMilestoneEndDate(LocalDate.parse(milestoneEntry.getValue().getDeadline(), formatter));
                    milestoneActivityRel.setActivityStartDate(LocalDate.parse(activityEntry.getValue().getStartTime(), formatter));
                    milestoneActivityRel.setActivityEndDate(LocalDate.parse(activityEntry.getValue().getEndTime(), formatter));
                    milestoneActivityRel.setSubactivityStartDate(LocalDate.now());
                    milestoneActivityRel.setSubactivityEndDate(LocalDate.now());
                    milestoneActivityRelRepository.save(milestoneActivityRel);

                }

            }



    }


    public void fillMilestone(){
        MilestoneMaster milestoneMaster = new MilestoneMaster();
        milestoneMaster.setMilestoneName("MPP_Trails");
        milestoneMaster.setMilestoneIcon("*");
        milestoneMaster.setRowState(1);
        MilestoneMaster milestoneMaster1 = new MilestoneMaster();
        milestoneMaster1.setMilestoneName("Pilot");
        milestoneMaster1.setMilestoneIcon("**");
        milestoneMaster1.setRowState(1);
        MilestoneMaster milestoneMaster3 = new MilestoneMaster();
        milestoneMaster3.setMilestoneName("Soap");
        milestoneMaster3.setMilestoneIcon("*");
        milestoneMaster3.setRowState(1);
        MilestoneMaster milestoneMaster4 = new MilestoneMaster();
        milestoneMaster4.setMilestoneName("PP_Trails");
        milestoneMaster4.setMilestoneIcon("*");
        milestoneMaster4.setRowState(1);

        milestoneMasterRepository.save(milestoneMaster);
        milestoneMasterRepository.save(milestoneMaster1);
        milestoneMasterRepository.save(milestoneMaster3);
       // milestoneMasterRepository.save(milestoneMaster4);



    }
    public void fillActivity(){

        List<Subactivity> activityList = new ArrayList<>();
       // String[] activityName = {"DCMS", "Design Check Sheet-1", "Design Check Sheet-2", "Prior Mule verification", "Designer CAE-Rough", "Designer CAE-Final", "SES QC-Rough", "SES QC-Final", "Kickoff / Strategy DR", "Development DR", "Merchantability DR", "ControlConcept DR", "ISO 26262 verification review", "Concept DR", "FMEA DR", "Aerodynamics DR-1", "Cross Section DR", "Body Design Section DR", "Rust DR-1", "MGR DR-1", "Final Req. DR", "Mikri DR-1", "Condensation DR-1", "Condensation DR-2", "MGR DR-2", "Mikri DR-2", "UNR DR", "MR DR/A surface DR", "DMDR", "Rust DR-2", "Aerodynamics DR-2", "Drawing DR", "Appearance proto DR", "Structure Briefing DR", "Tool Go Ahead DR", "Supplier DR", "Engineering Dr/Static DR", "Design Quality Gate", "Review DR", "Homologation-Certificate" };
        String[] subActivity = {"Type1", "Type2","Type3"};
        for(String s : subActivity)
        {
            Subactivity activity = new Subactivity();
            activity.setSubactivityName(s);
            activity.setRowState(1);
            activityList.add(activity);
        }
        subActivityRepository.saveAll(activityList);

    }
    public void fillType(){
        TypeMaster typeMaster = new TypeMaster();
        typeMaster.setName("1");
        typeMaster.setRowState(1);
        TypeMaster typeMaster1 = new TypeMaster();
        typeMaster1.setName("2");
        typeMaster1.setRowState(1);
        TypeMaster typeMaster2 = new TypeMaster();
        typeMaster2.setName("3");
        typeMaster2.setRowState(1);

        typeRepository.save(typeMaster);
        typeRepository.save(typeMaster1);
        typeRepository.save(typeMaster2);

    }
    public void fillSubActivity(){

    }
}
