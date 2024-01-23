package com.agg.certificados.services.typeDocumentservices;

import com.agg.certificados.dtos.response.BotaderoResponseDto;
import com.agg.certificados.dtos.response.TypeDocumentResponseDto;
import com.agg.certificados.entity.Botadero;
import com.agg.certificados.entity.TypeDocument;
import com.agg.certificados.entity.User;
import com.agg.certificados.mapper.IMapStructMapper;
import com.agg.certificados.repositories.typeDocumentRepository.ITypeDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TypeDocumentService implements ITypeDocumentService{
    @Autowired
    private ITypeDocumentRepository typeDocumentRepository;

    @Autowired
    private IMapStructMapper mapStructMapper;

    public List<TypeDocumentResponseDto> getActive(){
        List<TypeDocument> entities = typeDocumentRepository.findAll();

        List<TypeDocumentResponseDto> listDto = new ArrayList<>();

        for (TypeDocument entity:entities) {

            if (entity.status == true){

                listDto.add(mapStructMapper.TypeDocumentToTypeDocumentResponseDto(entity));
            }

        }

        return listDto;
    }
}
