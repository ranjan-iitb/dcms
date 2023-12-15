package com.maruti.dcms.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;


@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ModelDataDTO {
    private String userEmail;
    private String modelName;
    private String type;
    private String designResponsibility;
    private String developmentResponsibility;
    private String description;
    private Map<String, Map<String, Map<String, String>>> milestones;

    // Getters and setters
}