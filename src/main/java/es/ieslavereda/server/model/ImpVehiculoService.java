package es.ieslavereda.server.model;

import es.ieslavereda.model.MyDataSource;
import es.ieslavereda.model.Result;
import es.ieslavereda.model.clases.vehiculos.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Per ara crec que getALL y delete de tots está correcte
 */
//TODO
public class ImpVehiculoService implements IVehiculoService{
    //    @Override
//    public List<Vehiculo> getAll() {
//        List<Vehiculo> vehiculos = new ArrayList<>();
//
//        DataSource ds = MyDataSource.getMyOracleDataSource();
//        String sql = "SELECT * FROM vehiculo";
//        try (Connection con = ds.getConnection();
//             Statement stmt = con.createStatement();
//             ResultSet rs = stmt.executeQuery(sql)) {
//
//            Vehiculo vehiculo;
//
//            while (rs.next()) {
//                vehiculo = new Vehiculo(rs.getString("matricula"), rs.getFloat("precioHora"),
//                        rs.getString("marca"), rs.getString("descripcion"),
//                        rs.getString("color"),rs.getInt("bateria"),
//                        rs.getDate("fechaAdq"),rs.getString("estado"),rs.getInt("idCarnet"),
//                        rs.getTimestamp("changeDts"),rs.getString("changeBy"));
//                vehiculos.add(vehiculo);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return vehiculos;
//    }
//
//    @Override
//    public Result<Vehiculo> delete(String matricula) {
//        DataSource ds= MyDataSource.getMyOracleDataSource();
//        String sql = "DELETE FROM vehiculo WHERE matricucula like ?;";
//
//        try (Connection con = ds.getConnection();
//            CallableStatement statement = con.prepareCall(sql);){
//
//            int pos =0;
//            statement.setString(++pos,matricula);
//
//            ResultSet rs= statement.getResultSet();
//            if (rs.next()){
//                Vehiculo v = new Vehiculo(matricula, rs.getFloat("precioHora"),
//                        rs.getString("marca"), rs.getString("descripcion"),
//                        rs.getString("color"),rs.getInt("bateria"),
//                        rs.getDate("fechaAdq"),rs.getString("estado"),rs.getInt("idCarnet"),
//                        rs.getTimestamp("changeDts"),rs.getString("changeBy"));
//                return new Result.Success<Vehiculo>(v);
//            }
//            return new Result.Error(400,"Ningun vehiculo eliminado");
//        } catch (Exception e) {
//            return new Result.Error(444,"Algun error capturado");
//        }
//    }
//
//    @Override
//    public Result<Vehiculo> update(Vehiculo v) {
//        DataSource ds= MyDataSource.getMyOracleDataSource();
//        String sql ="UPDATE vehiculo set precioHora=?, marca=?, descripcion=?, color=?, bateria=?, fechaAdq=?, estado=?, idcCarnet=?,changeDts=current_timestamp,changeBy=server" +
//                "where matricula like ?";
//        try (Connection con = ds.getConnection();
//            CallableStatement statement = con.prepareCall(sql);){
//
//            int pos = 0;
//            statement.setString(++pos, v.getMatricula());
//            statement.setFloat(++pos, v.getPrecioHora());
//            statement.setString(++pos, v.getMarca());
//            statement.setString(++pos, v.getDescripcion());
//            statement.setString(++pos, v.getColor());
//            statement.setInt(++pos, v.getBateria());
//            statement.setDate(++pos, v.getFechaAdq());
//            statement.setString(++pos, v.getEstado());
//            statement.setFloat(++pos, v.getIdCarnet());
//
//            int cant = statement.executeUpdate();
//            if (cant == 1)
//                return new Result.Success<Vehiculo>(v);
//            else
//                return new Result.Error(401,"Ninguna vehiculo actualizada");
//
//        } catch (Exception e) {
//            return new Result.Error(444,"Algun error capturado");
//        }
//    }
//
//    @Override
//    public Result<Vehiculo> add(Vehiculo v) {
//        DataSource ds= MyDataSource.getMyOracleDataSource();
//        String sql ="INSERT INTO vehiculo values (?,?,?,?,?, ?,?,?,?,?,?)";
//        try (Connection con = ds.getConnection();
//             CallableStatement statement = con.prepareCall(sql);){
//
//            int pos = 0;
//            statement.setString(++pos, v.getMatricula());
//            statement.setFloat(++pos, v.getPrecioHora());
//            statement.setString(++pos, v.getMarca());
//            statement.setString(++pos, v.getDescripcion());
//            statement.setString(++pos, v.getColor());
//            statement.setInt(++pos, v.getBateria());
//            statement.setDate(++pos, v.getFechaAdq());
//            statement.setString(++pos, v.getEstado());
//            statement.setFloat(++pos, v.getIdCarnet());
//
//            int cant = statement.executeUpdate();
//            if (cant == 1)
//                return new Result.Success<Vehiculo>(v);
//            else
//                return new Result.Error(401,"Ninguna vehiculo añadida");
//
//        } catch (Exception e) {
//            return new Result.Error(444,"Algun error capturado");
//        }
//    }
//
//    @Override
//    public Result<Vehiculo> get(String matricula) {
//        return null;
//    }

    /*COCHES*/
    @Override
    public List<Vehiculo> getAllC() {
        List<Vehiculo> vehiculos = new ArrayList<>();

        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "SELECT * FROM vehiculo v INNER JOIN coche c ON v.matricula=c.matricula";
        try (Connection con = ds.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            Coche c;
            while (rs.next()) {
                c = new Coche(rs.getString("matricula"), rs.getFloat("precioHora"),
                        rs.getString("marca"), rs.getString("descripcion"),
                        rs.getString("color"),rs.getInt("bateria"),
                        rs.getDate("fechaAdq"),rs.getString("estado"),rs.getInt("idCarnet"),
                        rs.getTimestamp("changeDts"),rs.getString("changeBy"),
                        rs.getInt("numPlazas"),rs.getInt("numPuertas")
                );
                vehiculos.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehiculos;
    }

    @Override
    public Result<Vehiculo> deleteC(String matricula) {
        DataSource ds= MyDataSource.getMyOracleDataSource();
        String sql = "DELETE FROM vehiculo v INNER JOIN coche c ON v.matricula=c.matricula WHERE matricula like ?;";

        try (Connection con = ds.getConnection();
            CallableStatement statement = con.prepareCall(sql);){

            int pos =0;
            statement.setString(++pos,matricula);

            ResultSet rs= statement.getResultSet();
            if (rs.next()){
                Coche v = new Coche(matricula, rs.getFloat("precioHora"),
                        rs.getString("marca"), rs.getString("descripcion"),
                        rs.getString("color"),rs.getInt("bateria"),
                        rs.getDate("fechaAdq"),rs.getString("estado"),rs.getInt("idCarnet"),
                        rs.getTimestamp("changeDts"),rs.getString("changeBy"),
                        rs.getInt("numPlazas"),rs.getInt("numPuertas")
                );
                return new Result.Success<Vehiculo>(v);
            }
            return new Result.Error(400,"Ningun vehiculo eliminado");
        } catch (Exception e) {
            return new Result.Error(444,"Algun error capturado");
        }
    }

    @Override
    public Result<Vehiculo> updateC(Vehiculo v) {
        return null;
    }

    @Override
    public Result<Vehiculo> addC(Vehiculo v) {
        return null;
    }


    /*MOTO*/
    @Override
    public List<Vehiculo> getAllM() {
        List<Vehiculo> vehiculos = new ArrayList<>();

        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "SELECT * FROM vehiculo v INNER JOIN moto m ON v.matricula=m.matricula";
        try (Connection con = ds.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            Moto vehiculo;

            while (rs.next()) {
                vehiculo = new Moto(rs.getString("matricula"), rs.getFloat("precioHora"),
                        rs.getString("marca"), rs.getString("descripcion"),
                        rs.getString("color"),rs.getInt("bateria"),
                        rs.getDate("fechaAdq"),rs.getString("estado"),rs.getInt("idCarnet"),
                        rs.getTimestamp("changeDts"),rs.getString("changeBy"),
                        rs.getInt("velocidadMax"),rs.getInt("cilindrada")
                );
                vehiculos.add(vehiculo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehiculos;    }

    @Override
    public Result<Vehiculo> deleteM(String matricula) {
        DataSource ds= MyDataSource.getMyOracleDataSource();
        String sql = "DELETE FROM vehiculo v INNER JOIN moto c ON v.matricula=c.matricula WHERE matricula like ?;";

        try (Connection con = ds.getConnection();
             CallableStatement statement = con.prepareCall(sql);){

            int pos =0;
            statement.setString(++pos,matricula);

            ResultSet rs= statement.getResultSet();
            if (rs.next()){
                Moto v = new Moto(matricula, rs.getFloat("precioHora"),
                        rs.getString("marca"), rs.getString("descripcion"),
                        rs.getString("color"),rs.getInt("bateria"),
                        rs.getDate("fechaAdq"),rs.getString("estado"),rs.getInt("idCarnet"),
                        rs.getTimestamp("changeDts"),rs.getString("changeBy"),
                        rs.getInt("velocidadMax"),rs.getInt("cilindrada")
                );
                return new Result.Success<Vehiculo>(v);
            }
            return new Result.Error(400,"Ningun vehiculo eliminado");
        } catch (Exception e) {
            return new Result.Error(444,"Algun error capturado");
        }
    }

    @Override
    public Result<Vehiculo> updateM(Vehiculo v) {
        return null;
    }

    @Override
    public Result<Vehiculo> addM(Vehiculo v) {
        return null;
    }


    /*BICICLETA*/
    @Override
    public List<Vehiculo> getAllB() {
        List<Vehiculo> vehiculos = new ArrayList<>();

        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "SELECT * FROM vehiculo v INNER JOIN bicicleta b ON v.matriucla=b.matricula";
        try (Connection con = ds.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            Bicicleta vehiculo;

            while (rs.next()) {
                vehiculo = new Bicicleta(rs.getString("matricula"), rs.getFloat("precioHora"),
                        rs.getString("marca"), rs.getString("descripcion"),
                        rs.getString("color"),rs.getInt("bateria"),
                        rs.getDate("fechaAdq"),rs.getString("estado"),rs.getInt("idCarnet"),
                        rs.getTimestamp("changeDts"),rs.getString("changeBy"), rs.getString("tipo")
                );
                vehiculos.add(vehiculo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehiculos;    }

    @Override
    public Result<Vehiculo> deleteB(String matricula) {
        DataSource ds= MyDataSource.getMyOracleDataSource();
        String sql = "DELETE FROM vehiculo v INNER JOIN bicicleta c ON v.matricula=c.matricula WHERE matricula like ?;";

        try (Connection con = ds.getConnection();
             CallableStatement statement = con.prepareCall(sql);){

            int pos =0;
            statement.setString(++pos,matricula);

            ResultSet rs= statement.getResultSet();
            if (rs.next()){
                Bicicleta v = new Bicicleta(matricula, rs.getFloat("precioHora"),
                        rs.getString("marca"), rs.getString("descripcion"),
                        rs.getString("color"),rs.getInt("bateria"),
                        rs.getDate("fechaAdq"),rs.getString("estado"),rs.getInt("idCarnet"),
                        rs.getTimestamp("changeDts"),rs.getString("changeBy"), rs.getString("tipo")
                );
                return new Result.Success<Vehiculo>(v);
            }
            return new Result.Error(400,"Ningun vehiculo eliminado");
        } catch (Exception e) {
            return new Result.Error(444,"Algun error capturado");
        }    }

    @Override
    public Result<Vehiculo> updateB(Vehiculo v) {
        return null;
    }

    @Override
    public Result<Vehiculo> addB(Vehiculo v) {
        return null;
    }


    /*PATINETE*/
    @Override
    public List<Vehiculo> getAllP() {
        List<Vehiculo> vehiculos = new ArrayList<>();

        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "SELECT * FROM vehiculo v INNER JOIN patienete p ON v.matricula=p.matricula";
        try (Connection con = ds.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            Patinete vehiculo;

            while (rs.next()) {
                vehiculo = new Patinete(rs.getString("matricula"), rs.getFloat("precioHora"),
                        rs.getString("marca"), rs.getString("descripcion"),
                        rs.getString("color"),rs.getInt("bateria"),
                        rs.getDate("fechaAdq"),rs.getString("estado"),rs.getInt("idCarnet"),
                        rs.getTimestamp("changeDts"),rs.getString("changeBy"),
                        rs.getInt("numRuedas"), rs.getInt("tamanyo")
                );
                vehiculos.add(vehiculo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehiculos;
    }

    @Override
    public Result<Vehiculo> deleteP(String matricula) {
        DataSource ds= MyDataSource.getMyOracleDataSource();
        String sql = "DELETE FROM vehiculo v INNER JOIN patinete c ON v.matricula=c.matricula WHERE matricula like ?;";

        try (Connection con = ds.getConnection();
             CallableStatement statement = con.prepareCall(sql);){

            int pos =0;
            statement.setString(++pos,matricula);

            ResultSet rs= statement.getResultSet();
            if (rs.next()){
                Coche v = new Coche(matricula, rs.getFloat("precioHora"),
                        rs.getString("marca"), rs.getString("descripcion"),
                        rs.getString("color"),rs.getInt("bateria"),
                        rs.getDate("fechaAdq"),rs.getString("estado"),rs.getInt("idCarnet"),
                        rs.getTimestamp("changeDts"),rs.getString("changeBy"),
                        rs.getInt("numRuedas"), rs.getInt("tamanyo")
                );
                return new Result.Success<Vehiculo>(v);
            }
            return new Result.Error(400,"Ningun vehiculo eliminado");
        } catch (Exception e) {
            return new Result.Error(444,"Algun error capturado");
        }    }

    @Override
    public Result<Vehiculo> updateP(Vehiculo v) {
        return null;
    }

    @Override
    public Result<Vehiculo> addP(Vehiculo v) {
        return null;
    }

//    @Override
//    public List<Vehiculo> getAll() {
//        List<Vehiculo> vehiculos = new ArrayList<>();
//
//        DataSource ds = MyDataSource.getMyOracleDataSource();
//        String sql = "SELECT * FROM vehiculo";
//        try (Connection con = ds.getConnection();
//             Statement stmt = con.createStatement();
//             ResultSet rs = stmt.executeQuery(sql)) {
//
//            Vehiculo vehiculo;
//
//            while (rs.next()) {
//                vehiculo = new Vehiculo(rs.getString("matricula"), rs.getFloat("precioHora"),
//                        rs.getString("marca"), rs.getString("descripcion"),
//                        rs.getString("color"),rs.getInt("bateria"),
//                        rs.getDate("fechaAdq"),rs.getString("estado"),rs.getInt("idCarnet"),
//                        rs.getTimestamp("changeDts"),rs.getString("changeBy"));
//                vehiculos.add(vehiculo);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return vehiculos;
//    }
//
//    @Override
//    public Result<Vehiculo> delete(String matricula) {
//        DataSource ds= MyDataSource.getMyOracleDataSource();
//        String sql = "DELETE FROM vehiculo WHERE matricucula like ?;";//TODO tindria que fer un delete per cada tipo per a llevarlo de les atres bases?
//
//        try (Connection con = ds.getConnection();
//            CallableStatement statement = con.prepareCall(sql);){
//
//            int pos =0;
//            statement.setString(++pos,matricula);
//
//            ResultSet rs= statement.getResultSet();
//            if (rs.next()){
//                Vehiculo v = new Vehiculo(matricula, rs.getFloat("precioHora"),
//                        rs.getString("marca"), rs.getString("descripcion"),
//                        rs.getString("color"),rs.getInt("bateria"),
//                        rs.getDate("fechaAdq"),rs.getString("estado"),rs.getInt("idCarnet"),
//                        rs.getTimestamp("changeDts"),rs.getString("changeBy"));
//                return new Result.Success<Vehiculo>(v);
//            }
//            return new Result.Error(400,"Ningun vehiculo eliminado");
//        } catch (Exception e) {
//            return new Result.Error(444,"Algun error capturado");
//        }
//    }
//
//    @Override
//    public Result<Vehiculo> update(Vehiculo v) {
//        DataSource ds= MyDataSource.getMyOracleDataSource();
//        String sql ="UPDATE vehiculo set precioHora=?, marca=?, descripcion=?, color=?, bateria=?, fechaAdq=?, estado=?, idcCarnet=?,changeDts=current_timestamp,changeBy=server" +
//                "where matricula like ?";
//        try (Connection con = ds.getConnection();
//            CallableStatement statement = con.prepareCall(sql);){
//
//            int pos = 0;
//            statement.setString(++pos, v.getMatricula());
//            statement.setFloat(++pos, v.getPrecioHora());
//            statement.setString(++pos, v.getMarca());
//            statement.setString(++pos, v.getDescripcion());
//            statement.setString(++pos, v.getColor());
//            statement.setInt(++pos, v.getBateria());
//            statement.setDate(++pos, v.getFechaAdq());
//            statement.setString(++pos, v.getEstado());
//            statement.setFloat(++pos, v.getIdCarnet());
//
//            int cant = statement.executeUpdate();
//            if (cant == 1)
//                return new Result.Success<Vehiculo>(v);
//            else
//                return new Result.Error(401,"Ninguna vehiculo actualizada");
//
//        } catch (Exception e) {
//            return new Result.Error(444,"Algun error capturado");
//        }
//    }
//
//    @Override
//    public Result<Vehiculo> add(Vehiculo v) {
//        DataSource ds= MyDataSource.getMyOracleDataSource();
//        String sql ="INSERT INTO vehiculo values (?,?,?,?,?, ?,?,?,?,?,?)";
//        try (Connection con = ds.getConnection();
//             CallableStatement statement = con.prepareCall(sql);){
//
//            int pos = 0;
//            statement.setString(++pos, v.getMatricula());
//            statement.setFloat(++pos, v.getPrecioHora());
//            statement.setString(++pos, v.getMarca());
//            statement.setString(++pos, v.getDescripcion());
//            statement.setString(++pos, v.getColor());
//            statement.setInt(++pos, v.getBateria());
//            statement.setDate(++pos, v.getFechaAdq());
//            statement.setString(++pos, v.getEstado());
//            statement.setFloat(++pos, v.getIdCarnet());
//
//            int cant = statement.executeUpdate();
//            if (cant == 1)
//                return new Result.Success<Vehiculo>(v);
//            else
//                return new Result.Error(401,"Ninguna vehiculo añadida");
//
//        } catch (Exception e) {
//            return new Result.Error(444,"Algun error capturado");
//        }
//    }
//
//    @Override
//    public Result<Vehiculo> get(String matricula) {
//        return null;
//    }


}
