package es.ieslavereda.server.controler;

import es.ieslavereda.model.AuthenticateData;
import es.ieslavereda.model.Result;
import es.ieslavereda.model.clases.Cliente;
import es.ieslavereda.server.model.IClienteService;
import es.ieslavereda.server.model.ImpClienteService;
import es.ieslavereda.server.model.JsonTransformer;
import oracle.ons.Cli;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

public class ClienteController {
    static Logger logger = LoggerFactory.getLogger(ClienteController.class);

    private static IClienteService service = new ImpClienteService();
    private static JsonTransformer<Cliente> jsonTransformer = new JsonTransformer<>();

    public static Result<Cliente> authenticate(Request request, Response response) {
        logger.info("Autenticando ");
        String body = request.body();

        JsonTransformer<AuthenticateData> jst = new JsonTransformer<>();
        AuthenticateData ad = jst.getObjet(body,AuthenticateData.class);

        Result<Cliente> result = service.auth(ad);

        if(result instanceof Result.Success){
            response.status(200);
        } else {
            response.status(404);
        }
        return result;
    }

}
