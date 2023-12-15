package com.maruti.dcms.controller;

import com.maruti.dcms.dto.ApiResponse;
import com.maruti.dcms.dto.request.ModelRecordDTO;

import com.maruti.dcms.service.ModelRecordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/create")
public class ModelCreateController {

    @Autowired
    ModelRecordService modelRecordService;


    @PostMapping
    public ApiResponse<ModelRecordDTO> createNewModel(@RequestBody ModelRecordDTO modelRecordDTO) throws Exception {

        modelRecordService.createNewModel(modelRecordDTO);
        ApiResponse<ModelRecordDTO> ar = new ApiResponse<>();
        ar.setStatus("ok model created ");
        ar.setResults(modelRecordDTO);

       // System.out.println(ValueMapper.jsonAsString(modelRecordDTO));


        System.out.println();
        return ar;
       // return modelRecordDTO;
    }

    @GetMapping ("/milestone")
    public ApiResponse<String>createMilestone(){
        //modelRecordService.fillActivity();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResults("sucess");
        return apiResponse;

    }

}
