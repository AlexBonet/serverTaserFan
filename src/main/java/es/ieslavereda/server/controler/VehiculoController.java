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

/**
 * TODO FER QUE EL GET ALL PILLE DEL PROCEDURE DE LISTARVEHICULOS
 */

public class VehiculoController {
    static Logger logger = LoggerFactory.getLogger(VehiculoController.class);

    private static IVehiculoService service = new ImpVehiculoService();

    private static JsonTransformer<Vehiculo> jsonTransformer = new JsonTransformer<>();
    private static JsonTransformer<Bicicleta> jsonTransformerB = new JsonTransformer<>();
    private static JsonTransformer<Coche> jsonTransformerC = new JsonTransformer<>();
    private static JsonTransformer<Moto> jsonTransformerM = new JsonTransformer<>();
    private static JsonTransformer<Patinete> jsonTransformerP = new JsonTransformer<>();

    /**Obtener todos los vehiculos*/
    public static List<Vehiculo> getAll(Request request, Response response) {
        logger.info("Obteniendo vehiculos... ");
        return service.getAll(request.queryParams("tipo"));
    }

    public static Object getAl(Request request, Response response) {
        logger.info("Obteniendo vehiculos... ");
        return service.getAl();
    }


    /**Obtener*/
    public static Result<Coche> getCoche(Request request, Response response) {
        logger.info("Obteniedo coche... ");
        Result<Coche> result = service.getC(request.queryParams("matricula"));

        if(result instanceof Result.Success)
            response.status(200);
        else {
            Result.Error error = (Result.Error)result;
            response.status(error.getCode());
        }

        return result;
    }
    public static Result<Moto> getMoto(Request request, Response response) {
        logger.info("Obteniedo moto... ");
        Result<Moto> result = service.getM(request.queryParams("matricula"));

        if(result instanceof Result.Success)
            response.status(200);
        else {
            Result.Error error = (Result.Error)result;
            response.status(error.getCode());
        }

        return result;
    }
    public static Result<Bicicleta> getBici(Request request, Response response) {
        logger.info("Obteniedo bicicleta... ");
        Result<Bicicleta> result = service.getB(request.queryParams("matricula"));

        if(result instanceof Result.Success)
            response.status(200);
        else {
            Result.Error error = (Result.Error)result;
            response.status(error.getCode());
        }

        return result;
    }
    public static Result<Patinete> getPatin(Request request, Response response) {
        logger.info("Obteniedo patinete... ");
        Result<Patinete> result = service.getP(request.queryParams("matricula"));

        if(result instanceof Result.Success)
            response.status(200);
        else {
            Result.Error error = (Result.Error)result;
            response.status(error.getCode());
        }

        return result;
    }

    /**Añadir*/
    public static Result<Coche> addCoche(Request request, Response response) {
        logger.info("Añadiendo coche... ");

        Coche x = jsonTransformerC.getObjet(request.body(), Coche.class);
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
        logger.info("Añadiendo moto... ");

        Moto x = jsonTransformerM.getObjet(request.body(), Moto.class);
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
        logger.info("Añadiendo bici... ");

        Bicicleta x = jsonTransformerB.getObjet(request.body(), Bicicleta.class);
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
        logger.info("Añadiendo patinete... ");

        Patinete x = jsonTransformerP.getObjet(request.body(), Patinete.class);
        Result<Patinete> result = service.addP(x);

        if(result instanceof Result.Success)
            response.status(200);
        else {
            Result.Error error = (Result.Error)result;
            response.status(error.getCode());
        }

        return result;
    }

    /**Eliminar*/
    public static Result<Coche> delCoche(Request request, Response response) {
        logger.info("Eliminando coche... ");
        return service.deleteC(request.queryParams("matricula"));
    }
    public static Result<Moto> delMotos(Request request, Response response) {
        logger.info("Eliminando moto... ");
        return service.deleteM(request.queryParams("matricula"));
    }
    public static Result<Bicicleta> delBicis(Request request, Response response) {
        logger.info("Eliminando bicicleta... ");
        return service.deleteB(request.queryParams("matricula"));
    }
    public static Result<Patinete> delPatin(Request request, Response response) {
        logger.info("Eliminando patinete... ");
        return service.deleteP(request.queryParams("matricula"));
    }

    /**Actualizar*/
    public static Result<Coche> updCoche(Request request, Response response) {
        logger.info("Actualizando coche... ");

        Coche x = jsonTransformerC.getObjet(request.body(), Coche.class);
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
        logger.info("Actualizando moto... ");

        Moto x = jsonTransformerM.getObjet(request.body(), Moto.class);
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
        logger.info("Actualizando bici... ");

        Bicicleta x = jsonTransformerB.getObjet(request.body(), Bicicleta.class);
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
        logger.info("Actualizando patin... ");

        Patinete x = jsonTransformerP.getObjet(request.body(), Patinete.class);
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
