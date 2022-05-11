package es.ieslavereda.server.model;

import es.ieslavereda.model.AuthenticateData;
import es.ieslavereda.model.MyDataSource;
import es.ieslavereda.model.clases.Cliente;
import es.ieslavereda.model.Result;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ImpClienteService implements IClienteService{
    @Override
    public List<Cliente> getAll() {
        List<Cliente> clientes = new ArrayList<>();

        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "SELECT * FROM person";
        try (Connection con = ds.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            Cliente cliente;

            while (rs.next()) {
                cliente = new Cliente(rs.getInt("idCliente"), rs.getString("dni"),
                        rs.getString("nombre"), rs.getString("apellidos"),
                        rs.getString("domicilio"),rs.getString("cp"),
                        rs.getString("email"),rs.getDate("fechaNac"),
                        rs.getInt("idCarnet"),rs.getByte("foto"),
                        rs.getTimestamp("changeDts"),rs.getString("changeBy"));
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    @Override //TODO
    public Result<Cliente> auth(AuthenticateData ad) {
        return null;
    }

    @Override
    public Cliente get(String id) {
        Cliente cliente=null;
        DataSource ds = MyDataSource.getMyOracleDataSource();
        String sql = "SELECT * FROM cliente WHERE dni LIKE '" + id + "'";
        try (Connection con = ds.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                cliente = new Cliente(rs.getInt("idCliente"), rs.getString("dni"),
                        rs.getString("nombre"), rs.getString("apellidos"),
                        rs.getString("domicilio"),rs.getString("cp"),
                        rs.getString("email"),rs.getDate("fechaNac"),
                        rs.getInt("idCarnet"),rs.getByte("foto"),
                        rs.getTimestamp("changeDts"),rs.getString("changeBy"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }
}
