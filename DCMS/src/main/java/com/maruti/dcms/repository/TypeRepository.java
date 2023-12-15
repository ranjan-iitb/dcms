package com.maruti.dcms.repository;

import com.maruti.dcms.entity.TypeMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<TypeMaster, Integer> {
    TypeMaster findByName(String type);
}
