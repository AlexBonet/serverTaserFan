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
public class ImpVehiculoService implements IVehiculoService {

    /*COCHES*/
    @Override
    public List<Coche> getAllC() {
        List<Coche> vehiculos = new ArrayList<>();

        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "SELECT * FROM vehiculo v INNER JOIN coche c ON v.matricula=c.matricula";
        try (Connection con = ds.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            Coche c;
            while (rs.next()) {
                c = new Coche(rs.getString("matricula"), rs.getFloat("precioHora"),
                        rs.getString("marca"), rs.getString("descripcion"),
                        rs.getString("color"), rs.getInt("bateria"),
                        rs.getDate("fechaAdq"), rs.getString("estado"), rs.getInt("idCarnet"),
                        rs.getTimestamp("changeDts"), rs.getString("changeBy"),
                        rs.getInt("numPlazas"), rs.getInt("numPuertas")
                );
                vehiculos.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehiculos;
    }

    @Override
    public Result<Coche> deleteC(String matricula) {
        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "DELETE FROM vehiculo v INNER JOIN coche c ON v.matricula=c.matricula WHERE matricula like ?;";

        try (Connection con = ds.getConnection();
             CallableStatement statement = con.prepareCall(sql)) {

            int pos = 0;
            statement.setString(++pos, matricula);

            ResultSet rs = statement.getResultSet();
            if (rs.next()) {
                Coche v = new Coche(matricula, rs.getFloat("precioHora"),
                        rs.getString("marca"), rs.getString("descripcion"),
                        rs.getString("color"), rs.getInt("bateria"),
                        rs.getDate("fechaAdq"), rs.getString("estado"), rs.getInt("idCarnet"),
                        rs.getTimestamp("changeDts"), rs.getString("changeBy"),
                        rs.getInt("numPlazas"), rs.getInt("numPuertas")
                );
                return new Result.Success<Vehiculo>(v);
            }
            return new Result.Error(400, "Ningun vehiculo eliminado");
        } catch (Exception e) {
            return new Result.Error(444, "Algun error capturado");
        }
    }

    @Override
    public Result<Coche> addC(Coche v) {
        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "INSERT INTO vehiculo(matricula,preciohora,marca,descripcion,color,bateria,fechaadq,estado,idcarnet)" +
                " values (" + v.getMatricula() + "," + v.getPrecioHora() + "," + v.getMarca() +
                "," + v.getDescripcion() + "," + v.getColor() + "," + v.getBateria() + "," + v.getFechaAdq()
                + "," + v.getEstado() + "," + v.getIdCarnet() + ");\n" +
                "INSERT INTO coche values(" + v.getMatricula() + "," + v.getNumPlazas() + "," + v.getNumPuertas() + ")";
        try (Connection con = ds.getConnection();
             CallableStatement statement = con.prepareCall(sql)) {

            int pos = 0;
            statement.setString(++pos, v.getMatricula());
            statement.setFloat(++pos, v.getPrecioHora());
            statement.setString(++pos, v.getMarca());
            statement.setString(++pos, v.getDescripcion());
            statement.setString(++pos, v.getColor());
            statement.setInt(++pos, v.getBateria());
            statement.setDate(++pos, v.getFechaAdq());
            statement.setString(++pos, v.getEstado());
            statement.setFloat(++pos, v.getIdCarnet());
            statement.setInt(++pos, v.getNumPlazas());
            statement.setInt(++pos, v.getNumPuertas());

            int cant = statement.executeUpdate();
            if (cant == 1)
                return new Result.Success<Vehiculo>(v);
            else
                return new Result.Error(401, "Ninguna vehiculo añadida");

        } catch (Exception e) {
            return new Result.Error(444, "Algun error capturado");
        }
    }

    @Override
    public Result<Coche> updateC(Coche v) {
        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "UPDATE vehiculo set precioHora=" + v.getPrecioHora() + ", marca=" + v.getMarca() + ", descripcion=" + v.getDescripcion() +
                " ,color=" + v.getColor() + ", bateria=" + v.getBateria() + " fechaAdq=" + v.getFechaAdq() + ", estado=" + v.getEstado() + ", " +
                "idcCarnet=" + v.getIdCarnet() + ",changeDts=current_timestamp,changeBy=server" +
                "where matricula like" + v.getMatricula() + ";" +
                "UPDATE coche set numplazas=" + v.getNumPlazas() + ", numPuertas=" + v.getNumPuertas() + "where matricula like" + v.getMatricula() + ";";
        try (Connection con = ds.getConnection();
             CallableStatement statement = con.prepareCall(sql)) {

            int pos = 0;
            statement.setString(++pos, v.getMatricula());
            statement.setFloat(++pos, v.getPrecioHora());
            statement.setString(++pos, v.getMarca());
            statement.setString(++pos, v.getDescripcion());
            statement.setString(++pos, v.getColor());
            statement.setInt(++pos, v.getBateria());
            statement.setDate(++pos, v.getFechaAdq());
            statement.setString(++pos, v.getEstado());
            statement.setFloat(++pos, v.getIdCarnet());
            statement.setInt(++pos, v.getNumPlazas());
            statement.setInt(++pos, v.getNumPuertas());

            int cant = statement.executeUpdate();
            if (cant == 1)
                return new Result.Success<Vehiculo>(v);
            else
                return new Result.Error(401, "Ninguna vehiculo actualizada");

        } catch (Exception e) {
            return new Result.Error(444, "Algun error capturado");
        }
    }


    /*MOTO*/
    @Override
    public List<Moto> getAllM() {
        List<Moto> vehiculos = new ArrayList<>();

        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "SELECT * FROM vehiculo v INNER JOIN moto m ON v.matricula=m.matricula";
        try (Connection con = ds.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            Moto vehiculo;

            while (rs.next()) {
                vehiculo = new Moto(rs.getString("matricula"), rs.getFloat("precioHora"),
                        rs.getString("marca"), rs.getString("descripcion"),
                        rs.getString("color"), rs.getInt("bateria"),
                        rs.getDate("fechaAdq"), rs.getString("estado"), rs.getInt("idCarnet"),
                        rs.getTimestamp("changeDts"), rs.getString("changeBy"),
                        rs.getInt("velocidadMax"), rs.getInt("cilindrada")
                );
                vehiculos.add(vehiculo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehiculos;
    }

    @Override
    public Result<Moto> deleteM(String matricula) {
        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "DELETE FROM vehiculo v INNER JOIN moto c ON v.matricula=c.matricula WHERE matricula like ?;";

        try (Connection con = ds.getConnection();
             CallableStatement statement = con.prepareCall(sql)) {

            int pos = 0;
            statement.setString(++pos, matricula);

            ResultSet rs = statement.getResultSet();
            if (rs.next()) {
                Moto v = new Moto(matricula, rs.getFloat("precioHora"),
                        rs.getString("marca"), rs.getString("descripcion"),
                        rs.getString("color"), rs.getInt("bateria"),
                        rs.getDate("fechaAdq"), rs.getString("estado"), rs.getInt("idCarnet"),
                        rs.getTimestamp("changeDts"), rs.getString("changeBy"),
                        rs.getInt("velocidadMax"), rs.getInt("cilindrada")
                );
                return new Result.Success<Vehiculo>(v);
            }
            return new Result.Error(400, "Ningun vehiculo eliminado");
        } catch (Exception e) {
            return new Result.Error(444, "Algun error capturado");
        }
    }

    @Override
    public Result<Moto> addM(Moto v) {
        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "INSERT INTO vehiculo(matricula,preciohora,marca,descripcion,color,bateria,fechaadq,estado,idcarnet)" +
                " values (" + v.getMatricula() + "," + v.getPrecioHora() + "," + v.getMarca() +
                "," + v.getDescripcion() + "," + v.getColor() + "," + v.getBateria() + "," + v.getFechaAdq()
                + "," + v.getEstado() + "," + v.getIdCarnet() + ");\n" +
                "INSERT INTO moto values(" + v.getMatricula() + "," + v.getVelocidadMax() + "," + v.getCilindrada() + ")";
        try (Connection con = ds.getConnection();
             CallableStatement statement = con.prepareCall(sql)) {

            int pos = 0;
            statement.setString(++pos, v.getMatricula());
            statement.setFloat(++pos, v.getPrecioHora());
            statement.setString(++pos, v.getMarca());
            statement.setString(++pos, v.getDescripcion());
            statement.setString(++pos, v.getColor());
            statement.setInt(++pos, v.getBateria());
            statement.setDate(++pos, v.getFechaAdq());
            statement.setString(++pos, v.getEstado());
            statement.setFloat(++pos, v.getIdCarnet());
            statement.setInt(++pos, v.getVelocidadMax());
            statement.setInt(++pos, v.getCilindrada());

            int cant = statement.executeUpdate();
            if (cant == 1)
                return new Result.Success<Vehiculo>(v);
            else
                return new Result.Error(401, "Ninguna vehiculo añadida");

        } catch (Exception e) {
            return new Result.Error(444, "Algun error capturado");
        }
    }

    @Override
    public Result<Moto> updateM(Moto v) {
        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "UPDATE vehiculo set precioHora=" + v.getPrecioHora() + ", marca=" + v.getMarca() + ", descripcion=" + v.getDescripcion() +
                " ,color=" + v.getColor() + ", bateria=" + v.getBateria() + " fechaAdq=" + v.getFechaAdq() + ", estado=" + v.getEstado() + ", " +
                "idcCarnet=" + v.getIdCarnet() + ",changeDts=current_timestamp,changeBy=server" +
                "where matricula like" + v.getMatricula() + ";" +
                "UPDATE moto set numplazas=" + v.getVelocidadMax() + ", numPuertas=" + v.getCilindrada() + "where matricula like" + v.getMatricula() + ";";
        try (Connection con = ds.getConnection();
             CallableStatement statement = con.prepareCall(sql)) {

            int pos = 0;
            statement.setString(++pos, v.getMatricula());
            statement.setFloat(++pos, v.getPrecioHora());
            statement.setString(++pos, v.getMarca());
            statement.setString(++pos, v.getDescripcion());
            statement.setString(++pos, v.getColor());
            statement.setInt(++pos, v.getBateria());
            statement.setDate(++pos, v.getFechaAdq());
            statement.setString(++pos, v.getEstado());
            statement.setFloat(++pos, v.getIdCarnet());
            statement.setInt(++pos, v.getVelocidadMax());
            statement.setInt(++pos, v.getCilindrada());

            int cant = statement.executeUpdate();
            if (cant == 1)
                return new Result.Success<Vehiculo>(v);
            else
                return new Result.Error(401, "Ninguna vehiculo actualizada");

        } catch (Exception e) {
            return new Result.Error(444, "Algun error capturado");
        }
    }

    /*BICICLETA*/
    @Override
    public List<Bicicleta> getAllB() {
        List<Bicicleta> vehiculos = new ArrayList<>();

        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "SELECT * FROM vehiculo v INNER JOIN bicicleta b ON v.matriucla=b.matricula";
        try (Connection con = ds.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            Bicicleta vehiculo;

            while (rs.next()) {
                vehiculo = new Bicicleta(rs.getString("matricula"), rs.getFloat("precioHora"),
                        rs.getString("marca"), rs.getString("descripcion"),
                        rs.getString("color"), rs.getInt("bateria"),
                        rs.getDate("fechaAdq"), rs.getString("estado"), rs.getInt("idCarnet"),
                        rs.getTimestamp("changeDts"), rs.getString("changeBy"), rs.getString("tipo")
                );
                vehiculos.add(vehiculo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehiculos;
    }

    @Override
    public Result<Bicicleta> deleteB(String matricula) {
        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "DELETE FROM vehiculo v INNER JOIN bicicleta c ON v.matricula=c.matricula WHERE matricula like ?;";

        try (Connection con = ds.getConnection();
             CallableStatement statement = con.prepareCall(sql)) {

            int pos = 0;
            statement.setString(++pos, matricula);

            ResultSet rs = statement.getResultSet();
            if (rs.next()) {
                Bicicleta v = new Bicicleta(matricula, rs.getFloat("precioHora"),
                        rs.getString("marca"), rs.getString("descripcion"),
                        rs.getString("color"), rs.getInt("bateria"),
                        rs.getDate("fechaAdq"), rs.getString("estado"), rs.getInt("idCarnet"),
                        rs.getTimestamp("changeDts"), rs.getString("changeBy"), rs.getString("tipo")
                );
                return new Result.Success<Vehiculo>(v);
            }
            return new Result.Error(400, "Ningun vehiculo eliminado");
        } catch (Exception e) {
            return new Result.Error(444, "Algun error capturado");
        }
    }

    @Override
    public Result<Bicicleta> addB(Bicicleta v) {
        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "INSERT INTO vehiculo(matricula,preciohora,marca,descripcion,color,bateria,fechaadq,estado,idcarnet)" +
                " values (" + v.getMatricula() + "," + v.getPrecioHora() + "," + v.getMarca() +
                "," + v.getDescripcion() + "," + v.getColor() + "," + v.getBateria() + "," + v.getFechaAdq()
                + "," + v.getEstado() + "," + v.getIdCarnet() + ");\n" +
                "INSERT INTO bicicleta values(" + v.getMatricula() + "," + v.getTipo() + ")";
        try (Connection con = ds.getConnection();
             CallableStatement statement = con.prepareCall(sql)) {

            int pos = 0;
            statement.setString(++pos, v.getMatricula());
            statement.setFloat(++pos, v.getPrecioHora());
            statement.setString(++pos, v.getMarca());
            statement.setString(++pos, v.getDescripcion());
            statement.setString(++pos, v.getColor());
            statement.setInt(++pos, v.getBateria());
            statement.setDate(++pos, v.getFechaAdq());
            statement.setString(++pos, v.getEstado());
            statement.setFloat(++pos, v.getIdCarnet());
            statement.setString(++pos, v.getTipo());

            int cant = statement.executeUpdate();
            if (cant == 1)
                return new Result.Success<Vehiculo>(v);
            else
                return new Result.Error(401, "Ninguna vehiculo añadida");

        } catch (Exception e) {
            return new Result.Error(444, "Algun error capturado");
        }
    }

    @Override
    public Result<Bicicleta> updateB(Bicicleta v) {
        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "UPDATE vehiculo set precioHora=" + v.getPrecioHora() + ", marca=" + v.getMarca() + ", descripcion=" + v.getDescripcion() +
                " ,color=" + v.getColor() + ", bateria=" + v.getBateria() + " fechaAdq=" + v.getFechaAdq() + ", estado=" + v.getEstado() + ", " +
                "idcCarnet=" + v.getIdCarnet() + ",changeDts=current_timestamp,changeBy=server" +
                "where matricula like" + v.getMatricula() + ";" +
                "UPDATE bicicleta set numplazas=" + v.getTipo() + "where matricula like" + v.getMatricula() + ";";
        try (Connection con = ds.getConnection();
             CallableStatement statement = con.prepareCall(sql)) {

            int pos = 0;
            statement.setString(++pos, v.getMatricula());
            statement.setFloat(++pos, v.getPrecioHora());
            statement.setString(++pos, v.getMarca());
            statement.setString(++pos, v.getDescripcion());
            statement.setString(++pos, v.getColor());
            statement.setInt(++pos, v.getBateria());
            statement.setDate(++pos, v.getFechaAdq());
            statement.setString(++pos, v.getEstado());
            statement.setFloat(++pos, v.getIdCarnet());
            statement.setString(++pos, v.getTipo());


            int cant = statement.executeUpdate();
            if (cant == 1)
                return new Result.Success<Vehiculo>(v);
            else
                return new Result.Error(401, "Ninguna vehiculo actualizada");

        } catch (Exception e) {
            return new Result.Error(444, "Algun error capturado");
        }
    }

    /*PATINETE*/
    @Override
    public List<Patinete> getAllP() {
        List<Patinete> vehiculos = new ArrayList<>();

        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "SELECT * FROM vehiculo v INNER JOIN patienete p ON v.matricula=p.matricula";
        try (Connection con = ds.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            Patinete vehiculo;

            while (rs.next()) {
                vehiculo = new Patinete(rs.getString("matricula"), rs.getFloat("precioHora"),
                        rs.getString("marca"), rs.getString("descripcion"),
                        rs.getString("color"), rs.getInt("bateria"),
                        rs.getDate("fechaAdq"), rs.getString("estado"), rs.getInt("idCarnet"),
                        rs.getTimestamp("changeDts"), rs.getString("changeBy"),
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
    public Result<Patinete> deleteP(String matricula) {
        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "DELETE FROM vehiculo v INNER JOIN patinete c ON v.matricula=c.matricula WHERE matricula like ?;";

        try (Connection con = ds.getConnection();
             CallableStatement statement = con.prepareCall(sql)) {

            int pos = 0;
            statement.setString(++pos, matricula);

            ResultSet rs = statement.getResultSet();
            if (rs.next()) {
                Coche v = new Coche(matricula, rs.getFloat("precioHora"),
                        rs.getString("marca"), rs.getString("descripcion"),
                        rs.getString("color"), rs.getInt("bateria"),
                        rs.getDate("fechaAdq"), rs.getString("estado"), rs.getInt("idCarnet"),
                        rs.getTimestamp("changeDts"), rs.getString("changeBy"),
                        rs.getInt("numRuedas"), rs.getInt("tamanyo")
                );
                return new Result.Success<Vehiculo>(v);
            }
            return new Result.Error(400, "Ningun vehiculo eliminado");
        } catch (Exception e) {
            return new Result.Error(444, "Algun error capturado");
        }
    }

    @Override
    public Result<Patinete> addP(Patinete v) {
        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "INSERT INTO vehiculo(matricula,preciohora,marca,descripcion,color,bateria,fechaadq,estado,idcarnet)" +
                " values (" + v.getMatricula() + "," + v.getPrecioHora() + "," + v.getMarca() +
                "," + v.getDescripcion() + "," + v.getColor() + "," + v.getBateria() + "," + v.getFechaAdq()
                + "," + v.getEstado() + "," + v.getIdCarnet() + ");\n" +
                "INSERT INTO patinete values(" + v.getMatricula() + "," + v.getNumRuedas() + "," + v.getTamanyo() + ")";
        try (Connection con = ds.getConnection();
             CallableStatement statement = con.prepareCall(sql)) {

            int pos = 0;
            statement.setString(++pos, v.getMatricula());
            statement.setFloat(++pos, v.getPrecioHora());
            statement.setString(++pos, v.getMarca());
            statement.setString(++pos, v.getDescripcion());
            statement.setString(++pos, v.getColor());
            statement.setInt(++pos, v.getBateria());
            statement.setDate(++pos, v.getFechaAdq());
            statement.setString(++pos, v.getEstado());
            statement.setFloat(++pos, v.getIdCarnet());
            statement.setInt(++pos, v.getNumRuedas());
            statement.setInt(++pos, v.getTamanyo());

            int cant = statement.executeUpdate();
            if (cant == 1)
                return new Result.Success<Vehiculo>(v);
            else
                return new Result.Error(401, "Ninguna vehiculo añadida");

        } catch (Exception e) {
            return new Result.Error(444, "Algun error capturado");
        }
    }

    @Override
    public Result<Patinete> updateP(Patinete v) {
        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "UPDATE vehiculo set precioHora=" + v.getPrecioHora() + ", marca=" + v.getMarca() + ", descripcion=" + v.getDescripcion() +
                " ,color=" + v.getColor() + ", bateria=" + v.getBateria() + " fechaAdq=" + v.getFechaAdq() + ", estado=" + v.getEstado() + ", " +
                "idcCarnet=" + v.getIdCarnet() + ",changeDts=current_timestamp,changeBy=server" +
                "where matricula like" + v.getMatricula() + ";" +
                "UPDATE patinete set numplazas=" + v.getNumRuedas() + ", numPuertas=" + v.getTamanyo() + "where matricula like" + v.getMatricula() + ";";
        try (Connection con = ds.getConnection();
             CallableStatement statement = con.prepareCall(sql)) {

            int pos = 0;
            statement.setString(++pos, v.getMatricula());
            statement.setFloat(++pos, v.getPrecioHora());
            statement.setString(++pos, v.getMarca());
            statement.setString(++pos, v.getDescripcion());
            statement.setString(++pos, v.getColor());
            statement.setInt(++pos, v.getBateria());
            statement.setDate(++pos, v.getFechaAdq());
            statement.setString(++pos, v.getEstado());
            statement.setFloat(++pos, v.getIdCarnet());
            statement.setInt(++pos, v.getNumRuedas());
            statement.setInt(++pos, v.getTamanyo());

            int cant = statement.executeUpdate();
            if (cant == 1)
                return new Result.Success<Vehiculo>(v);
            else
                return new Result.Error(401, "Ninguna vehiculo actualizada");

        } catch (Exception e) {
            return new Result.Error(444, "Algun error capturado");
        }
    }

    //GETALL
    /*
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
//*/
    //DELETE
    /*
//    @Override
//    public Result<Vehiculo> delete(String matricula) {
//        DataSource ds= MyDataSource.getMyOracleDataSource();
//        String sql = "DELETE FROM vehiculo WHERE matricucula like ?;";//
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
//*/
    //UPDATE
    /*
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
//*/
    //ADD
    /*
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
//    }*/


}
