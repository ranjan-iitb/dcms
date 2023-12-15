package com.maruti.dcms.controller;

import com.google.gson.Gson;
import com.maruti.dcms.dto.ApiResponse;
import com.maruti.dcms.dto.response.ModelListResponseDto;
import com.maruti.dcms.entity.ModelList;
import com.maruti.dcms.entity.ModelRecord;
import com.maruti.dcms.service.ModelListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ModelListController {
    @Autowired
    ModelListService ns;
    @GetMapping("/dcms/allmodel")
    public ApiResponse<List<ModelListResponseDto>> fetchAllModel()
    {
       List<ModelRecord> models = ns.fetchAllModelRecords();
       List<ModelListResponseDto> modelListResponseDtos = new ArrayList<>();
       for(ModelRecord m : models)
       {
           ModelListResponseDto mdt = new ModelListResponseDto();
           mdt.setModelName(m.getModelNumber());
          if(m.getRowState()==1){

          }

          modelListResponseDtos.add(mdt);
       }
       ApiResponse<List<ModelListResponseDto>> responseDto = new ApiResponse<>();
       responseDto.setStatus("OK");
       responseDto.setResults(modelListResponseDtos);


       return responseDto;


    }


}
