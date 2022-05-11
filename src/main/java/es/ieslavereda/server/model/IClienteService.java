package es.ieslavereda.server.model;

import es.ieslavereda.model.AuthenticateData;
import es.ieslavereda.model.clases.Cliente;
import es.ieslavereda.model.Result;

import java.util.List;

public interface IClienteService {
    List<Cliente> getAll();
    Result<Cliente> auth(AuthenticateData ad);
    Cliente get(String id); //fa falta? y si vuic enseñar tota la info?

}
