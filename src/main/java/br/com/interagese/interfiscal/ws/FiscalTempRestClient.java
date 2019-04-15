/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.interagese.interfiscal.ws;

import br.com.interagese.interfiscal.entity.Fiscaltemp;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Bruno Martins
 */
public class FiscalTempRestClient extends WebService {

    private final Integer maxRowCount;

    public FiscalTempRestClient() {
        this.maxRowCount = 1000;

    }

    public List<Fiscaltemp> getProductImportation() {
        List<Fiscaltemp> result = new ArrayList<>();
        int countMax = getClientService().path("getCount").request(MediaType.APPLICATION_JSON).get(Integer.class);
        int diference = countMax / maxRowCount;

        if (countMax % maxRowCount != 0) {
            diference++;
        }
        for (int i = 0; i < diference; i++) {
            result.addAll(getClientService().path("getProductImportation").queryParam("position", i).request(MediaType.APPLICATION_JSON).get(new GenericType<List<Fiscaltemp>>() {
            }));
        }

        return result;
    }

//    public void getEnviarDados() {
//        getClientService().path("nome do metodo ?valor1=10&valor2=20").request(MediaType.APPLICATION_JSON).post(Entity.json(""));
//    }
}
