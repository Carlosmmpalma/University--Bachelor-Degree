package spring.boot.security.jdbc.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** a anotação abaixo seria dispensável se as outras classes estivessem neste package 
 * https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/autoconfigure/SpringBootApplication.html#scanBasePackages--
 */
@SpringBootApplication(scanBasePackages = "spring.boot.security.jdbc.auth")
public class SpringSecurityFormBasedJdbcAuthApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityFormBasedJdbcAuthApp.class, args);

                System.out.println("TWeb: aceder a http://localhost:8080/");
                System.out.println("\t credenciais: admin / admin ");
	}

}
