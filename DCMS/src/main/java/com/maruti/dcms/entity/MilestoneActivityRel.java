package com.maruti.dcms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MilestoneActivityRel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable=false, updatable=false)
    int id;
    @ManyToOne
    @JoinColumn(name="milestone_id",referencedColumnName="milestone_id")
    MilestoneMaster milestoneId;

    @ManyToOne
    @JoinColumn(name="activity_id", referencedColumnName = "id")
    Activity activityId;

    @ManyToOne
    @JoinColumn(name="subactivity_id", referencedColumnName = "id")
    Subactivity subactivityId;

    @ManyToOne
    @JoinColumn(name="model_name", referencedColumnName = "model_number")
    //@Column(name = "model_name")
    ModelRecord modelNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    LocalDate milestoneEndDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    LocalDate activityStartDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    LocalDate activityEndDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    LocalDate subactivityStartDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    LocalDate subactivityEndDate;

    int rowState;
}
