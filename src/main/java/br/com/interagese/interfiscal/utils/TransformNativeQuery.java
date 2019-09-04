/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.interagese.interfiscal.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adam
 */
public class TransformNativeQuery {

    private final Class clazz;

    public TransformNativeQuery(Class clazz) {
        this.clazz = clazz;
    }

    public List execute(List<Object[]> list) throws InstantiationException, IllegalAccessException {

        List resultado = new ArrayList<>();
        for (Object[] o : list) {

            Object objectResult = clazz.newInstance();
            resultado.add(objectResult);

            int numField = 0;
            for (Object value : o) {

                Field field = objectResult.getClass().getDeclaredFields()[numField];
                field.setAccessible(true);
                field.set(objectResult, value);
                
                numField++;
            }

        }

        return resultado;
    }

}
