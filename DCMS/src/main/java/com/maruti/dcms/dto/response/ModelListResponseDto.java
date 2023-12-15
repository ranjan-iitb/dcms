package com.maruti.dcms.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ModelListResponseDto {
    String modelName;
    String editedby;
    LocalDate editedon;
    int type;
    Boolean chart;
    String status;
    String description;
    Boolean active;
    Boolean actionDelete;
    @JsonIgnore
    int rowState;
}
