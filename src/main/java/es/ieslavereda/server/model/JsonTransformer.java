package es.ieslavereda.server.model;

import com.google.gson.Gson;
import spark.ResponseTransformer;

public class JsonTransformer<T> implements ResponseTransformer {
    private Gson gson = new Gson();

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }

    public T getObjet(String json, Class<T> clazz){
        return gson.fromJson(json,clazz);
    }
}
