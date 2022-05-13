package es.ieslavereda.server;
//TODO

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

        //Vehiculos
        get(API.Routes.ALL_VEHI, VehiculoController::getVehiculos, new JsonTransformer<>());
        post(API.Routes.ADD_VEHI, VehiculoController::addVehiculos, new JsonTransformer<>());
        delete(API.Routes.DEL_VEHI, VehiculoController::delVehiculos, new JsonTransformer<>());
        put(API.Routes.UPD_VEHI, VehiculoController::updVehiculos, new JsonTransformer<>());
    }
}
