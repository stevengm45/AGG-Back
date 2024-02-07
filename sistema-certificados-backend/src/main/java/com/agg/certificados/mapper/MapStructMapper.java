package com.agg.certificados.mapper;

import com.agg.certificados.dtos.request.ManagerDataGeneratorRequestDto;
import com.agg.certificados.dtos.request.QuantitiesRcdRequestDto;
import com.agg.certificados.dtos.response.*;
import com.agg.certificados.entity.*;
import com.agg.certificados.repositories.typeRcdRepository.ITypeRcdRepository;
import com.agg.certificados.utils.ManagerEnum;
import com.agg.certificados.utils.TypeRcdEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapStructMapper implements IMapStructMapper{

    @Autowired
    private ITypeRcdRepository typeRcdRepository;
    @Override
    public QuantitiesRcd QuantitiesRcdRequestDtoToQuantitiesRcd(QuantitiesRcdRequestDto dto, DataGenerator dataGenerator) {

        QuantitiesRcd entity = new QuantitiesRcd();

//        entity.setQuantity_rcd(dto.quantity_rcd);
//        entity.setType_rcd_id(typeRcdRepository.findById(dto.type_rcd_id).orElse(null));
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
        dto.type_weight = TypeWeightToTypeWeightResponseDto(entity.type_weight_id);

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

    @Override
    public CertificationMiniResponseDto CertificationToCertificationMiniResponseDto(Certification certification){
        CertificationMiniResponseDto dto = new CertificationMiniResponseDto();
        dto.create_date = certification.create_date;
        dto.id_certification = certification.id_certification;
        dto.final_number_certification = certification.final_number_certification;
        dto.number_certification = certification.number_certification;
        return dto;
    }

    @Override
    public DataGeneratorEditResponseDto DataGeneratorToDataGeneratorEditResponseDto(DataGenerator entity){
        DataGeneratorEditResponseDto dto = new DataGeneratorEditResponseDto();
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
    public ManagerDataGeneratorRequestDto ManagerToManagerDataGeneratorRequestDto(List<ManagerDataGenerator> managerList){
        ManagerDataGeneratorRequestDto response = new ManagerDataGeneratorRequestDto();
        for (ManagerDataGenerator item: managerList){
            if (item.manager_id.id_manager == ManagerEnum.PuntoLimpio.getNumberId()){
                response.manager_id_1 = true;
            }
            if (item.manager_id.id_manager == ManagerEnum.Aprovechamiento.getNumberId()){
                response.manager_id_2 = true;
            }
            if (item.manager_id.id_manager == ManagerEnum.DisposicionFinal.getNumberId()){
                response.manager_id_3 = true;
            }
        }
        return response;
    }

    @Override
    public QuantitiesRcdRequestDto QuantitiesRcdToQuantitiesRcdRequestDto(List<QuantitiesRcd> enitities){
        QuantitiesRcdRequestDto response = new QuantitiesRcdRequestDto();
        for (QuantitiesRcd item: enitities){
            if(item.type_rcd_id.id_type_rcd == TypeRcdEnum.Uno.getNumberId()){
                response.quantity_rcd_1 = item.quantity_rcd;;
            }
            if(item.type_rcd_id.id_type_rcd == TypeRcdEnum.UnoUno.getNumberId()){
                response.quantity_rcd_2 = item.quantity_rcd;;
            }
            if(item.type_rcd_id.id_type_rcd == TypeRcdEnum.UnoDos.getNumberId()){
                response.quantity_rcd_3 = item.quantity_rcd;;
            }
            if(item.type_rcd_id.id_type_rcd == TypeRcdEnum.UnoTres.getNumberId()){
                response.quantity_rcd_4 = item.quantity_rcd;;
            }
            if(item.type_rcd_id.id_type_rcd == TypeRcdEnum.UnoCuatro.getNumberId()){
                response.quantity_rcd_5 = item.quantity_rcd;;
            }
            if(item.type_rcd_id.id_type_rcd == TypeRcdEnum.Dos.getNumberId()){
                response.quantity_rcd_6 = item.quantity_rcd;;
            }
            if(item.type_rcd_id.id_type_rcd == TypeRcdEnum.DosUno.getNumberId()){
                response.quantity_rcd_7 = item.quantity_rcd;;
            }
            if(item.type_rcd_id.id_type_rcd == TypeRcdEnum.DosDos.getNumberId()){
                response.quantity_rcd_8 = item.quantity_rcd;;
            }
            if(item.type_rcd_id.id_type_rcd == TypeRcdEnum.DosTres.getNumberId()){
                response.quantity_rcd_9 = item.quantity_rcd;;
            }
        }
        return response;
    }

    public BotaderoResponseDto BotaderoToBotaderoResponseDto(Botadero entity){
        return new BotaderoResponseDto(entity);
    }

    @Override
    public TypeWeightResponseDto TypeWeightToTypeWeightResponseDto(TypeWeight entity) {

        TypeWeightResponseDto dto = new TypeWeightResponseDto();
        dto.id_type_weight = entity.id_type_weight;
        dto.name = entity.name;
        dto.status = entity.status;
        dto.description = entity.description;

        return dto;
    }
}
