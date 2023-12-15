package com.maruti.dcms.repository;

import com.maruti.dcms.entity.Subactivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubActivityRepository extends JpaRepository<Subactivity,Integer> {
    Subactivity findBySubactivityName(String type1);
}
