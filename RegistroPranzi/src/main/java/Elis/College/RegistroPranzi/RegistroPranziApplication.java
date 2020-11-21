package Elis.College.RegistroPranzi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableCaching
@EnableWebMvc
@SpringBootApplication
@EnableJpaAuditing
public class RegistroPranziApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistroPranziApplication.class, args);
	}

}
