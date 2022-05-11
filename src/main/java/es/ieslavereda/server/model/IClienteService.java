package es.ieslavereda.server.model;

import es.ieslavereda.model.clases.Cliente;
import es.ieslavereda.model.Result;

import java.util.List;

public interface IClienteService {
    List<Cliente> getAll();
    Result<Cliente> delete();
    Result<Cliente> update();
    Result<Cliente> add();
    Result<Cliente> get(); //fa falta?
}
