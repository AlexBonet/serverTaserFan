package es.ieslavereda.server.model;

import es.ieslavereda.model.MyDataSource;
import es.ieslavereda.model.Result;
import es.ieslavereda.model.clases.vehiculos.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * funciona: getALL, getC,getM,getP, addC, addM,
 * TODO getB arreglar
 * todo mirar que funcione tots el insertarvehiculos
 * TODO ADD(de moment coche):{"message":"Algun error capturado: ORA-20222: FATAL ERROR CAPTURADO. ORA-01403: No se ha encontrado ningún
 * dato\nORA-06512: en \"C##1DAMBONET.GESTIONVEHICULOS\", línea 33\nORA-06512: en línea 1\n","code":444}
 * TODO UPDATE (de moment coche): indice columna no valido
 * TODO hasta que no añada no borre
 */
public class ImpVehiculoService implements IVehiculoService {

    /**OBTENER TODOS LOS VEHICULOS*/
    @Override
    public List<Vehiculo> getAll() {
        List<Vehiculo> vehiculos = new ArrayList<>();

        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "SELECT * FROM vehiculo v ";

        try (Connection con = ds.getConnection();
             CallableStatement stmt = con.prepareCall(sql);
             ResultSet rs = stmt.executeQuery()) {

            Vehiculo v;
            while (rs.next()) {
                v = new Vehiculo(rs.getString("matricula"), rs.getFloat("precioHora"),
                        rs.getString("marca"), rs.getString("descripcion"),
                        rs.getString("color"), rs.getInt("bateria"),
                        rs.getDate("fechaAdq"), rs.getString("estado"), rs.getString("idCarnet")
                );
                vehiculos.add(v);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehiculos;
    }

    /**COCHES*/
    @Override
    public Result<Coche> getC(String matricula) {
        Coche c= null;
        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "{call GESTIONVEHICULOS.consultarCoche(?,?,?, ?,?,? ,?,?,? ,?,?)}";

        try (Connection con = ds.getConnection();
             CallableStatement stmt = con.prepareCall(sql)) {

            stmt.setString(1,matricula);
            stmt.registerOutParameter(2, Types.FLOAT);
            stmt.registerOutParameter(3,Types.VARCHAR);
            stmt.registerOutParameter(4, Types.VARCHAR);
            stmt.registerOutParameter(5, Types.VARCHAR);
            stmt.registerOutParameter(6, Types.INTEGER);
            stmt.registerOutParameter(7, Types.DATE);
            stmt.registerOutParameter(8, Types.VARCHAR);
            stmt.registerOutParameter(9, Types.VARCHAR);
            stmt.registerOutParameter(10, Types.INTEGER);
            stmt.registerOutParameter(11, Types.INTEGER);

            stmt.execute();

            String v_matricula = matricula;
            float v_precioHora = stmt.getFloat(2);
            String v_marca = stmt.getString(3);
            String v_descripcion = stmt.getString(4);
            String v_color = stmt.getString(5);
            int v_bateria = stmt.getInt(6);
            Date date = stmt.getDate(7);
            String v_estado = stmt.getString(8);
            String v_idCarnet = stmt.getString(9);
            int v_numPlazas = stmt.getInt(10);
            int v_numPuertas = stmt.getInt(11);

            c = new Coche(v_matricula, v_precioHora, v_marca, v_descripcion, v_color, v_bateria, date, v_estado,v_idCarnet, v_numPlazas, v_numPuertas);

        } catch (SQLException e) {
            e.printStackTrace();
            return new Result.Error(402, "ALGUN ERROR CAPTURADO");
        }
        if(c != null){
            return new Result.Success<Coche>(c);
        }else{
            return new Result.Error(404, "COCHE NO ENCONTRADO");
        }
    }

    @Override
    public Result<Coche> deleteC(String matricula) {
        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "DELETE FROM vehiculo v INNER JOIN coche c ON v.matricula=c.matricula WHERE matricula like ?;";

        try (Connection con = ds.getConnection();
             CallableStatement statement = con.prepareCall(sql)) {


            statement.setString(1, matricula);

            if (statement.execute())
                return new Result.Success<>(200);
            else
                return new Result.Error(401, "Ninguna vehiculo añadida");

        } catch (Exception e) {
            return new Result.Error(444, "Algun error capturado");
        }
    }

    @Override
    public Result<Coche> addC(Coche v) {
        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "{call GESTIONVEHICULOS.insertarCoche(?,?,? ,?,?,? ,?,?,? ,?,?)}";

        try (Connection con = ds.getConnection();
             CallableStatement statement = con.prepareCall(sql)) {

            statement.setString(1, v.getMatricula());
            statement.setFloat(2, v.getPrecioHora());
            statement.setString(3, v.getMarca());
            statement.setString(4, v.getDescripcion());
            statement.setString(5, v.getColor());
            statement.setInt(6, v.getBateria());
            statement.setDate(7, v.getFechaAdq());
            statement.setString(8, v.getEstado());
            statement.setString(9, v.getIdCarnet());
            statement.setInt(10, v.getNumPlazas());
            statement.setInt(11, v.getNumPuertas());

            if (!statement.execute())
                return new Result.Success<Coche>(v);
            else
                return new Result.Error(401, "Ninguna vehiculo añadida");

        } catch (Exception e) {
            return new Result.Error(444, "Algun error capturado: " + e.getMessage());
        }
    }

    @Override
    public Result<Coche> updateC(Coche v) {
        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "{call GESTIONVEHICULOS.actualizarCoche(?,?,?,?,?,?,?,?,?,?,?)}";

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
            statement.setString(++pos, v.getIdCarnet());
            statement.setInt(++pos, v.getNumPlazas());
            statement.setInt(++pos, v.getNumPuertas());

            if (!statement.execute())
                return new Result.Success<Coche>(v);
            else
                return new Result.Error(401, "Ninguna vehiculo actualizada");

        } catch (Exception e) {
            return new Result.Error(444, "Algun error capturado: " + e.getMessage());
        }
    }


    /**MOTO*/
    @Override
    public Result<Moto> getM(String matricula) {
        Moto m = null;
        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "{call GESTIONVEHICULOS.consultarMoto(?,?,?, ?,?,? ,?,?,? ,?,?)}";

        try (Connection con = ds.getConnection();
             CallableStatement stmt = con.prepareCall(sql)) {

            stmt.setString(1,matricula);
            stmt.registerOutParameter(2, Types.FLOAT);
            stmt.registerOutParameter(3,Types.VARCHAR);
            stmt.registerOutParameter(4, Types.VARCHAR);
            stmt.registerOutParameter(5, Types.VARCHAR);
            stmt.registerOutParameter(6, Types.INTEGER);
            stmt.registerOutParameter(7, Types.DATE);
            stmt.registerOutParameter(8, Types.VARCHAR);
            stmt.registerOutParameter(9, Types.VARCHAR);
            stmt.registerOutParameter(10, Types.INTEGER);
            stmt.registerOutParameter(11, Types.INTEGER);

            stmt.execute();

            String v_matricula = matricula;
            float v_precioHora = stmt.getFloat(2);
            String v_marca = stmt.getString(3);
            String v_descripcion = stmt.getString(4);
            String v_color = stmt.getString(5);
            int v_bateria = stmt.getInt(6);
            Date date = stmt.getDate(7);
            String v_estado = stmt.getString(8);
            String v_idCarnet = stmt.getString(9);
            int v_cilindrada = stmt.getInt(10);
            int v_velMax = stmt.getInt(11);

            m = new Moto(v_matricula, v_precioHora, v_marca, v_descripcion, v_color, v_bateria, date, v_estado,v_idCarnet, v_cilindrada, v_velMax);

        } catch (SQLException e) {
            e.printStackTrace();
            return new Result.Error(402, "ALGUN ERROR CAPTURADO");
        }
        if(m != null){
            return new Result.Success<Moto>(m);
        }else{
            return new Result.Error(404, "MOTO NO ENCONTRADA");
        }
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
                        rs.getDate("fechaAdq"), rs.getString("estado"), rs.getString("idCarnet"),
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
        String sql = "{call GESTIONVEHICULOS.insertarMoto(?,?,? ,?,?,? ,?,?,? ,?,?)}";

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
            statement.setString(++pos, v.getIdCarnet());
            statement.setInt(++pos, v.getVelocidadMax());
            statement.setInt(++pos, v.getCilindrada());

            if (!statement.execute())
                return new Result.Success<Moto>(v);
            else
                return new Result.Error(401, "Ninguna vehiculo añadida");

        } catch (Exception e) {
            return new Result.Error(444, "Algun error capturado: " + e.getMessage());
        }
    }

    @Override
    public Result<Moto> updateM(Moto v) {
        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "{call GESTIONVEHICULOS.actualizarMoto(?,?,?,?,?,?,?,?,?,?,?)}";

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
            statement.setString(++pos, v.getIdCarnet());
            statement.setInt(++pos, v.getVelocidadMax());
            statement.setInt(++pos, v.getCilindrada());

            if (!statement.execute())
                return new Result.Success<Moto>(v);
            else
                return new Result.Error(401, "Ninguna vehiculo actualizada");

        } catch (Exception e) {
            return new Result.Error(444, "Algun error capturado" + e.getMessage());
        }
    }


    /**BICICLETA*/
    @Override
    public Result<Bicicleta> getB(String matricula) {
        Bicicleta b = null;
        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "{call GESTIONVEHICULOS.consultarBici(?,?,?, ?,?,? ,?,?,? ,?)}";

        try (Connection con = ds.getConnection();
            CallableStatement stmt = con.prepareCall(sql)) {

            stmt.setString(1,matricula);
            stmt.registerOutParameter(2, Types.FLOAT);
            stmt.registerOutParameter(3,Types.VARCHAR);
            stmt.registerOutParameter(4, Types.VARCHAR);
            stmt.registerOutParameter(5, Types.VARCHAR);
            stmt.registerOutParameter(6, Types.FLOAT);
            stmt.registerOutParameter(7, Types.DATE);
            stmt.registerOutParameter(8, Types.VARCHAR);
            stmt.registerOutParameter(9, Types.VARCHAR);
            stmt.registerOutParameter(10, Types.VARCHAR);

            stmt.execute();

            String v_matricula = matricula;
            float v_precioHora = stmt.getFloat(2);
            String v_marca = stmt.getString(3);
            String v_descripcion = stmt.getString(4);
            String v_color = stmt.getString(5);
            int v_bateria = stmt.getInt(6);
            Date date = stmt.getDate(7);
            String v_estado = stmt.getString(8);
            String v_idCarnet = stmt.getString(9);
            String tipo = stmt.getString(10);

            b = new Bicicleta(v_matricula, v_precioHora, v_marca, v_descripcion, v_color, v_bateria, date, v_estado,v_idCarnet, tipo);

        } catch (SQLException e) {
            e.printStackTrace();
            return new Result.Error(402, "ALGUN ERROR CAPTURADO");
        }
        if(b != null){
            return new Result.Success<Bicicleta>(b);
        }else{
            return new Result.Error(404, "BICI NO ENCONTRADA");
        }
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
                        rs.getDate("fechaAdq"), rs.getString("estado"), rs.getString("idCarnet"),
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
        String sql = "{call GESTIONVEHICULOS.insertarBici(?,?,? ,?,?,? ,?,?,? ,?)}";

        try (Connection con = ds.getConnection();
             CallableStatement statement = con.prepareCall(sql)) {//lo que pides con callableStatment es que haga lo de abajo y no arriba??

            int pos = 0;
            statement.setString(++pos, v.getMatricula());
            statement.setFloat(++pos, v.getPrecioHora());
            statement.setString(++pos, v.getMarca());
            statement.setString(++pos, v.getDescripcion());
            statement.setString(++pos, v.getColor());
            statement.setInt(++pos, v.getBateria());
            statement.setDate(++pos, v.getFechaAdq());
            statement.setString(++pos, v.getEstado());
            statement.setString(++pos, v.getIdCarnet());
            statement.setString(++pos, v.getTipo());

            if (!statement.execute())
                return new Result.Success<Bicicleta>(v);
            else
                return new Result.Error(401, "Ninguna vehiculo añadida");

        } catch (Exception e) {
            return new Result.Error(444, "Algun error capturado: " + e.getMessage());
        }
    }

    @Override
    public Result<Bicicleta> updateB(Bicicleta v) {
        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "{call GESTIONVEHICULOS.actualizarBici(?,?,?,?,?,?,?,?,?,?,?)}";

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
            statement.setString(++pos, v.getIdCarnet());
            statement.setString(++pos, v.getTipo());


            if (!statement.execute())
                return new Result.Success<Bicicleta>(v);
            else
                return new Result.Error(401, "Ninguna vehiculo actualizada");

        } catch (Exception e) {
            return new Result.Error(444, "Algun error capturado"+ e.getMessage());
        }
    }


    /**PATINETE*/
    @Override
    public Result<Patinete> getP(String matricula) {
        Patinete p = null;
        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "{call GESTIONVEHICULOS.consultarPatinete(?,?,?, ?,?,? ,?,?,? ,?,?)}";

        try (Connection con = ds.getConnection();
             CallableStatement stmt = con.prepareCall(sql)) {

            stmt.setString(1,matricula);
            stmt.registerOutParameter(2, Types.FLOAT);
            stmt.registerOutParameter(3,Types.VARCHAR);
            stmt.registerOutParameter(4, Types.VARCHAR);
            stmt.registerOutParameter(5, Types.VARCHAR);
            stmt.registerOutParameter(6, Types.INTEGER);
            stmt.registerOutParameter(7, Types.DATE);
            stmt.registerOutParameter(8, Types.VARCHAR);
            stmt.registerOutParameter(9, Types.VARCHAR);
            stmt.registerOutParameter(10, Types.INTEGER);
            stmt.registerOutParameter(11, Types.INTEGER);

            stmt.execute();

            String v_matricula = matricula;
            float v_precioHora = stmt.getFloat(2);
            String v_marca = stmt.getString(3);
            String v_descripcion = stmt.getString(4);
            String v_color = stmt.getString(5);
            int v_bateria = stmt.getInt(6);
            Date date = stmt.getDate(7);
            String v_estado = stmt.getString(8);
            String v_idCarnet = stmt.getString(9);
            int v_numRuedas = stmt.getInt(10);
            int v_tamanyo = stmt.getInt(11);

            p = new Patinete(v_matricula, v_precioHora, v_marca, v_descripcion, v_color, v_bateria, date, v_estado,v_idCarnet, v_numRuedas, v_tamanyo);

        } catch (SQLException e) {
                e.printStackTrace();
                return new Result.Error(402, "ALGUN ERROR CAPTURADO");
            }
            if(p != null){
                return new Result.Success<Patinete>(p);
            }else{
                return new Result.Error(404, "PATIN NO ENCONTRADA");
            }
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
                        rs.getDate("fechaAdq"), rs.getString("estado"), rs.getString("idCarnet"),
                        rs.getTimestamp("changeDts"), rs.getString("changeBy"),
                        rs.getInt("numRuedas"), rs.getInt("tamanyo")
                );
                return new Result.Success<Vehiculo>(v);
            }
            return new Result.Error(400, "Ningun vehiculo eliminado");
        } catch (Exception e) {
            return new Result.Error(444, "Algun error capturado en las bicis: " + e.getMessage());
        }
    }

    @Override
    public Result<Patinete> addP(Patinete v) {// todo - error
        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "{call GESTIONVEHICULOS.insertarPatinete(?,?,? ,?,?,? ,?,?,? ,?,?)}";

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
            statement.setString(++pos, v.getIdCarnet());
            statement.setInt(++pos, v.getNumRuedas());
            statement.setInt(++pos, v.getTamanyo());

            if (!statement.execute())
                return new Result.Success<Patinete>(v);
            else
                return new Result.Error(401, "Ninguna vehiculo añadida");

        } catch (Exception e) {
            return new Result.Error(444, "Algun error capturado: \n" + e.getMessage());
        }
    }

    @Override
    public Result<Patinete> updateP(Patinete v) {
        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "{call GESTIONVEHICULOS.actualizarPatinete(?,?,?,?,?,?,?,?,?,?,?)}";

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
            statement.setString(++pos, v.getIdCarnet());
            statement.setInt(++pos, v.getNumRuedas());
            statement.setInt(++pos, v.getTamanyo());

            if (!statement.execute())
                return new Result.Success<Patinete>(v);
            else
                return new Result.Error(401, "Ninguna vehiculo actualizada");

        } catch (Exception e) {
            return new Result.Error(444, "Algun error capturado" + e.getMessage());
        }
    }

}
