package com.maruti.dcms.repository;

import com.maruti.dcms.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity,Integer> {
    Activity findByActivityName(String key);
}
