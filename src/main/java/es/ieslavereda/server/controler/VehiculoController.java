package es.ieslavereda.server.controler;

import es.ieslavereda.model.Result;
import es.ieslavereda.model.clases.vehiculos.*;
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


    /*Obtener*/
    public static List<Coche> getCoches(Request request, Response response) {
        logger.info("Obteniendo vehiculos... ");
        return service.getAllC();
    }
    public static List<Moto> getMotos(Request request, Response response) {
        logger.info("Obteniendo vehiculos... ");
        return service.getAllM();
    }
    public static List<Bicicleta> getBicis(Request request, Response response) {
        logger.info("Obteniendo vehiculos... ");
        return service.getAllB();
    }
    public static List<Patinete> getPatines(Request request, Response response) {
        logger.info("Obteniendo vehiculos... ");
        return service.getAllP();
    }

    /*Añadir*/
    public static Result<Coche> addCoche(Request request, Response response) {
        logger.info("Añadiendo vehiculo... ");

        Coche x = (Coche) jsonTransformer.getObjet(request.body(), Vehiculo.class);
        Result<Coche> result = service.addC(x);

        if(result instanceof Result.Success)
            response.status(200);
        else {
            Result.Error error = (Result.Error)result;
            response.status(error.getCode());
        }

        return result;
    }
    public static Result<Moto> addMotos(Request request, Response response) {
        logger.info("Añadiendo vehiculo... ");

        Moto x = (Moto) jsonTransformer.getObjet(request.body(), Vehiculo.class);
        Result<Moto> result = service.addM(x);

        if(result instanceof Result.Success)
            response.status(200);
        else {
            Result.Error error = (Result.Error)result;
            response.status(error.getCode());
        }

        return result;
    }
    public static Result<Bicicleta> addBicis(Request request, Response response) {
        logger.info("Añadiendo vehiculo... ");

        Bicicleta x = (Bicicleta) jsonTransformer.getObjet(request.body(), Vehiculo.class);
        Result<Bicicleta> result = service.addB(x);

        if(result instanceof Result.Success)
            response.status(200);
        else {
            Result.Error error = (Result.Error)result;
            response.status(error.getCode());
        }

        return result;
    }
    public static Result<Patinete> addPatin(Request request, Response response) {
        logger.info("Añadiendo vehiculo... ");

        Patinete x = (Patinete) jsonTransformer.getObjet(request.body(), Vehiculo.class);
        Result<Patinete> result = service.addP(x);

        if(result instanceof Result.Success)
            response.status(200);
        else {
            Result.Error error = (Result.Error)result;
            response.status(error.getCode());
        }

        return result;
    }

    /*Eliminar*/
    public static Result<Coche> delCoche(Request request, Response response) {
        logger.info("Eliminando vehiculo... ");
        return service.deleteC(request.queryParams("matricula"));
    }
    public static Result<Moto> delMotos(Request request, Response response) {
        logger.info("Eliminando vehiculo... ");
        return service.deleteM(request.queryParams("matricula"));
    }
    public static Result<Bicicleta> delBicis(Request request, Response response) {
        logger.info("Eliminando vehiculo... ");
        return service.deleteB(request.queryParams("matricula"));
    }
    public static Result<Patinete> delPatin(Request request, Response response) {
        logger.info("Eliminando vehiculo... ");
        return service.deleteP(request.queryParams("matricula"));
    }

    /*Actualizar*/
    public static Result<Coche> updCoche(Request request, Response response) {
        logger.info("Actualizando vehiculo... ");

        Coche x = (Coche) jsonTransformer.getObjet(request.body(), Vehiculo.class);
        Result<Coche> result = service.updateC(x);

        if(result instanceof Result.Success)
            response.status(200);
        else {
            Result.Error error = (Result.Error)result;
            response.status(error.getCode());
        }

        return result;
    }
    public static Result<Moto> updMotos(Request request, Response response) {
        logger.info("Actualizando vehiculo... ");

        Moto x = (Moto) jsonTransformer.getObjet(request.body(), Vehiculo.class);
        Result<Moto> result = service.updateM(x);

        if(result instanceof Result.Success)
            response.status(200);
        else {
            Result.Error error = (Result.Error)result;
            response.status(error.getCode());
        }

        return result;
    }
    public static Result<Bicicleta> updBicis(Request request, Response response) {
        logger.info("Actualizando vehiculo... ");

        Bicicleta x = (Bicicleta) jsonTransformer.getObjet(request.body(), Vehiculo.class);
        Result<Bicicleta> result = service.updateB(x);

        if(result instanceof Result.Success)
            response.status(200);
        else {
            Result.Error error = (Result.Error)result;
            response.status(error.getCode());
        }

        return result;
    }
    public static Result<Patinete> updPatin(Request request, Response response) {
        logger.info("Actualizando vehiculo... ");

        Patinete x = (Patinete) jsonTransformer.getObjet(request.body(), Vehiculo.class);
        Result<Patinete> result = service.updateP(x);

        if(result instanceof Result.Success)
            response.status(200);
        else {
            Result.Error error = (Result.Error)result;
            response.status(error.getCode());
        }

        return result;
    }

}
