package com.maruti.dcms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelList {
    @Id
    String modelName;
    String editedby;
    LocalDate editedon;
    int type;
    Boolean chart;
    String status;
    String description;
    Boolean active;
    Boolean actionDelete;
    int rowState;


}
