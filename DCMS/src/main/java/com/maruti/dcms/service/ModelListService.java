package com.maruti.dcms.service;

import com.maruti.dcms.dto.response.ModelListResponseDto;
import com.maruti.dcms.entity.ModelList;
import com.maruti.dcms.entity.ModelRecord;
import com.maruti.dcms.repository.ModelListRepository;
import com.maruti.dcms.repository.ModelRecordRepository;
import com.maruti.dcms.utility.ValueMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ModelListService {

    ModelRecordRepository modelRecordRepository;

    ModelListRepository modelrepo;

    public List<ModelListResponseDto> listAllModels()
    {
        List<ModelListResponseDto> modelDto = null;
      List<ModelList> model =  modelrepo.findAll();

      if(!model.isEmpty())
      {
          modelDto = model.stream()
                  .map(ValueMapper::convertToDTO)
                  .collect(Collectors.toList());

      } else {
          modelDto = Collections.emptyList();
      }return modelDto;

    }

    public List<ModelRecord> fetchAllModelRecords(){

        return modelRecordRepository.findAll();
    }
}
