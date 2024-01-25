package com.agg.certificados.services.dataManagerServices;

import com.agg.certificados.dtos.response.DataManagerResponseDto;
import com.agg.certificados.entity.DataManager;
import com.agg.certificados.mapper.IMapStructMapper;
import com.agg.certificados.repositories.dataManager.IDataManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class DataManagerService implements  IDataManagerService{

    @Autowired
    private IDataManagerRepository dataManagerRepository;
    @Autowired
    private IMapStructMapper mapStructMapper;

    public List<DataManagerResponseDto> getAll(){
        List<DataManager> dataManagers = dataManagerRepository.findAll();
        List<DataManagerResponseDto> listResponse = new ArrayList<>();
        for (DataManager item: dataManagers){

            listResponse.add(mapStructMapper.DataManagerToDataManagerResponseDto(item));

        }

        return listResponse;

    }
    public DataManagerResponseDto getbyId(Long id){
        DataManager dataManager = dataManagerRepository.findById(id).orElse(null);
        return mapStructMapper.DataManagerToDataManagerResponseDto(dataManager);
    }

}
