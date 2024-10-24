package com.agg.certificados;


import com.agg.certificados.entity.*;
import com.agg.certificados.exceptions.UserFoundException;
import com.agg.certificados.repositories.RolRepository;
import com.agg.certificados.repositories.dataManager.IDataManagerRepository;
import com.agg.certificados.repositories.manager.IManagerRepository;
import com.agg.certificados.repositories.typeDocumentRepository.ITypeDocumentRepository;
import com.agg.certificados.repositories.typeRcdRepository.ITypeRcdRepository;
import com.agg.certificados.repositories.typeWeightRepository.ITypeWeightRepository;
import com.agg.certificados.services.usersServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Set;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.agg.certificados.repositories")
@EntityScan(basePackages = "com.agg.certificados.entity")
public class SistemaCertificadosBackendApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private RolRepository rolRepository;

	@Autowired
	private ITypeDocumentRepository typeDocumentRepository;

	@Autowired
	private ITypeWeightRepository typeWeightRepository;
	@Autowired
	private ITypeRcdRepository typeRcdRepository;

	@Autowired
	private IDataManagerRepository dataManagerRepository;

	@Autowired
	private IManagerRepository managerRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SistemaCertificadosBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		try{
			Set<TypeDocument> typeDocuments = new HashSet<>();
			TypeDocument typeDocument = new TypeDocument();

			typeDocument.setName("CC");
			typeDocument.setDescription("Cédula ciudadanía");
			typeDocument.setStatus(true);
			typeDocumentRepository.save(typeDocument);

			TypeDocument typeDocumentsNit = new TypeDocument();

			typeDocumentsNit.setName("NIT");
			typeDocumentsNit.setDescription("NIT");
			typeDocumentsNit.setStatus(true);
			typeDocumentRepository.save(typeDocumentsNit);

			Rol rol = new Rol();
			rol.setRolId(1L);
			rol.setRolName("ADMIN");

			rolRepository.save(rol);

			User user1 = new User();

			user1.setFull_name("Administrador");
			user1.setUsername("admin");
			user1.setPassword(bCryptPasswordEncoder.encode("admin*2024"));
			user1.setEmail("");
			user1.setType_document_id(typeDocumentRepository.findById(1L).orElse(null));
			user1.setNumber_id(0L);
			user1.setCreate_date(Timestamp.valueOf(LocalDateTime.now()));

			Set<UserRol> userRoles = new HashSet<>();
			UserRol userRol1 = new UserRol();
			userRol1.setRol(rol);
			userRol1.setUser(user1);
			userRoles.add(userRol1);

			User saveUser = userService.saveUser(user1, userRoles);

		} catch(UserFoundException e) {
			e.printStackTrace();
		}


		Set<TypeWeight> typeWeights = new HashSet<>();
		TypeWeight typeWeight = new TypeWeight();

		typeWeight.setName("TN");
		typeWeight.setDescription("Toneladas");
		typeWeight.setStatus(true);
		typeWeightRepository.save(typeWeight);

		TypeWeight typeWeightKl = new TypeWeight();

		typeWeightKl.setName("KL");
		typeWeightKl.setDescription("Kilos");
		typeWeightKl.setStatus(true);
		typeWeightRepository.save(typeWeightKl);

		List<TypeRcd> typeRcdList = new ArrayList<>();
		TypeRcd typeRcd1 = new TypeRcd();
		typeRcd1.setId_type_rcd(1L);
		typeRcd1.setName("Residuos de construcción y demolición - RCD susceptibles de aprovechamiento");
		typeRcd1.setStatus(true);
		typeRcd1.setDescription("Residuos de construcción y demolición - RCD susceptibles de aprovechamiento");

		typeRcdList.add(typeRcd1);

		TypeRcd typeRcd2 = new TypeRcd();
		typeRcd2.setId_type_rcd(2L);
		typeRcd2.setName("Productos de excavación y sobrantes de la adecuación del terreno");
		typeRcd2.setStatus(true);
		typeRcd2.setDescription("Productos de excavación y sobrantes de la adecuación del terreno: coberturas vegetales, tierras, " +
				"limos y materiales pétreos productos de la excavación, entre otros.");
		typeRcdList.add(typeRcd2);

		TypeRcd typeRcd3 = new TypeRcd();
		//typeRcd3.setId_type_rcd(3L);
		typeRcd3.setName("Productos de cimentaciones y pilotajes");
		typeRcd3.setStatus(true);
		typeRcd3.setDescription("Productos de cimentaciones y pilotajes: arcillas, bentonitas y demás.");
		typeRcdList.add(typeRcd3);

		TypeRcd typeRcd4 = new TypeRcd();
		//typeRcd4.setId_type_rcd(4L);
		typeRcd4.setName("Pétreos");
		typeRcd4.setStatus(true);
		typeRcd4.setDescription("Pétreos: hormigón, arenas, gravas, gravillas, cantos, pétreos asfalticos, trozos de ladrillos y " +
				"bloques, cerámicas,sobrantes de mezcla de cementos y concretos hidráulicos, entre otros.");
		typeRcdList.add(typeRcd4);

		TypeRcd typeRcd5 = new TypeRcd();
		//typeRcd5.setId_type_rcd(5L);
		typeRcd5.setName("No Pétreos");
		typeRcd5.setStatus(true);
		typeRcd5.setDescription("No pétreos: vidrio, metales como acero, hierro, cobre, aluminio, con o sin recubrimiento de zinc o " +
				"estaño, plásticos tales como: PVC, polietileno, policarbonato, acrílico, espumas de poliestireno y de poliuretano, gomas y cauchos," +
				"madera y compuestos de madera, cartón-yeso (drywall), entre otros.");
		typeRcdList.add(typeRcd5);

		TypeRcd typeRcd6 = new TypeRcd();
		//typeRcd6.setId_type_rcd(6L);
		typeRcd6.setName("Residuos de construcción y demolición - RCD no susceptibles de aprovechamiento:");
		typeRcd6.setStatus(true);
		typeRcd6.setDescription("Residuos de construcción y demolición - RCD no susceptibles de aprovechamiento:");
		typeRcdList.add(typeRcd6);

		TypeRcd typeRcd7 = new TypeRcd();
		//typeRcd7.setId_type_rcd(7L);
		typeRcd7.setName("Los contaminados con residuos peligrosos.");
		typeRcd7.setStatus(true);
		typeRcd7.setDescription("Los contaminados con residuos peligrosos.");
		typeRcdList.add(typeRcd7);

		TypeRcd typeRcd8 = new TypeRcd();
		//typeRcd8.setId_type_rcd(8L);
		typeRcd8.setName("Los que por su estado no pueden ser aprovechados.");
		typeRcd8.setStatus(true);
		typeRcd8.setDescription("Los que por su estado no pueden ser aprovechados.");
		typeRcdList.add(typeRcd8);

		TypeRcd typeRcd9 = new TypeRcd();
		//typeRcd9.setId_type_rcd(9L);
		typeRcd9.setName("Los que tengan características de peligrosidad, estos se regirán por la normatividad ambiental especial establecida para su gestión.");
		typeRcd9.setStatus(true);
		typeRcd9.setDescription("Los que tengan características de peligrosidad, estos se regirán por la normatividad ambiental especial establecida para su gestión.");
		typeRcdList.add(typeRcd9);

		typeRcdRepository.saveAll(typeRcdList);

		Long id = 1L;
		DataManager dataManager = new DataManager();
		dataManager.setName("SUMINISTRAMOS Y CONTRATAMOS AGG SAS");
		dataManager.setEmail("suministramosycontratamos@gmail.com");
		dataManager.setAddress("CALLE 70 # 12B – 77 SIETE DE AGOSTO (Oficina) ");
		dataManager.setNumber_id("901191011-8");
		dataManager.setPhone_number("3148095541 - (602) 3848023");
		dataManager.setLegal_representative("ALEJANDRO GARZÓN GUZMÁN");
		dataManager.setUnic_number("");
		dataManager.setType_document_id(typeDocumentRepository.findById(2L).orElse(null));

		dataManagerRepository.save(dataManager);

		DataManager dataManager2 = new DataManager();
		dataManager2.setName("LINA PAOLA ROJAS GUZMAN");
		dataManager2.setEmail("linarojitas@hotmail.com");
		dataManager2.setAddress("CALLE 88 #20a - 56");
		dataManager2.setNumber_id("1.110.465.056");
		dataManager2.setPhone_number("3167448482");
		dataManager2.setLegal_representative("LINA PAOLA ROJAS GUZMAN");
		dataManager2.setUnic_number("");
		dataManager2.setType_document_id(typeDocumentRepository.findById(1l).orElse(null));

		dataManagerRepository.save(dataManager2);

		DataManager dataManager3 = new DataManager();
		dataManager3.setName("FABIOLA ASPRILLA CACERES");
		dataManager3.setEmail("fabiola23@gmail.com");
		dataManager3.setAddress("CALLE 90 # 16-57");
		dataManager3.setNumber_id("31.880.147");
		dataManager3.setPhone_number("3167978987");
		dataManager3.setLegal_representative("FABIOLA ASPRILLA CACERES");
		dataManager3.setUnic_number("");
		dataManager3.setType_document_id(typeDocumentRepository.findById(1l).orElse(null));

		dataManagerRepository.save(dataManager3);

		DataManager dataManager4 = new DataManager();
		dataManager4.setName("ALEJANDRO GARZON GUZMAN");
		dataManager4.setEmail("suministramosycontratamos@gmail.com");
		dataManager4.setAddress("CALLE 70 # 12B – 77 SIETE DE AGOSTO (Oficina) ");
		dataManager4.setNumber_id("79.308.043");
		dataManager4.setPhone_number("3148095541 - (602) 3848023");
		dataManager4.setLegal_representative("ALEJANDRO GARZÓN GUZMÁN");
		dataManager4.setUnic_number("");
		dataManager4.setType_document_id(typeDocumentRepository.findById(1L).orElse(null));

		dataManagerRepository.save(dataManager4);


		Manager manager1 = new Manager();
		manager1.setName("Punto limpio");
		manager1.setStatus(true);

		Manager manager2 = new Manager();
		manager2.setName("Planta de aprovechamiento");
		manager2.setStatus(true);

		Manager manager3 = new Manager();
		manager3.setName("Disposición final");
		manager3.setStatus(true);

		managerRepository.save(manager1);
		managerRepository.save(manager2);
		managerRepository.save(manager3);

	}
}
