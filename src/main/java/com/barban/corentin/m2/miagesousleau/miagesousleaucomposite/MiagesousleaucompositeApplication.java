package com.barban.corentin.m2.miagesousleau.miagesousleaucomposite;

import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.repo.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
public class MiagesousleaucompositeApplication {

    public static final String USER_SERVICE_URL = "http://GESTIONMEMBRE/";
    public static final String COURS_SERVICE_URL = "http://GESTIONCOURS/";
    public static final String PISCINE_SERVICE_URL = "https://data.toulouse-metropole.fr/api/datasets/1.0/piscines/";


    public static void main(String[] args) {
        SpringApplication.run(MiagesousleaucompositeApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @Bean
    public EnseignantCoursRepository enseignantRepository() {
        return new EnseignantCoursImpl(USER_SERVICE_URL, COURS_SERVICE_URL, PISCINE_SERVICE_URL);
    }

    @Bean
    public ParticipantCoursRepository participantRepository() {
        return new ParticipantCoursImpl(USER_SERVICE_URL, COURS_SERVICE_URL);
    }

    @Bean
    public PresidentCoursRepository presidentCoursRepository() {
        return new PresidentCoursImpl(USER_SERVICE_URL, COURS_SERVICE_URL);
    }
}
