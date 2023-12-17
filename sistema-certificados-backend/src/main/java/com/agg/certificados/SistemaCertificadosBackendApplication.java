package com.agg.certificados;

import com.agg.certificados.entity.Rol;
import com.agg.certificados.entity.User;
import com.agg.certificados.entity.UserRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.agg.certificados.services.usersServices.IUserService;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SistemaCertificadosBackendApplication implements CommandLineRunner {

	@Autowired
	private IUserService userService;

	public static void main(String[] args) {
		SpringApplication.run(SistemaCertificadosBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*User user = new User();

		user.setFull_name("Steven Gonzalez");
		user.setUsername("stevengm45");
		user.setPassword("12345");
		user.setEmail("steven@gmail.com");
		user.setProfile("foto.png");

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
*/
	}
}
