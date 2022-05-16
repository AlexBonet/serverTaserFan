package es.ieslavereda.server.model;

import es.ieslavereda.model.Result;
import es.ieslavereda.model.clases.vehiculos.*;

import java.util.List;

public interface IVehiculoService {
//    List<Vehiculo> getAll(TipoVehiculo vehiculo);
//    Result<Vehiculo> delete(TipoVehiculo vehiculo,String matricula);
//    Result<Vehiculo> update(TipoVehiculo vehiculo,Vehiculo v);
//    Result<Vehiculo> add(TipoVehiculo vehiculo,Vehiculo v);
//    Result<Vehiculo> get(TipoVehiculo vehiculo,String matricula); //fa falta?

    /*COCHES*/
    List<Coche> getAllC();
    Result<Coche> deleteC(String matricula);
    Result<Coche> updateC(Coche c);
    Result<Coche> addC(Coche c);

    /*MOTOS*/
    List<Moto> getAllM();
    Result<Moto> deleteM(String matricula);
    Result<Moto> updateM(Moto m);
    Result<Moto> addM(Moto m);

    /*BICIS*/
    List<Bicicleta> getAllB();
    Result<Bicicleta> deleteB(String matricula);
    Result<Bicicleta> updateB(Bicicleta b);
    Result<Bicicleta> addB(Bicicleta b);

    /*PATIN*/
    List<Patinete> getAllP();
    Result<Patinete> deleteP(String matricula);
    Result<Patinete> updateP(Patinete p);
    Result<Patinete> addP(Patinete p);

}
