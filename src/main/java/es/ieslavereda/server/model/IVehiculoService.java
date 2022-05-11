package es.ieslavereda.server.model;

import es.ieslavereda.model.Result;
import es.ieslavereda.model.clases.Vehiculo;

import java.util.List;

public interface IVehiculoService {
    List<Vehiculo> getAll();
    Result<Vehiculo> delete(String matricula);
    Result<Vehiculo> update(Vehiculo v);
    Result<Vehiculo> add(Vehiculo v);
    Result<Vehiculo> get(String matricula); //fa falta?
}
