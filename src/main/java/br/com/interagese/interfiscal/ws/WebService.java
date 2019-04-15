/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.interagese.interfiscal.ws;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

/**
 *
 * @author Bruno Martins
 */
public class WebService {

    public WebTarget getClientService() {
        Client result = new ResteasyClientBuilder().build();
        return result.target("http://localhost:8080/inter-fiscal-service/api/services/");
    }

}
