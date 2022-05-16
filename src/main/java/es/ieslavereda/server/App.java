package es.ieslavereda.server;

import es.ieslavereda.server.controler.EmpleadoController;
import es.ieslavereda.server.controler.VehiculoController;
import es.ieslavereda.server.model.JsonTransformer;
import static spark.Spark.*;

public class App {
    //TODO cambiar el nom de VehiculoController::...
    public static void main(String[] args) {
        //Empleados
        post(API.Routes.AUTHENTICATE, EmpleadoController::authenticate, new JsonTransformer<>());
        get(API.Routes.ALL_EMPLEADOS, EmpleadoController::getEmpleados, new JsonTransformer<>());
        get(API.Routes.GET_EMPLEADO, EmpleadoController::getEmpleado, new JsonTransformer<>());

        //COCHE
        get(API.Routes.COCHE, VehiculoController::getVehiculos, new JsonTransformer<>());
        post(API.Routes.COCHE, VehiculoController::addVehiculos, new JsonTransformer<>());
        delete(API.Routes.COCHE, VehiculoController::delVehiculos, new JsonTransformer<>());
        put(API.Routes.COCHE, VehiculoController::updVehiculos, new JsonTransformer<>());

        //MOTOS
        get(API.Routes.MOTOS, VehiculoController::getVehiculos, new JsonTransformer<>());
        post(API.Routes.MOTOS, VehiculoController::addVehiculos, new JsonTransformer<>());
        delete(API.Routes.MOTOS, VehiculoController::delVehiculos, new JsonTransformer<>());
        put(API.Routes.MOTOS, VehiculoController::updVehiculos, new JsonTransformer<>());

        //PATIN
        get(API.Routes.PATIN, VehiculoController::getVehiculos, new JsonTransformer<>());
        post(API.Routes.PATIN, VehiculoController::addVehiculos, new JsonTransformer<>());
        delete(API.Routes.PATIN, VehiculoController::delVehiculos, new JsonTransformer<>());
        put(API.Routes.PATIN, VehiculoController::updVehiculos, new JsonTransformer<>());

        //BICIS
        get(API.Routes.BICIS, VehiculoController::getVehiculos, new JsonTransformer<>());
        post(API.Routes.BICIS, VehiculoController::addVehiculos, new JsonTransformer<>());
        delete(API.Routes.BICIS, VehiculoController::delVehiculos, new JsonTransformer<>());
        put(API.Routes.BICIS, VehiculoController::updVehiculos, new JsonTransformer<>());
    }
}
