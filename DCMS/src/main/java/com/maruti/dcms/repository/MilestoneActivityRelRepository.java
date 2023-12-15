package com.maruti.dcms.repository;

import com.maruti.dcms.entity.MilestoneActivityRel;
import com.maruti.dcms.entity.ModelRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MilestoneActivityRelRepository extends JpaRepository<MilestoneActivityRel, Integer> {
    List<MilestoneActivityRel> findAllByModelNumber_ModelNumber(String modelName);

   // List<MilestoneActivityRel> findByModelName(String modelNumber);
}
