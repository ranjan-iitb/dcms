package com.maruti.dcms.repository;

import com.maruti.dcms.entity.MilestoneMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilestoneMasterRepository extends JpaRepository<MilestoneMaster, Integer> {

    MilestoneMaster findByMilestoneName(String key);
}
