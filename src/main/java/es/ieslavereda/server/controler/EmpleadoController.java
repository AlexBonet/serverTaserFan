package es.ieslavereda.server.controler;

import es.ieslavereda.model.AuthenticateData;
import es.ieslavereda.model.Result;
import es.ieslavereda.model.clases.Cliente;
import es.ieslavereda.server.model.IEmpleadoService;
import es.ieslavereda.server.model.ImpEmpleadoService;
import es.ieslavereda.server.model.JsonTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import java.util.List;

//TODO get() getAll()
public class EmpleadoController {
    static Logger logger = LoggerFactory.getLogger(EmpleadoController.class);

    private static IEmpleadoService service = new ImpEmpleadoService();
    private static JsonTransformer<Cliente> jsonTransformer = new JsonTransformer<>();

    public static Result<Cliente> authenticate(Request request, Response response) {
        logger.info("Autenticando... ");
        String body = request.body();

        JsonTransformer<AuthenticateData> jst = new JsonTransformer<>();
        AuthenticateData ad = jst.getObjet(body,AuthenticateData.class);

        Result<Cliente> result = service.auth(ad);

        if(result instanceof Result.Success){
            response.status(200);
        } else {
            Result.Error error = (Result.Error)result;
            response.status(error.getCode());
        }
        return result;
    }

    public static List<Cliente> getClientes(Request request, Response response) {
        logger.info("Obteniendo todos los clientes... ");
        return service.getAll();
    }

    public static Cliente getCliente(Request request, Response response) {
        logger.info("Obteniendo cliente... ");

        String id = request.queryParams("id");

        return service.get(id);
    }
}
