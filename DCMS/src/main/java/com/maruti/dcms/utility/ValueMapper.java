package com.maruti.dcms.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maruti.dcms.dto.response.ModelListResponseDto;
import com.maruti.dcms.entity.ModelList;

public class ValueMapper {

    public static ModelListResponseDto convertToDTO(ModelList modelList)
    {
        ModelListResponseDto modelDto = new ModelListResponseDto();
        modelDto.setModelName(modelList.getModelName());
        modelDto.setChart(modelList.getChart());
        modelDto.setActive(modelList.getActive());
        modelDto.setDescription(modelList.getDescription());
        modelDto.setEditedby(modelList.getEditedby());
        modelDto.setEditedon(modelList.getEditedon());
        modelDto.setActionDelete(modelList.getActionDelete());
        modelDto.setType(modelList.getType());
        modelDto.setStatus(modelList.getStatus());

        return modelDto;
    }

    public static String jsonAsString(Object obj){
        try {
            return new ObjectMapper().writeValueAsString(obj);
        }catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
