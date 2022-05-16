package es.ieslavereda.server;

import es.ieslavereda.server.controler.EmpleadoController;
import es.ieslavereda.server.controler.VehiculoController;
import es.ieslavereda.server.model.JsonTransformer;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        //Empleados
        post(API.Routes.AUTHENTICATE, EmpleadoController::authenticate, new JsonTransformer<>());
        get(API.Routes.ALL_EMPLEADOS, EmpleadoController::getEmpleados, new JsonTransformer<>());
        get(API.Routes.GET_EMPLEADO, EmpleadoController::getEmpleado, new JsonTransformer<>());

        //COCHE
        get(API.Routes.COCHE, VehiculoController::getCoches, new JsonTransformer<>());
        post(API.Routes.COCHE, VehiculoController::addCoche, new JsonTransformer<>());
        delete(API.Routes.COCHE, VehiculoController::delCoche, new JsonTransformer<>());
        put(API.Routes.COCHE, VehiculoController::updCoche, new JsonTransformer<>());

        //MOTOS
        get(API.Routes.MOTOS, VehiculoController::getMotos, new JsonTransformer<>());
        post(API.Routes.MOTOS, VehiculoController::addMotos, new JsonTransformer<>());
        delete(API.Routes.MOTOS, VehiculoController::delMotos, new JsonTransformer<>());
        put(API.Routes.MOTOS, VehiculoController::updMotos, new JsonTransformer<>());

        //PATIN
        get(API.Routes.PATIN, VehiculoController::getPatines, new JsonTransformer<>());
        post(API.Routes.PATIN, VehiculoController::addPatin, new JsonTransformer<>());
        delete(API.Routes.PATIN, VehiculoController::delPatin, new JsonTransformer<>());
        put(API.Routes.PATIN, VehiculoController::updPatin, new JsonTransformer<>());

        //BICIS
        get(API.Routes.BICIS, VehiculoController::getBicis, new JsonTransformer<>());
        post(API.Routes.BICIS, VehiculoController::addBicis, new JsonTransformer<>());
        delete(API.Routes.BICIS, VehiculoController::delBicis, new JsonTransformer<>());
        put(API.Routes.BICIS, VehiculoController::updBicis, new JsonTransformer<>());
    }
}
