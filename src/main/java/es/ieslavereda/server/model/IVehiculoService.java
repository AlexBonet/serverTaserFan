package es.ieslavereda.server.model;

import es.ieslavereda.model.Result;
import es.ieslavereda.model.clases.vehiculos.*;

import java.util.List;

public interface IVehiculoService {
    List<Vehiculo> getAll(String tipo);
//    Result<Vehiculo> delete(TipoVehiculo vehiculo,String matricula);
//    Result<Vehiculo> update(TipoVehiculo vehiculo,Vehiculo v);
//    Result<Vehiculo> add(TipoVehiculo vehiculo,Vehiculo v);
//    Result<Vehiculo> get(TipoVehiculo vehiculo,String matricula); //fa falta?

    /*COCHES*/
    Result<Coche> getC(String matricula);
    Result<Coche> deleteC(String matricula);
    Result<Coche> updateC(Coche c);
    Result<Coche> addC(Coche c);

    /*MOTOS*/
    Result<Moto> getM(String matricula);
    Result<Moto> deleteM(String matricula);
    Result<Moto> updateM(Moto m);
    Result<Moto> addM(Moto m);

    /*BICIS*/
    Result<Bicicleta> getB(String matricula);
    Result<Bicicleta> deleteB(String matricula);
    Result<Bicicleta> updateB(Bicicleta b);
    Result<Bicicleta> addB(Bicicleta b);

    /*PATIN*/
    Result<Patinete> getP(String matricula);
    Result<Patinete> deleteP(String matricula);
    Result<Patinete> updateP(Patinete p);
    Result<Patinete> addP(Patinete p);

    List<Vehiculo> getAl();
}
