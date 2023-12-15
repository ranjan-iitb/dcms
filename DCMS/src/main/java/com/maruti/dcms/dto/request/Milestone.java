package com.maruti.dcms.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Milestone {

    @JsonProperty("Deadline")
    private String deadline;
    @JsonProperty("subTask")
    private Map<String, SubTask> subTask;
}
