package org.sagittarius90.database.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="utente")
@NamedQueries({
        @NamedQuery(name = Utente.ALL_UTENTI, query = "from Utente"),
        @NamedQuery(name = Utente.GET_BY_EMAIL, query = "from Utente u where u.email= :email")
})
public class Utente implements Serializable {

    public static final String ALL_UTENTI = "Utente.allUtenti";
    public static final String GET_BY_EMAIL = "User.getByEmail";

    @Id @Column(name="id_utente")
    @GeneratedValue
    private Integer id;

    @Column(name="nome")
    private String nome;

    @Column(name="cognome")
    private String cognome;

    @Column(name="email")
    private String email;

    @Column(name="telefono")
    private String telefono;

    @Column(name="password")
    private String password;

    @Column(name="cap_comune")
    private String capComune;

    @Transient
    private Session session;

    public static String getAllUtenti() {
        return ALL_UTENTI;
    }

    public static String getGetByEmail() {
        return GET_BY_EMAIL;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCapComune() {
        return capComune;
    }

    public void setCapComune(String capComune) {
        this.capComune = capComune;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
