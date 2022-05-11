package es.ieslavereda.server.model;

import es.ieslavereda.model.AuthenticateData;
import es.ieslavereda.model.clases.Cliente;
import es.ieslavereda.model.Result;

import java.util.List;
//TODO
public class ImpClienteService implements IClienteService{
    @Override
    public List<Cliente> getAll() {
        return null;
    }

    @Override
    public Result<Cliente> auth(AuthenticateData ad) {
        return null;
    }

    @Override
    public Result<Cliente> get() {
        return null;
    }
}
