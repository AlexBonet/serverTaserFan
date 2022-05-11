package es.ieslavereda.server.controler;

import es.ieslavereda.model.Result;
import es.ieslavereda.model.clases.Vehiculo;
import es.ieslavereda.server.model.IVehiculoService;
import es.ieslavereda.server.model.ImpVehiculoService;
import es.ieslavereda.server.model.JsonTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import java.util.List;

//TODO posar metodos
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


        return null;
    }

    public static Result<Vehiculo> delVehiculos(Request request, Response response) {
        logger.info("Eliminando vehiculo... ");

        return null;
    }

    public static Result<Vehiculo> updVehiculos(Request request, Response response) {
        logger.info("Actualizando vehiculo... ");

        return null;
    }
}
