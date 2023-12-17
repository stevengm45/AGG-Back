package com.agg.certificados;


import com.agg.certificados.entity.*;
import com.agg.certificados.repositories.typeDocument.ITypeDocumentRepository;
import com.agg.certificados.services.usersServices.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@SpringBootApplication
public class SistemaCertificadosBackendApplication implements CommandLineRunner {

	@Autowired
	private IUserService userService;

	@Autowired
	private ITypeDocumentRepository typeDocumentRepository;


	public static void main(String[] args) {
		SpringApplication.run(SistemaCertificadosBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Set<TypeDocument> typeDocuments = new HashSet<>();
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
		System.out.println(userSaved.getUsername());

	}
}
