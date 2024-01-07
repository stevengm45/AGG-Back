package com.agg.certificados.services.dataGeneratorServices;

import com.agg.certificados.dtos.request.DataGeneratorRequestDto;
import com.agg.certificados.dtos.request.ManagerDataGeneratorRequestDto;
import com.agg.certificados.dtos.request.QuantitiesRcdRequestDto;
import com.agg.certificados.dtos.response.DataGeneratorResponseDto;
import com.agg.certificados.dtos.response.QuantitiesRcdResponseDto;
import com.agg.certificados.entity.*;
import com.agg.certificados.mapper.IMapStructMapper;
import com.agg.certificados.repositories.botaderoRepository.IBotaderoRepository;
import com.agg.certificados.repositories.dataDriverRepository.IDataDriverRepository;
import com.agg.certificados.repositories.dataGeneratorRepository.IDataGeneratorRepository;
import com.agg.certificados.repositories.dataManager.IDataManagerRepository;
import com.agg.certificados.repositories.manager.IManagerRepository;
import com.agg.certificados.repositories.managerDataGeneratorRepository.IManagerDataGeneratorRepository;
import com.agg.certificados.repositories.priceRcdRepository.IPriceRcdRepository;
import com.agg.certificados.repositories.quantitiesRcdRepository.IQuantitiesRcdRepository;
import com.agg.certificados.repositories.typeDocumentRepository.ITypeDocumentRepository;
import com.agg.certificados.repositories.typeRcdRepository.ITypeRcdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataGeneratorService implements IDataGeneratorService{
    @Autowired
    private IDataGeneratorRepository dataGeneratorRepository;
    @Autowired
    private IDataDriverRepository dataDriverRepository;
    @Autowired
    private IBotaderoRepository botaderoRepository;
    @Autowired
    private ITypeDocumentRepository typeDocumentRepository;
    @Autowired
    private IManagerRepository managerRepository;
    @Autowired
    private IDataManagerRepository dataManagerRepository;
    @Autowired
    private IQuantitiesRcdRepository quantitiesRcdRepository;
    @Autowired
    private ITypeRcdRepository typeRcdRepository;
    @Autowired
    private IManagerDataGeneratorRepository managerDataGeneratorRepository;
    @Autowired
    private IPriceRcdRepository priceRcdRepository;

    @Autowired
    private IMapStructMapper mapStructMapper;
    @Transactional
    public Long save(DataGeneratorRequestDto dto) {

        DataGenerator dataGenerator = new DataGenerator();

        dataGenerator.setBotadero_id(botaderoRepository.findById(dto.botadero_id).orElse(null));
        dataGenerator.setData_manager_id(dataManagerRepository.findById(dto.data_manager_id).orElse(null));

        dataGenerator.setUnic_number(dto.unic_number);
        dataGenerator.setName(dto.name);
        dataGenerator.setType_document_id(typeDocumentRepository.findById(dto.type_document_id).orElse(null));
        dataGenerator.setNumber_id(dto.number_id);

        dataGenerator.setLegal_representative(dto.legal_representative);
        dataGenerator.setAddress(dto.address);
        dataGenerator.setPhone_number(dto.phone_number);
        dataGenerator.setEmail(dto.email);
        dataGenerator.setAddress_rcd(dto.address_rcd);
        dataGenerator.setTotal_rcd(dto.quantitiesRcd.stream().mapToLong(value -> value.quantity_rcd).sum()); //Revisar este total, para quitarlo del dto y ponerlo calculado directo aqui mismo

        dataGenerator.setReception_date_rcd(dto.reception_date_rcd);

        //Data driver
        DataDriver dataDriver = new DataDriver();

        dataDriver.setName(dto.data_driver.name);
        dataDriver.setType_document_id(typeDocumentRepository.findById(dto.data_driver.type_document_id).orElse(null));
        dataDriver.setNumber_id(dto.data_driver.number_id);
        dataDriver.setVehicle_plate(dto.data_driver.vehicle_plate);
        dataDriver.setData_generator_id(dataGenerator);

        //Quantities
        List<QuantitiesRcd> quantities = new ArrayList<>();

        for(QuantitiesRcdRequestDto item : dto.quantitiesRcd){

            quantities.add(mapStructMapper.QuantitiesRcdRequestDtoToQuantitiesRcd(item, dataGenerator));

        }

        //Manager data generator, de muchos a muchos
        List<ManagerDataGenerator> managerDataGenerators = new ArrayList<>();

        for(ManagerDataGeneratorRequestDto item: dto.manager){

            managerDataGenerators.add(
                    new ManagerDataGenerator(0L,managerRepository.findById(item.manager_id).orElse(null),dataGenerator)
            );

        }

        //Price Rcd

        PriceRcd priceRcd = new PriceRcd();

        priceRcd.price_m3 = dto.price_rcd.price_m3;
        priceRcd.data_generator_id = dataGenerator;
        priceRcd.total_price = dto.price_rcd.total_price; //Crear el metodo para hacer el calculo del total, hacer la transformación de toneladas a metro cubico

        dataGeneratorRepository.save(dataGenerator);

        quantitiesRcdRepository.saveAll(quantities);

        dataDriverRepository.save(dataDriver);

        managerDataGeneratorRepository.saveAll(managerDataGenerators);

        priceRcdRepository.save(priceRcd);

        return dataGenerator.id_data_generator;
    }

    @Transactional
    public DataGeneratorResponseDto getInformationCertificate(Long idDataGenerator){

        DataGenerator entityDataGenerator = dataGeneratorRepository.findById(idDataGenerator).orElse(null);

        DataDriver entityDataDriver = dataDriverRepository.findByIdDataGenerator(entityDataGenerator.id_data_generator);

        List<QuantitiesRcd> entitiesQuantitiesRcd = quantitiesRcdRepository.findByIdDataGenerator(entityDataGenerator.id_data_generator);

        List<ManagerDataGenerator> entitiesManagerDataGenerator = managerDataGeneratorRepository.findByIdDataGenerator(entityDataGenerator.id_data_generator);

        PriceRcd entityPriceRcd = priceRcdRepository.findByIdDataGenerator(entityDataGenerator.id_data_generator);

        DataGeneratorResponseDto dtoDataGenerator = mapStructMapper.DataGeneratorToDataGeneratorResponseDto(entityDataGenerator);

        //Price rcd
        dtoDataGenerator.price_rcd = mapStructMapper.PriceRcdToPriceRcdResponseDto(entityPriceRcd);

        dtoDataGenerator.data_driver = mapStructMapper.DataDriverToDataDriverResponseDto(entityDataDriver);
        dtoDataGenerator.quantitiesRcd = new ArrayList<>();
        for (QuantitiesRcd item: entitiesQuantitiesRcd)
        {
            dtoDataGenerator.quantitiesRcd.add(mapStructMapper.QuantitiesRcdToQuantitiesRcdResponseDto(item));
        }

        dtoDataGenerator.manager = new ArrayList<>();

        for(ManagerDataGenerator item: entitiesManagerDataGenerator){

            dtoDataGenerator.manager.add(mapStructMapper
                    .ManagerToManagerResponseDto(
                            managerRepository.findById(item.manager_id.getId_manager()).orElse(null)
                    )
            );
        }

        return dtoDataGenerator;

    }

}
