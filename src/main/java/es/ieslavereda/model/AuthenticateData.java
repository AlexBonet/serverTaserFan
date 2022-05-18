package es.ieslavereda.model;

public class AuthenticateData {
    private String email;
    private String password;

    public AuthenticateData(String identificador, String password) {
        this.email = identificador;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
