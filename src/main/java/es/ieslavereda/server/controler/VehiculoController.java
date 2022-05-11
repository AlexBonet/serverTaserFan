package es.ieslavereda.server.controler;

import es.ieslavereda.model.clases.Vehiculo;
import es.ieslavereda.server.model.IVehiculoService;
import es.ieslavereda.server.model.ImpVehiculoService;
import es.ieslavereda.server.model.JsonTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VehiculoController {
    static Logger logger = LoggerFactory.getLogger(VehiculoController.class);

    private static IVehiculoService service = new ImpVehiculoService();
    private static JsonTransformer<Vehiculo> jsonTransformer = new JsonTransformer<>();
}
