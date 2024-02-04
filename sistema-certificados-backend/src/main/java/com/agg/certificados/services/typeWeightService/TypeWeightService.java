package com.agg.certificados.services.typeWeightService;

import com.agg.certificados.dtos.response.TypeWeightResponseDto;
import com.agg.certificados.entity.TypeWeight;
import com.agg.certificados.mapper.IMapStructMapper;
import com.agg.certificados.repositories.typeWeightRepository.ITypeWeightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeWeightService implements ITypeWeightService {
    @Autowired
    private ITypeWeightRepository typeWeightRepository;

    @Autowired
    private IMapStructMapper mapStructMapper;

    public List<TypeWeightResponseDto> getActive(){
        List<TypeWeight> entities = typeWeightRepository.findAll();

        List<TypeWeightResponseDto> listDto = new ArrayList<>();

        for (TypeWeight entity:entities) {

            if (entity.status){

                listDto.add(mapStructMapper.TypeWeightToTypeWeightResponseDto(entity));
            }

        }

        return listDto;
    }
}
