package com.maruti.dcms.controller;


import com.maruti.dcms.dto.ApiResponse;
import com.maruti.dcms.dto.request.ModelRecordDTO;
import com.maruti.dcms.dto.response.ModelDataDTO;
import com.maruti.dcms.service.FetchModelDetailService;
import com.maruti.dcms.service.ModelRecordService;
import com.maruti.dcms.service.ModelRecordServiceNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/editmodel")
public class ModelDetailController {

    @Autowired
    FetchModelDetailService fetchModelDetailService;
@Autowired
    ModelRecordServiceNew modelRecordServiceNew;

    @GetMapping
    public ApiResponse<ModelDataDTO> getModelDetails(@RequestBody Map<String, String> modelname)
    {
       String selectedModelName = modelname.get("modelName");
        //ModelRecordDTO m = fetchModelDetailService.combineModelData(selectedModelName);
        ModelDataDTO m = modelRecordServiceNew.getModelData(selectedModelName);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResults(m);
        apiResponse.setStatus("Success");
        return apiResponse;
    }



}
