package com.agg.certificados.mapper;

import com.agg.certificados.dtos.request.QuantitiesRcdRequestDto;
import com.agg.certificados.dtos.response.*;
import com.agg.certificados.entity.*;
import com.agg.certificados.repositories.typeRcdRepository.ITypeRcdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapStructMapper implements IMapStructMapper{

    @Autowired
    private ITypeRcdRepository typeRcdRepository;
    @Override
    public QuantitiesRcd QuantitiesRcdRequestDtoToQuantitiesRcd(QuantitiesRcdRequestDto dto, DataGenerator dataGenerator) {

        QuantitiesRcd entity = new QuantitiesRcd();

        entity.setQuantity_rcd(dto.quantity_rcd);
        entity.setType_rcd_id(typeRcdRepository.findById(dto.type_rcd_id).orElse(null));
        entity.setData_generator_id(dataGenerator);
        return entity;
    }

    @Override
    public DataGeneratorResponseDto DataGeneratorToDataGeneratorResponseDto(DataGenerator entity){
        DataGeneratorResponseDto dto = new DataGeneratorResponseDto();


        dto.id_data_generator = entity.id_data_generator;
        dto.botadero = new BotaderoResponseDto(entity.botadero_id);
        dto.data_manager = DataManagerToDataManagerResponseDto(entity.data_manager_id);

        dto.unic_number = entity.unic_number;
        dto.name = entity.name;
        dto.type_document = TypeDocumentToTypeDocumentResponseDto(entity.type_document_id);
        dto.number_id = entity.number_id;
        dto.legal_representative = entity.legal_representative;
        dto.address =  entity.address;
        dto.phone_number = entity.phone_number;
        dto.email = entity.email;
        dto.address_rcd = entity.address_rcd;
        dto.reception_date_rcd = entity.reception_date_rcd;
        dto.total_rcd = entity.total_rcd;

        return dto;
    }

    @Override
    public DataManagerResponseDto DataManagerToDataManagerResponseDto(DataManager entity){

        DataManagerResponseDto dto = new DataManagerResponseDto();

        dto.id_data_manager = entity.id_data_manager;
        dto.unic_number = entity.unic_number;
        dto.name = entity.name;
        dto.type_document = TypeDocumentToTypeDocumentResponseDto(entity.type_document_id);
        dto.number_id = entity.number_id;
        dto.legal_representative = entity.legal_representative;
        dto.address =  entity.address;
        dto.phone_number = entity.phone_number;
        dto.email = entity.email;

        return dto;
    }


    @Override
    public ManagerResponseDto ManagerToManagerResponseDto(Manager entity){

        ManagerResponseDto dto = new ManagerResponseDto();

        dto.id_manager = entity.id_manager;
        dto.name = entity.name;
        dto.status = entity.status;

        return dto;
    }

    @Override
    public TypeDocumentResponseDto TypeDocumentToTypeDocumentResponseDto(TypeDocument entity){

        TypeDocumentResponseDto dto = new TypeDocumentResponseDto();

        dto.id_type_document = entity.id_type_document;
        dto.name = entity.name;
        dto.description = entity.description;
        dto.status = entity.status;

        return dto;
    }

    @Override
    public DataDriverResponseDto DataDriverToDataDriverResponseDto(DataDriver entity){

        DataDriverResponseDto dto = new DataDriverResponseDto();

        dto.id_data_driver = entity.id_data_driver;
        dto.data_generator_id = entity.data_generator_id.getId_data_generator();
        dto.name = entity.name;
        dto.type_document = TypeDocumentToTypeDocumentResponseDto(entity.type_document_id);
        dto.number_id = entity.number_id;
        dto.vehicle_plate = entity.vehicle_plate;

        return dto;
    }

    @Override
    public QuantitiesRcdResponseDto QuantitiesRcdToQuantitiesRcdResponseDto(QuantitiesRcd entity){

        QuantitiesRcdResponseDto dto = new QuantitiesRcdResponseDto();

        dto.id_quantities_rcd = entity.id_quantities_rcd;
        dto.type_rcd = TypeRcdToTypeRcdResponseDto(entity.type_rcd_id);
        dto.data_generator_id = entity.data_generator_id.getId_data_generator();
        dto.quantity_rcd = entity.quantity_rcd;

        return dto;
    }

    @Override
    public TypeRcdResponseDto TypeRcdToTypeRcdResponseDto(TypeRcd entity){
        TypeRcdResponseDto dto = new TypeRcdResponseDto();

        dto.id_type_rcd = entity.id_type_rcd;
        dto.name = entity.name;
        dto.description = entity.description;
        dto.status = entity.status;

        return dto;

    }


}
