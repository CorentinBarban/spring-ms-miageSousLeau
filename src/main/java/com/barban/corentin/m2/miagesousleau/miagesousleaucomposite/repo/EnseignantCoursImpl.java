package com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.client.RestTemplate;

public class EnseignantCoursImpl implements EnseignantCoursRepository{

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplateUser;

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplateCours;

    protected String serviceUrlUser;

    protected String serviceUrlCours;

    public EnseignantCoursImpl(String serviceUser, String serviceCours) {
        this.serviceUrlUser = serviceUser;
        this.serviceUrlCours = serviceCours;
    }
}
