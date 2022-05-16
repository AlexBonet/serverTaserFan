package es.ieslavereda.server.controler;

import es.ieslavereda.model.Result;
import es.ieslavereda.model.clases.vehiculos.Vehiculo;
import es.ieslavereda.server.model.IVehiculoService;
import es.ieslavereda.server.model.ImpVehiculoService;
import es.ieslavereda.server.model.JsonTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import java.util.List;

public class VehiculoController {
    static Logger logger = LoggerFactory.getLogger(VehiculoController.class);

    private static IVehiculoService service = new ImpVehiculoService();
    private static JsonTransformer<Vehiculo> jsonTransformer = new JsonTransformer<>();

    public static List<Vehiculo> getVehiculos(Request request, Response response) {
        logger.info("Obteniendo vehiculos... ");

        return service.getAll();
    }

    public static Result<Vehiculo> addVehiculos(Request request, Response response) {
        logger.info("Añadiendo vehiculo... ");

        Vehiculo v = jsonTransformer.getObjet(request.body(), Vehiculo.class);
        Result<Vehiculo> result = service.add(v);

        if(result instanceof Result.Success)
            response.status(200);
        else {
            Result.Error error = (Result.Error)result;
            response.status(error.getCode());
        }

        return result;
    }

    public static Result<Vehiculo> delVehiculos(Request request, Response response) {
        logger.info("Eliminando vehiculo... ");

        return service.delete(request.queryParams("matricula"));
    }

    public static Result<Vehiculo> updVehiculos(Request request, Response response) {
        logger.info("Actualizando vehiculo... ");

        Vehiculo v = jsonTransformer.getObjet(request.body(), Vehiculo.class);
        Result<Vehiculo> result = service.update(v);

        if(result instanceof Result.Success)
            response.status(200);
        else {
            Result.Error error = (Result.Error)result;
            response.status(error.getCode());
        }

        return result;
    }
}
