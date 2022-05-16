package es.ieslavereda.server.model;

import es.ieslavereda.model.Result;
import es.ieslavereda.model.clases.vehiculos.Vehiculo;

import java.util.List;

public interface IVehiculoService {
//    List<Vehiculo> getAll(TipoVehiculo vehiculo);
//    Result<Vehiculo> delete(TipoVehiculo vehiculo,String matricula);
//    Result<Vehiculo> update(TipoVehiculo vehiculo,Vehiculo v);
//    Result<Vehiculo> add(TipoVehiculo vehiculo,Vehiculo v);
//    Result<Vehiculo> get(TipoVehiculo vehiculo,String matricula); //fa falta?

    /*COCHES*/
    List<Vehiculo> getAllC();
    Result<Vehiculo> deleteC(String matricula);
    Result<Vehiculo> updateC(Vehiculo v);
    Result<Vehiculo> addC(Vehiculo v);

    /*MOTOS*/
    List<Vehiculo> getAllM();
    Result<Vehiculo> deleteM(String matricula);
    Result<Vehiculo> updateM(Vehiculo v);
    Result<Vehiculo> addM(Vehiculo v);

    /*BICIS*/
    List<Vehiculo> getAllB();
    Result<Vehiculo> deleteB(String matricula);
    Result<Vehiculo> updateB(Vehiculo v);
    Result<Vehiculo> addB(Vehiculo v);

    /*PATIN*/
    List<Vehiculo> getAllP();
    Result<Vehiculo> deleteP(String matricula);
    Result<Vehiculo> updateP(Vehiculo v);
    Result<Vehiculo> addP(Vehiculo v);

}
