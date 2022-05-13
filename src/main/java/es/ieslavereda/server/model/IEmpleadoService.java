package es.ieslavereda.server.model;

import es.ieslavereda.model.AuthenticateData;
import es.ieslavereda.model.clases.Cliente;
import es.ieslavereda.model.Result;
import es.ieslavereda.model.clases.Empleado;

import java.util.List;

public interface IEmpleadoService {
    List<Empleado> getAll();
    Result<Empleado> auth(AuthenticateData ad);
    Empleado get(String id); //fa falta? y si vuic enseñar tota la info?

}
