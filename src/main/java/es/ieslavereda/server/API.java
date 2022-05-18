package es.ieslavereda.server;

public class API {
    public static class Routes {
        //Clientes
        public static final String AUTHENTICATE = "/auth";
        public static final String ALL_EMPLEADOS = "/empleados";
        public static final String GET_EMPLEADO = "/empleado";

        //Vehiculos : tenim soles una ruta perque cuant fem algo en la app dira que tipo es [post,get,put,dlt]
        public static final String COCHE = "/coche";
        public static final String MOTOS = "/motos";
        public static final String PATIN = "/patin";
        public static final String BICIS = "/bicis";


    }
}
