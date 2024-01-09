package com.agg.certificados;


import com.agg.certificados.entity.TypeRcd;
import com.agg.certificados.repositories.typeDocumentRepository.ITypeDocumentRepository;
import com.agg.certificados.repositories.typeRcdRepository.ITypeRcdRepository;
import com.agg.certificados.services.usersServices.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.agg.certificados.repositories")
@EntityScan(basePackages = "com.agg.certificados.entity")
public class SistemaCertificadosBackendApplication implements CommandLineRunner {

	@Autowired
	private IUserService userService;

	@Autowired
	private ITypeDocumentRepository typeDocumentRepository;
	@Autowired
	private ITypeRcdRepository typeRcdRepository;


	public static void main(String[] args) {
		SpringApplication.run(SistemaCertificadosBackendApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry){
				registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods("*").allowedHeaders("*");
			}
		};
	}
	@Override
	public void run(String... args) throws Exception {

		/*Set<TypeDocument> typeDocuments = new HashSet<>();
		TypeDocument typeDocument = new TypeDocument();

		typeDocument.setName("CC");
		typeDocument.setDescription("Cédula ciudadanía");
		typeDocument.setStatus(true);
		typeDocumentRepository.save(typeDocument);

		User user = new User();

		user.setFull_name("Steven Gonzalez");
		user.setUsername("stevengm45");
		user.setPassword("12345");
		user.setEmail("steven@gmail.com");
		user.setType_document_id(typeDocument);
		user.setNumber_id(98989898L);
		user.setCreate_date(Timestamp.valueOf(LocalDateTime.now()));
		Rol rol = new Rol();
		rol.setRolId(1L);
		rol.setName("ADMIN");

		Set<UserRol> userRoles = new HashSet<>();
		UserRol userRol = new UserRol();
		userRol.setRol(rol);
		userRol.setUser(user);
		userRoles.add(userRol);


		User userSaved = userService.saveUser(user, userRoles);
		System.out.println(userSaved.getUsername());*/

//		List<TypeRcd> typeRcdList = new ArrayList<>();
//		TypeRcd typeRcd1 = new TypeRcd();
//		typeRcd1.setId_type_rcd(1L);
//		typeRcd1.setName("Residuos de construcción y demolición - RCD susceptibles de aprovechamiento");
//		typeRcd1.setStatus(true);
//		typeRcd1.setDescription("Residuos de construcción y demolición - RCD susceptibles de aprovechamiento");
//
//		typeRcdList.add(typeRcd1);
//
//		TypeRcd typeRcd2 = new TypeRcd();
//		typeRcd2.setId_type_rcd(2L);
//		typeRcd2.setName("Productos de excavación y sobrantes de la adecuación del terreno");
//		typeRcd2.setStatus(true);
//		typeRcd2.setDescription("Productos de excavación y sobrantes de la adecuación del terreno: coberturas vegetales, tierras, " +
//				"limos y materiales pétreos productos de la excavación, entre otros.");
//		typeRcdList.add(typeRcd2);

//		TypeRcd typeRcd3 = new TypeRcd();
//		//typeRcd3.setId_type_rcd(3L);
//		typeRcd3.setName("Productos de cimentaciones y pilotajes");
//		typeRcd3.setStatus(true);
//		typeRcd3.setDescription("Productos de cimentaciones y pilotajes: arcillas, bentonitas y demás.");
//		typeRcdList.add(typeRcd3);
//
//		TypeRcd typeRcd4 = new TypeRcd();
//		//typeRcd4.setId_type_rcd(4L);
//		typeRcd4.setName("Pétreos");
//		typeRcd4.setStatus(true);
//		typeRcd4.setDescription("Pétreos: hormigón, arenas, gravas, gravillas, cantos, pétreos asfalticos, trozos de ladrillos y " +
//				"bloques, cerámicas,sobrantes de mezcla de cementos y concretos hidráulicos, entre otros.");
//		typeRcdList.add(typeRcd4);

//		TypeRcd typeRcd5 = new TypeRcd();
//		//typeRcd5.setId_type_rcd(5L);
//		typeRcd5.setName("No Pétreos");
//		typeRcd5.setStatus(true);
//		typeRcd5.setDescription("No pétreos: vidrio, metales como acero, hierro, cobre, aluminio, con o sin recubrimiento de zinc o " +
//				"estaño, plásticos tales como: PVC, polietileno, policarbonato, acrílico, espumas de poliestireno y de poliuretano, gomas y cauchos," +
//				"madera y compuestos de madera, cartón-yeso (drywall), entre otros.");
//		typeRcdList.add(typeRcd5);
//
//		TypeRcd typeRcd6 = new TypeRcd();
//		//typeRcd6.setId_type_rcd(6L);
//		typeRcd6.setName("Residuos de construcción y demolición - RCD no susceptibles de aprovechamiento:");
//		typeRcd6.setStatus(true);
//		typeRcd6.setDescription("Residuos de construcción y demolición - RCD no susceptibles de aprovechamiento:");
//		typeRcdList.add(typeRcd6);
//
//		TypeRcd typeRcd7 = new TypeRcd();
//		//typeRcd7.setId_type_rcd(7L);
//		typeRcd7.setName("Los contaminados con residuos peligrosos.");
//		typeRcd7.setStatus(true);
//		typeRcd7.setDescription("Los contaminados con residuos peligrosos.");
//		typeRcdList.add(typeRcd7);
//
//		TypeRcd typeRcd8 = new TypeRcd();
//		//typeRcd8.setId_type_rcd(8L);
//		typeRcd8.setName("Los que por su estado no pueden ser aprovechados.");
//		typeRcd8.setStatus(true);
//		typeRcd8.setDescription("Los que por su estado no pueden ser aprovechados.");
//		typeRcdList.add(typeRcd8);
//
//		TypeRcd typeRcd9 = new TypeRcd();
//		//typeRcd9.setId_type_rcd(9L);
//		typeRcd9.setName("Los que tengan características de peligrosidad, estos se regirán por la normatividad ambiental especial establecida para su gestión.");
//		typeRcd9.setStatus(true);
//		typeRcd9.setDescription("Los que tengan características de peligrosidad, estos se regirán por la normatividad ambiental especial establecida para su gestión.");
//		typeRcdList.add(typeRcd9);
//
//		typeRcdRepository.saveAll(typeRcdList);


	}
}
