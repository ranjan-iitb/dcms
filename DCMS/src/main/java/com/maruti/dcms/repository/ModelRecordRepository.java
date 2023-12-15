package com.maruti.dcms.repository;

import com.maruti.dcms.dto.request.GeneralInfo;
import com.maruti.dcms.entity.ModelRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRecordRepository extends JpaRepository<ModelRecord, String> {

    ModelRecord findByModelNumber(String modelName);
}
