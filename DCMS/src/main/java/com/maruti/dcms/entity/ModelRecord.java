package com.maruti.dcms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelRecord {
    @Id
    @Column(name = "model_number")
    String modelNumber;
    @ManyToOne
    @JoinColumn(name="type", referencedColumnName = "id")
    TypeMaster type;
    String createdBy;
    String editedBy;
    LocalDate editedOn;
    Boolean activate;
    String designResponsibilty;
    String developmentResponsibilty;
    String description;
    int rowState;
}
