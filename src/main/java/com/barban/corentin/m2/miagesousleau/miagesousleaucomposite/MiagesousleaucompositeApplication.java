package com.barban.corentin.m2.miagesousleau.miagesousleaucomposite;

import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.repo.EnseignantCoursImpl;
import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.repo.EnseignantCoursRepository;
import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.repo.ParticipantCoursImpl;
import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.repo.ParticipantCoursRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MiagesousleaucompositeApplication {

	public static final String USER_SERVICE_URL = "http://localhost:8088/";
	public static final String COURS_SERVICE_URL = "http://localhost:8089/";


	public static void main(String[] args) {
		SpringApplication.run(MiagesousleaucompositeApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public EnseignantCoursRepository enseignantRepository(){
		return new EnseignantCoursImpl(USER_SERVICE_URL,COURS_SERVICE_URL);
	}

	@Bean
	public ParticipantCoursRepository participantRepository(){
		return new ParticipantCoursImpl(USER_SERVICE_URL,COURS_SERVICE_URL);
	}
}
