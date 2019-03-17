/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.interagese.interfiscal.ws;

import java.util.List;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Bruno Martins
 */
public class FiscalTempRestClient extends WebService {

    public void getReceberDados() {
        List<String> array = getClientService().path("nome do metodo").request(MediaType.APPLICATION_JSON).get(new GenericType<List<String>>() {
        });
    }

    public void getEnviarDados() {
        getClientService().path("nome do metodo ?valor1=10&valor2=20").request(MediaType.APPLICATION_JSON).post(Entity.json(""));
    }

}
