package com.agg.certificados.services.dataGeneratorServices;

import com.agg.certificados.dtos.request.DataGeneratorRequestDto;
import com.agg.certificados.dtos.request.ManagerDataGeneratorRequestDto;
import com.agg.certificados.dtos.request.QuantitiesRcdRequestDto;
import com.agg.certificados.dtos.response.DataGeneratorEditResponseDto;
import com.agg.certificados.dtos.response.DataGeneratorResponseDto;
import com.agg.certificados.dtos.response.FileBase64ResponseDto;
import com.agg.certificados.entity.*;
import com.agg.certificados.mapper.IMapStructMapper;
import com.agg.certificados.repositories.botaderoRepository.IBotaderoRepository;
import com.agg.certificados.repositories.certificationRepository.ICertificationRepository;
import com.agg.certificados.repositories.dataDriverRepository.IDataDriverRepository;
import com.agg.certificados.repositories.dataGeneratorRepository.IDataGeneratorRepository;
import com.agg.certificados.repositories.dataManager.IDataManagerRepository;
import com.agg.certificados.repositories.manager.IManagerRepository;
import com.agg.certificados.repositories.managerDataGeneratorRepository.IManagerDataGeneratorRepository;
import com.agg.certificados.repositories.quantitiesRcdRepository.IQuantitiesRcdRepository;
import com.agg.certificados.repositories.typeDocumentRepository.ITypeDocumentRepository;
import com.agg.certificados.repositories.typeRcdRepository.ITypeRcdRepository;
import com.agg.certificados.repositories.typeWeightRepository.ITypeWeightRepository;
import com.agg.certificados.services.certificationServices.service.ICertificationService;
import com.agg.certificados.utils.ManagerEnum;
import com.agg.certificados.utils.TypeRcdEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
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
    private ITypeWeightRepository typeWeightRepository;
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
    private ICertificationRepository certificationRepository;

    @Autowired
    private IMapStructMapper mapStructMapper;

    @Autowired
    private ICertificationService certificationService;
    @Transactional
    public Long save(DataGeneratorRequestDto dto) {

        DataGenerator dataGenerator = new DataGenerator();

        dataGenerator.setBotadero_id(botaderoRepository.findById(dto.botadero_id).orElse(null));
        dataGenerator.setData_manager_id(dataManagerRepository.findById(dto.data_manager_id).orElse(null));

        dataGenerator.setUnic_number(dto.unic_number);
        dataGenerator.setName(dto.name);
        dataGenerator.setType_document_id(typeDocumentRepository.findById(dto.type_document_id).orElse(null));
        dataGenerator.setNumber_id(dto.number_id);

        dataGenerator.setType_weight_id(typeWeightRepository.findById(dto.type_weight_id).orElse(null));

        dataGenerator.setLegal_representative(dto.legal_representative);
        dataGenerator.setAddress(dto.address);
        dataGenerator.setPhone_number(dto.phone_number);
        dataGenerator.setEmail(dto.email);
        dataGenerator.setAddress_rcd(dto.address_rcd);

        DecimalFormat formato = new DecimalFormat("#.##");


        dataGenerator.setTotal_rcd(Double.parseDouble(formato.format(dto.quantitiesRcd.total))); //Revisar este total, para quitarlo del dto y ponerlo calculado directo aqui mismo

        dataGenerator.setReception_date_rcd(dto.reception_date_rcd);

        //Data driver
        DataDriver dataDriver = new DataDriver();

        dataDriver.setName(dto.data_driver.name);
        dataDriver.setType_document_id(typeDocumentRepository.findById(dto.data_driver.type_document_id).orElse(null));
        dataDriver.setNumber_id(dto.data_driver.number_id);
        dataDriver.setVehicle_plate(dto.data_driver.vehicle_plate);
        dataDriver.setData_generator_id(dataGenerator);

        //Quantities


        List<QuantitiesRcd> quantities = mappingQuantitiesRcd(dto.quantitiesRcd.quantities,dataGenerator);


        //Manager data generator, de muchos a muchos
        List<ManagerDataGenerator> managerDataGenerators = new ArrayList<>();

        //Manager
        if (dto.manager.manager_id_1){
            Manager manager_1 = managerRepository.findById(ManagerEnum.PuntoLimpio.getNumberId()).orElse(null);
            managerDataGenerators.add(new ManagerDataGenerator(0L,manager_1,dataGenerator));
        }
        if (dto.manager.manager_id_2) {
            Manager manager_2 = managerRepository.findById(ManagerEnum.Aprovechamiento.getNumberId()).orElse(null);
            managerDataGenerators.add(new ManagerDataGenerator(0L,manager_2,dataGenerator));
        }
        if (dto.manager.manager_id_3) {
            Manager manager_3 = managerRepository.findById(ManagerEnum.DisposicionFinal.getNumberId()).orElse(null);
            managerDataGenerators.add(new ManagerDataGenerator(0L,manager_3,dataGenerator));
        }

        dataGeneratorRepository.save(dataGenerator);

        quantitiesRcdRepository.saveAll(quantities);

        dataDriverRepository.save(dataDriver);

        managerDataGeneratorRepository.saveAll(managerDataGenerators);

        return dataGenerator.id_data_generator;
    }

    private List<QuantitiesRcd> mappingQuantitiesRcd(QuantitiesRcdRequestDto quantities, DataGenerator dataGenerator){
        List<QuantitiesRcd> quantitiesList = new ArrayList<>();

        if (quantities.quantity_rcd_1 != null){
            if (quantities.quantity_rcd_1 >= 0){

                    if(dataGenerator.id_data_generator == null){
                        quantitiesList.add(new QuantitiesRcd(0L,typeRcdRepository.findById(TypeRcdEnum.Uno.getNumberId()).orElse(null),dataGenerator,quantities.quantity_rcd_1));
                    }else{
                        QuantitiesRcd quantity = quantitiesRcdRepository.findByIdDataGeneratorAndIdTypeRcd(dataGenerator.id_data_generator,TypeRcdEnum.Uno.getNumberId());

                        if(quantity==null){
                            quantitiesList.add(new QuantitiesRcd(0L,typeRcdRepository.findById(TypeRcdEnum.Uno.getNumberId()).orElse(null),dataGenerator,quantities.quantity_rcd_1));
                        }else{
                            quantitiesList.add(new QuantitiesRcd(quantity.id_quantities_rcd,typeRcdRepository.findById(TypeRcdEnum.Uno.getNumberId()).orElse(null),dataGenerator,quantities.quantity_rcd_1));
                        }
                    }


            }
        }
        if (quantities.quantity_rcd_2 != null){
            if (quantities.quantity_rcd_2 >= 0) {

                if (dataGenerator != null){
                    if(dataGenerator.id_data_generator == null){
                        quantitiesList.add(new QuantitiesRcd(0L,typeRcdRepository.findById(TypeRcdEnum.UnoUno.getNumberId()).orElse(null),dataGenerator,quantities.quantity_rcd_2));
                    }else{
                        QuantitiesRcd quantity = quantitiesRcdRepository.findByIdDataGeneratorAndIdTypeRcd(dataGenerator.id_data_generator,TypeRcdEnum.UnoUno.getNumberId());

                        if (quantity ==null){
                            quantitiesList.add(new QuantitiesRcd(0L,typeRcdRepository.findById(TypeRcdEnum.UnoUno.getNumberId()).orElse(null),dataGenerator,quantities.quantity_rcd_2));
                        }else{
                            quantitiesList.add(new QuantitiesRcd(quantity.id_quantities_rcd,typeRcdRepository.findById(TypeRcdEnum.UnoUno.getNumberId()).orElse(null),dataGenerator,quantities.quantity_rcd_2));
                        }
                    }
                }

            }
        }

        if (quantities.quantity_rcd_3 != null){
            if (quantities.quantity_rcd_3 >= 0) {
                if (dataGenerator != null){

                    if(dataGenerator.id_data_generator == null) {
                        quantitiesList.add(new QuantitiesRcd(0L,typeRcdRepository.findById(TypeRcdEnum.UnoDos.getNumberId()).orElse(null),dataGenerator,quantities.quantity_rcd_3));
                    }else{
                        QuantitiesRcd quantity = quantitiesRcdRepository.findByIdDataGeneratorAndIdTypeRcd(dataGenerator.id_data_generator,TypeRcdEnum.UnoDos.getNumberId());
                        if(quantity == null){
                            quantitiesList.add(new QuantitiesRcd(0l,typeRcdRepository.findById(TypeRcdEnum.UnoDos.getNumberId()).orElse(null),dataGenerator,quantities.quantity_rcd_3));
                        }else{
                            quantitiesList.add(new QuantitiesRcd(quantity.id_quantities_rcd,typeRcdRepository.findById(TypeRcdEnum.UnoDos.getNumberId()).orElse(null),dataGenerator,quantities.quantity_rcd_3));
                        }
                    }
                }

            }
        }


        if (quantities.quantity_rcd_4 != null){
            if (quantities.quantity_rcd_4 >= 0 ) {
                if (dataGenerator != null){
                    if(dataGenerator.id_data_generator == null ) {
                        quantitiesList.add(new QuantitiesRcd(0L,typeRcdRepository.findById(TypeRcdEnum.UnoTres.getNumberId()).orElse(null),dataGenerator,quantities.quantity_rcd_4));
                    }else{
                        QuantitiesRcd quantity = quantitiesRcdRepository.findByIdDataGeneratorAndIdTypeRcd(dataGenerator.id_data_generator,TypeRcdEnum.UnoTres.getNumberId());
                        if (quantity == null){
                            quantitiesList.add(new QuantitiesRcd(0L,typeRcdRepository.findById(TypeRcdEnum.UnoTres.getNumberId()).orElse(null),dataGenerator,quantities.quantity_rcd_4));
                        }else{
                            quantitiesList.add(new QuantitiesRcd(quantity.id_quantities_rcd,typeRcdRepository.findById(TypeRcdEnum.UnoTres.getNumberId()).orElse(null),dataGenerator,quantities.quantity_rcd_4));
                        }
                    }
                }


            }
        }


        if (quantities.quantity_rcd_5 != null){
            if (quantities.quantity_rcd_5 >= 0) {
                if (dataGenerator != null){
                    if(dataGenerator.id_data_generator == null)  {
                        quantitiesList.add(new QuantitiesRcd(0L,typeRcdRepository.findById(TypeRcdEnum.UnoCuatro.getNumberId()).orElse(null),dataGenerator,quantities.quantity_rcd_5));
                    }else{
                        QuantitiesRcd quantity = quantitiesRcdRepository.findByIdDataGeneratorAndIdTypeRcd(dataGenerator.id_data_generator,TypeRcdEnum.UnoCuatro.getNumberId());
                        if(quantity == null){
                            quantitiesList.add(new QuantitiesRcd(0L,typeRcdRepository.findById(TypeRcdEnum.UnoCuatro.getNumberId()).orElse(null),dataGenerator,quantities.quantity_rcd_5));

                        }else{
                            quantitiesList.add(new QuantitiesRcd(quantity.id_quantities_rcd,typeRcdRepository.findById(TypeRcdEnum.UnoCuatro.getNumberId()).orElse(null),dataGenerator,quantities.quantity_rcd_5));
                        }
                    }
                }

            }
        }


        if (quantities.quantity_rcd_6 != null){
            if (quantities.quantity_rcd_6 >= 0) {
                if (dataGenerator != null){
                    if(dataGenerator.id_data_generator == null) {
                        quantitiesList.add(new QuantitiesRcd(0L,typeRcdRepository.findById(TypeRcdEnum.Dos.getNumberId()).orElse(null),dataGenerator,quantities.quantity_rcd_6));

                    }else{
                        QuantitiesRcd quantity = quantitiesRcdRepository.findByIdDataGeneratorAndIdTypeRcd(dataGenerator.id_data_generator,TypeRcdEnum.Dos.getNumberId());
                        if(quantity == null){
                            quantitiesList.add(new QuantitiesRcd(0L,typeRcdRepository.findById(TypeRcdEnum.Dos.getNumberId()).orElse(null),dataGenerator,quantities.quantity_rcd_6));
                        }else{
                            quantitiesList.add(new QuantitiesRcd(quantity.id_quantities_rcd,typeRcdRepository.findById(TypeRcdEnum.Dos.getNumberId()).orElse(null),dataGenerator,quantities.quantity_rcd_6));
                        }
                    }
                }
            }
        }


        if (quantities.quantity_rcd_7 != null){
            if (quantities.quantity_rcd_7 >= 0) {
                if (dataGenerator != null){
                    if(dataGenerator.id_data_generator == null) {
                        quantitiesList.add(new QuantitiesRcd(0L,typeRcdRepository.findById(TypeRcdEnum.DosUno.getNumberId()).orElse(null),dataGenerator,quantities.quantity_rcd_7));
                    }else{
                        QuantitiesRcd quantity = quantitiesRcdRepository.findByIdDataGeneratorAndIdTypeRcd(dataGenerator.id_data_generator,TypeRcdEnum.DosUno.getNumberId());
                        if (quantity == null) {
                            quantitiesList.add(new QuantitiesRcd(0L,typeRcdRepository.findById(TypeRcdEnum.DosUno.getNumberId()).orElse(null),dataGenerator,quantities.quantity_rcd_7));
                        }else{
                            quantitiesList.add(new QuantitiesRcd(quantity.id_quantities_rcd,typeRcdRepository.findById(TypeRcdEnum.DosUno.getNumberId()).orElse(null),dataGenerator,quantities.quantity_rcd_7));
                        }
                    }
                }

            }
        }


        if (quantities.quantity_rcd_8 != null){
            if (quantities.quantity_rcd_8 >= 0) {
                if (dataGenerator != null){
                    if(dataGenerator.id_data_generator == null) {
                        quantitiesList.add(new QuantitiesRcd(0L,typeRcdRepository.findById(TypeRcdEnum.DosDos.getNumberId()).orElse(null),dataGenerator,quantities.quantity_rcd_8));
                    }else{
                        QuantitiesRcd quantity = quantitiesRcdRepository.findByIdDataGeneratorAndIdTypeRcd(dataGenerator.id_data_generator,TypeRcdEnum.DosDos.getNumberId());

                        if (quantity == null) {
                            quantitiesList.add(new QuantitiesRcd(0L,typeRcdRepository.findById(TypeRcdEnum.DosDos.getNumberId()).orElse(null),dataGenerator,quantities.quantity_rcd_8));
                        }else{
                            quantitiesList.add(new QuantitiesRcd(quantity.id_quantities_rcd,typeRcdRepository.findById(TypeRcdEnum.DosDos.getNumberId()).orElse(null),dataGenerator,quantities.quantity_rcd_8));
                        }

                    }
                }

            }
        }


        if (quantities.quantity_rcd_9 != null){
            if (quantities.quantity_rcd_9 >= 0 ) {
                if (dataGenerator !=  null){
                    if(dataGenerator.id_data_generator == null) {
                        quantitiesList.add(new QuantitiesRcd(0L,typeRcdRepository.findById(TypeRcdEnum.DosTres.getNumberId()).orElse(null),dataGenerator,quantities.quantity_rcd_9));
                    }else{
                        QuantitiesRcd quantity = quantitiesRcdRepository.findByIdDataGeneratorAndIdTypeRcd(dataGenerator.id_data_generator,TypeRcdEnum.DosTres.getNumberId());
                        if(quantity==null){
                            quantitiesList.add(new QuantitiesRcd(0L,typeRcdRepository.findById(TypeRcdEnum.DosTres.getNumberId()).orElse(null),dataGenerator,quantities.quantity_rcd_9));
                        }else{
                            quantitiesList.add(new QuantitiesRcd(quantity.id_quantities_rcd,typeRcdRepository.findById(TypeRcdEnum.DosTres.getNumberId()).orElse(null),dataGenerator,quantities.quantity_rcd_9));
                        }
                    }
                }

            }
        }


        return quantitiesList;
    }

    @Transactional
    public DataGeneratorResponseDto getInformationGetCertificate(Long idDataGenerator){
        DataGeneratorResponseDto dto = getInformationCertificate(idDataGenerator);
        dto.certification = mapStructMapper.CertificationToCertificationMiniResponseDto(certificationRepository.findByIdDataGenerator(idDataGenerator));

        return dto;
    }

    //@Transactional
    public DataGeneratorResponseDto getInformationCertificate(Long idDataGenerator){

        DataGenerator entityDataGenerator = dataGeneratorRepository.findById(idDataGenerator).orElse(null);

        DataDriver entityDataDriver = dataDriverRepository.findByIdDataGenerator(entityDataGenerator.id_data_generator);

        List<QuantitiesRcd> entitiesQuantitiesRcd = quantitiesRcdRepository.findByIdDataGenerator(entityDataGenerator.id_data_generator);

        List<ManagerDataGenerator> entitiesManagerDataGenerator = managerDataGeneratorRepository.findByIdDataGenerator(entityDataGenerator.id_data_generator);

//        PriceRcd entityPriceRcd = priceRcdRepository.findByIdDataGenerator(entityDataGenerator.id_data_generator);

        DataGeneratorResponseDto dtoDataGenerator = mapStructMapper.DataGeneratorToDataGeneratorResponseDto(entityDataGenerator);


        //Price rcd
//        dtoDataGenerator.price_rcd = mapStructMapper.PriceRcdToPriceRcdResponseDto(entityPriceRcd);

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

    @Transactional
    public boolean editDataGenerator(Long idCertification, DataGeneratorRequestDto dto){

        //Obtiene la certificacion
        Certification certification = certificationRepository.findById(idCertification).orElse(null);

        if (certification == null){
            throw new RuntimeException("No existe la certificacion con el id: " + idCertification);
        }

        DataGenerator dataGenerator = new DataGenerator();

        //Mapea el id del generador en el objeto
        dataGenerator.id_data_generator = certification.data_generator_id.getId_data_generator();

        dataGenerator.setBotadero_id(botaderoRepository.findById(dto.botadero_id).orElse(null));
        dataGenerator.setData_manager_id(dataManagerRepository.findById(dto.data_manager_id).orElse(null));

        dataGenerator.setUnic_number(dto.unic_number);
        dataGenerator.setName(dto.name);
        dataGenerator.setType_document_id(typeDocumentRepository.findById(dto.type_document_id).orElse(null));
        dataGenerator.setNumber_id(dto.number_id);
        dataGenerator.setType_weight_id(typeWeightRepository.findById(dto.type_weight_id).orElse(null));
        dataGenerator.setLegal_representative(dto.legal_representative);
        dataGenerator.setAddress(dto.address);
        dataGenerator.setPhone_number(dto.phone_number);
        dataGenerator.setEmail(dto.email);
        dataGenerator.setAddress_rcd(dto.address_rcd);
        DecimalFormat formato = new DecimalFormat("#.##");

        dataGenerator.setTotal_rcd(Double.parseDouble(formato.format(dto.quantitiesRcd.total))); //Revisar este total, para quitarlo del dto y ponerlo calculado directo aqui mismo

        dataGenerator.setReception_date_rcd(dto.reception_date_rcd);

        //Data driver
        DataDriver dataDriver = new DataDriver();

        //Se mapea el id del data driver
        dataDriver.setId_data_driver((dataDriverRepository.findByIdDataGenerator(dataGenerator.id_data_generator)).id_data_driver);

        dataDriver.setName(dto.data_driver.name);
        dataDriver.setType_document_id(typeDocumentRepository.findById(dto.data_driver.type_document_id).orElse(null));
        dataDriver.setNumber_id(dto.data_driver.number_id);
        dataDriver.setVehicle_plate(dto.data_driver.vehicle_plate);
        dataDriver.setData_generator_id(dataGenerator);

        //Quantities
        //Eliminar todos los quantities rcd

        //Insertar los nuevos quantities rcd
        List<QuantitiesRcd> quantities = mappingQuantitiesRcd(dto.quantitiesRcd.quantities,dataGenerator);


        //Manager data generator, de muchos a muchos
        List<ManagerDataGenerator> managerDataGenerators = updateManager(dto.manager,
                managerDataGeneratorRepository.findByIdDataGenerator(dataGenerator.id_data_generator),
                dataGenerator);


        dataGeneratorRepository.save(dataGenerator);

        quantitiesRcdRepository.saveAll(quantities);

        dataDriverRepository.save(dataDriver);

        managerDataGeneratorRepository.saveAll(managerDataGenerators);

        //Actualizacion de la certificacion
        DataGeneratorResponseDto dataGeneratorResponseDto = updateFileCertification(dataGenerator.id_data_generator);

        // Se le pone false, por que se va a editar una certificacion, que ya esta existente
        FileBase64ResponseDto files =  certificationService.generateCertificates(dataGeneratorResponseDto, false, certification.number_certification);

        certification.fileCertificateBotadero = files.fileCertificateBotadero;
        certification.fileCertificateBascula = files.fileCertificateBascula;

        certificationRepository.save(certification);
        //-------------------------------------------------

        return true;
    }

    private DataGeneratorResponseDto updateFileCertification(Long idDataGenerator ){
        DataGenerator dataGenerator = dataGeneratorRepository.findById(idDataGenerator).orElse(null);
        DataGeneratorResponseDto dto = mapStructMapper.DataGeneratorToDataGeneratorResponseDto(dataGenerator);

        Botadero botadero = botaderoRepository.findById(dataGenerator.botadero_id.id_botadero).orElse(null);

        dto.botadero = mapStructMapper.BotaderoToBotaderoResponseDto(botadero);

        List<ManagerDataGenerator> managers = managerDataGeneratorRepository.findByIdDataGenerator(dataGenerator.id_data_generator);

        dto.manager = new ArrayList<>();

        for (ManagerDataGenerator item: managers){

            dto.manager.add(mapStructMapper.ManagerToManagerResponseDto(item.manager_id));
        }

        dto.data_manager = mapStructMapper.DataManagerToDataManagerResponseDto(dataGenerator.data_manager_id);
        dto.type_document = mapStructMapper.TypeDocumentToTypeDocumentResponseDto(dataGenerator.type_document_id);

        dto.type_weight = mapStructMapper.TypeWeightToTypeWeightResponseDto(dataGenerator.type_weight_id);


        List<QuantitiesRcd> entitiesQuantitiesRcd = quantitiesRcdRepository.findByIdDataGenerator(dataGenerator.id_data_generator);
        dto.quantitiesRcd = new ArrayList<>();

        for (QuantitiesRcd item: entitiesQuantitiesRcd)
        {
            dto.quantitiesRcd.add(mapStructMapper.QuantitiesRcdToQuantitiesRcdResponseDto(item));
        }

        dto.data_driver = mapStructMapper.DataDriverToDataDriverResponseDto(dataDriverRepository.findByIdDataGenerator(dataGenerator.id_data_generator));
        return dto;
    }

    private List<ManagerDataGenerator> updateManager(ManagerDataGeneratorRequestDto dto, List<ManagerDataGenerator> list, DataGenerator dataGenerator){
        List<ManagerDataGenerator> response = new ArrayList<>();
        for (ManagerDataGenerator item: list){
            managerDataGeneratorRepository.deleteById(item.id_manager_data_generator);
        }
        //Manager
        if (dto.manager_id_1){
            Manager manager_1 = managerRepository.findById(ManagerEnum.PuntoLimpio.getNumberId()).orElse(null);
            response.add(new ManagerDataGenerator(0L,manager_1,dataGenerator));
        }
        if (dto.manager_id_2) {
            Manager manager_2 = managerRepository.findById(ManagerEnum.Aprovechamiento.getNumberId()).orElse(null);
            response.add(new ManagerDataGenerator(0L,manager_2,dataGenerator));
        }
        if (dto.manager_id_3) {
            Manager manager_3 = managerRepository.findById(ManagerEnum.DisposicionFinal.getNumberId()).orElse(null);
            response.add(new ManagerDataGenerator(0L,manager_3,dataGenerator));
        }
        return response;
    }

    @Transactional
    public DataGeneratorEditResponseDto getByIdEdit(Long idCertification){
        Certification certification = certificationRepository.findById(idCertification).orElse(null);

        DataGenerator dataGenerator = dataGeneratorRepository.findById(certification.data_generator_id.id_data_generator).orElse(null);

        if (certification == null){
            throw new RuntimeException("No existe la certificacion con el id: "+idCertification);
        }
        if (dataGenerator == null){
            throw new RuntimeException("No existe el generador con el id: "+dataGenerator.id_data_generator);
        }

        DataGeneratorEditResponseDto responseDto = mapStructMapper.DataGeneratorToDataGeneratorEditResponseDto(dataGenerator);
        responseDto.data_manager = mapStructMapper.DataManagerToDataManagerResponseDto(dataGenerator.data_manager_id);
        responseDto.data_driver = mapStructMapper.DataDriverToDataDriverResponseDto(dataDriverRepository.findByIdDataGenerator(dataGenerator.id_data_generator));
        responseDto.certification = mapStructMapper.CertificationToCertificationMiniResponseDto(certification);
        responseDto.manager = mapStructMapper.ManagerToManagerDataGeneratorRequestDto(managerDataGeneratorRepository.findByIdDataGenerator(dataGenerator.id_data_generator));
        responseDto.quantitiesRcd = mapStructMapper.QuantitiesRcdToQuantitiesRcdRequestDto(quantitiesRcdRepository.findByIdDataGenerator(dataGenerator.id_data_generator));
        responseDto.type_weight = mapStructMapper.TypeWeightToTypeWeightResponseDto(typeWeightRepository.findById(dataGenerator.type_weight_id.id_type_weight).orElse(null));

        return responseDto;
    }

}
