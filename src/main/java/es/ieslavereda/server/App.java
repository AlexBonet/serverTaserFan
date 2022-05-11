package es.ieslavereda.server;
//TODO

import es.ieslavereda.server.controler.ClienteController;
import es.ieslavereda.server.controler.VehiculoController;
import es.ieslavereda.server.model.JsonTransformer;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        //Clientes
        post(API.Routes.AUTHENTICATE, ClienteController::authenticate, new JsonTransformer<>());
        get(API.Routes.ALL_CLIENTES, ClienteController::getClientes, new JsonTransformer<>());
        get(API.Routes.GET_CLIENTE, ClienteController::getCliente, new JsonTransformer<>());

        //Vehiculos
        get(API.Routes.ALL_VEHI, VehiculoController::getVehiculos, new JsonTransformer<>());
        post(API.Routes.ADD_VEHI, VehiculoController::addVehiculos, new JsonTransformer<>());
        delete(API.Routes.DEL_VEHI, VehiculoController::delVehiculos, new JsonTransformer<>());
        put(API.Routes.UPD_VEHI, VehiculoController::updVehiculos, new JsonTransformer<>());
    }
}
