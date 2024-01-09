package com.agg.certificados;


import com.agg.certificados.entity.*;
import com.agg.certificados.exceptions.UserFoundException;
import com.agg.certificados.repositories.typeDocumentRepository.ITypeDocumentRepository;
import com.agg.certificados.services.usersServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@SpringBootApplication
public class SistemaCertificadosBackendApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private ITypeDocumentRepository typeDocumentRepository;

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

			User user = new User();

			user.setFull_name("steven");
			user.setUsername("steven");
			user.setPassword(bCryptPasswordEncoder.encode("12345"));
			user.setEmail("stevenl@gmail.com");
			user.setType_document_id(typeDocument);
			user.setNumber_id(77789898L);
			user.setCreate_date(Timestamp.valueOf(LocalDateTime.now()));
			Rol rol = new Rol();
			rol.setRolId(1L);
			rol.setRolName("ADMIN");

			Set<UserRol> userRoles = new HashSet<>();
			UserRol userRol = new UserRol();
			userRol.setRol(rol);
			userRol.setUser(user);
			userRoles.add(userRol);


			User userSaved = userService.saveUser(user, userRoles);
			System.out.println(userSaved.getUsername());
		} catch(UserFoundException e) {
			e.printStackTrace();
		}

	}
}
