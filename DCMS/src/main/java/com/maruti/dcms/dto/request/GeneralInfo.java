package com.maruti.dcms.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneralInfo {
    @JsonProperty("NtUser")
    private String ntUser;
    @JsonProperty("user_email")
    private String userEmail;
    @JsonProperty("Model_Name")
    private String modelName;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("Design_Responsibility")
    private String designResponsibility;
    @JsonProperty("Developement_Responsilbity")
    private String developmentResponsibility;
    @JsonProperty("Description")
    private String description;
}
