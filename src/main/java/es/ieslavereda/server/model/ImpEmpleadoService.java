package es.ieslavereda.server.model;

import es.ieslavereda.model.AuthenticateData;
import es.ieslavereda.model.MyDataSource;
import es.ieslavereda.model.Result;
import es.ieslavereda.model.clases.Empleado;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImpEmpleadoService implements IEmpleadoService {
    @Override
    public List<Empleado> getAll() {
        List<Empleado> empleados = new ArrayList<>();

        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "SELECT * FROM empleado";
        try (Connection con = ds.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            Empleado empleado;

            while (rs.next()) {
                empleado = new Empleado(rs.getInt("IDEMPLEADO"), rs.getString("dni"),
                        rs.getString("nombre"), rs.getString("apellidos"),
                        rs.getString("domicilio"),rs.getString("cp"),
                        rs.getString("email"),rs.getDate("fechaNac"),
                        rs.getString("cargo"));
                empleados.add(empleado);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleados;
    }

    @Override //TODO, dona error de datos incorrectos
    public Result<Empleado> auth(AuthenticateData ad) {
        DataSource ds = MyDataSource.getMyOracleDataSource();
        Empleado e = null;
        try(Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement("select * from empleado where email like ? and ENCRYPT_PASWD.decrypt_val(password) like ?");){

            int pos = 0;
            stmt.setString(++pos, ad.getIdentificador());
            stmt.setString(++pos, ad.getPassword());
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                e = new Empleado(rs.getInt("idEmpleado"), rs.getString("dni"),
                        rs.getString("nombre"), rs.getString("apellidos"),
                        rs.getString("domicilio"),rs.getString("cp"),
                        rs.getString("email"),rs.getDate("fechaNac"),
                        rs.getString("cargo"));
                return new Result.Success<Empleado>(e);
            }else{
                return new Result.Error(404, "Datos incorrectos");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new Result.Error(404, "Erros de acceso a la base de datos");
        }
    }

    @Override
    public Empleado get(String id) {
        Empleado empleado=null;
        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "SELECT * FROM empleado WHERE dni LIKE '" + id + "'";
        try (Connection con = ds.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                empleado = new Empleado(rs.getInt("idEmpleado"), rs.getString("dni"),
                        rs.getString("nombre"), rs.getString("apellidos"),
                        rs.getString("domicilio"),rs.getString("cp"),
                        rs.getString("email"),rs.getDate("fechaNac"),
                        rs.getString("cargo"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleado;
    }
}
