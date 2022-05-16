package es.ieslavereda.server.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import es.ieslavereda.model.clases.vehiculos.Bicicleta;
import es.ieslavereda.model.clases.vehiculos.Coche;
import es.ieslavereda.model.clases.vehiculos.Moto;
import es.ieslavereda.model.clases.vehiculos.Patinete;
import spark.ResponseTransformer;

import java.lang.reflect.Type;

public class JsonTransformer<T> implements ResponseTransformer {
    private Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }

    public T getObjet(String json, Class<T> clazz){
        return gson.fromJson(json,clazz);
    }
}
