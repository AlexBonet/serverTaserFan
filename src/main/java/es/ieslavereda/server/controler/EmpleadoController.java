package es.ieslavereda.server.controler;

import es.ieslavereda.model.AuthenticateData;
import es.ieslavereda.model.Result;
import es.ieslavereda.model.clases.Cliente;
import es.ieslavereda.model.clases.Empleado;
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
    private static JsonTransformer<Empleado> jsonTransformer = new JsonTransformer<>();

    public static Result<Empleado> authenticate(Request request, Response response) {
        logger.info("Autenticando... ");
        String body = request.body();

        JsonTransformer<AuthenticateData> jst = new JsonTransformer<>();
        AuthenticateData ad = jst.getObjet(body,AuthenticateData.class);

        Result<Empleado> result = service.auth(ad);

        if(result instanceof Result.Success){
            response.status(200);
        } else {
            Result.Error error = (Result.Error)result;
            response.status(error.getCode());
        }
        return result;
    }

    public static List<Empleado> getEmpleados(Request request, Response response) {
        logger.info("Obteniendo todos los empleados... ");
        return service.getAll();
    }

    public static Empleado getEmpleado(Request request, Response response) {
        logger.info("Obteniendo empleado... ");

        String id = request.queryParams("id");

        return service.get(id);
    }
}
