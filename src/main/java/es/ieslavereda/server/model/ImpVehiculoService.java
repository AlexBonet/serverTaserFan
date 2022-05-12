package es.ieslavereda.server.model;

import es.ieslavereda.model.MyDataSource;
import es.ieslavereda.model.Result;
import es.ieslavereda.model.clases.Vehiculo;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//TODO tots els metodos
public class ImpVehiculoService implements IVehiculoService{
    @Override
    public List<Vehiculo> getAll() {
        List<Vehiculo> vehiculos = new ArrayList<>();

        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "SELECT * FROM person";
        try (Connection con = ds.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            Vehiculo vehiculo;

            while (rs.next()) {
                vehiculo = new Vehiculo(rs.getString("matricula"), rs.getFloat("precioHora"),
                        rs.getString("marca"), rs.getString("descripcion"),
                        rs.getString("color"),rs.getInt("bateria"),
                        rs.getDate("fechaAdq"),rs.getString("estado"),rs.getInt("idCarnet"),
                        rs.getTimestamp("changeDts"),rs.getString("changeBy"));
                vehiculos.add(vehiculo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehiculos;
    }

    @Override
    public Result<Vehiculo> delete(String matricula) {
        DataSource ds= MyDataSource.getMyOracleDataSource();
        String sql = "DELETE FROM vehiculo WHERE matricucula ="+matricula+";";//TODO tindria que fer un delete per cada tipo per a llevarlo de les atres bases?

        try (Connection con = ds.getConnection();
            CallableStatement statement = con.prepareCall(sql);){

            int pos =0;
            statement.setString(++pos,matricula);

            ResultSet rs= statement.getResultSet();
            if (rs.next()){
                Vehiculo v = new Vehiculo(matricula, rs.getFloat("precioHora"),
                        rs.getString("marca"), rs.getString("descripcion"),
                        rs.getString("color"),rs.getInt("bateria"),
                        rs.getDate("fechaAdq"),rs.getString("estado"),rs.getInt("idCarnet"),
                        rs.getTimestamp("changeDts"),rs.getString("changeBy"));
                return new Result.Success<Vehiculo>(v);
            }
            return new Result.Error(400,"Ningun vehiculo eliminado");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new Result.Error(444,"Algun error capturado");
        }
    }

    @Override
    public Result<Vehiculo> update(Vehiculo v) {
        return null;
    }

    @Override
    public Result<Vehiculo> add(Vehiculo v) {
        return null;
    }

    @Override
    public Result<Vehiculo> get(String matricula) {
        return null;
    }
}
