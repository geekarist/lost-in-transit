package com.github.geekarist.lostintransit;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import com.github.geekarist.lostintransit.MyResource;

public class MyResourceTest {

    @Test
    public void shouldComputeUrlForTransportationScoreService() {
        // GIVEN
        String home = "9, Rue de la Croix Faubin, 75011, Paris";
        MyResource resource = new MyResource();

        // WHEN
        String score = resource.getIt(home);
        
        // THEN
        // wiremock should respond classpath:ratp.html to 
        // "http://www.ratp.fr/itineraires/fr/ratp/resultat-detaille/start/9%2C+Rue+de+la+Croix+Faubin%2C+75011%2C+Paris/end/2%2C+Place+de+La+D%C3%A9fense%2C+92800%2C+Puteaux"); 
        assertThat(score).isEqualTo("195");
    }
}
