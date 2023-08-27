package com.fumutik.manageclient;
import com.fumutik.manageclient.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


//@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
@SpringBootApplication
public class ManageclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManageclientApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	/*@Bean
	CommandLineRunner commandLineRunner(PatientRepository patientRepository){
		return args -> {
			patientRepository.save(new Patient(null,"Elie",new Date(),false,12));
			patientRepository.save(new Patient(null,"Eto'o",new Date(),true,120));
			patientRepository.save(new Patient(null,"Dédé",new Date(),true,32));
			patientRepository.save(new Patient(null,"Frida",new Date(),false,48));

			patientRepository.findAll().forEach(p->{
				System.out.println(p.getNom());
			});
		};
	}*/
}
