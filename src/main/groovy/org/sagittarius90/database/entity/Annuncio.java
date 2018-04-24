package org.sagittarius90.database.entity;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="annuncio")
@NamedQueries(
        @NamedQuery(name = Annuncio.ALL_ANNUNCI, query = "from Annuncio")
)
public class Annuncio  implements Serializable{

    public static final String ALL_ANNUNCI = "Annuncio.allAnnunci";

    @Id @Column(name="id_annuncio")
    @GeneratedValue
    private Integer id;

    @Column(name="categoria")
    private String categoria;

    @Column(name="sottocategoria")
    private String sottocategoria;

    @Column(name="vendo_cerco")
    private char vendoCerco;

    @Column(name="titolo")
    private String titolo;

    @Column(name="descrizione")
    private String descrizione;

    @Column(name="prezzo")
    private double prezzo;

    @Column(name="id_utente")
    private double idUtente;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSottocategoria() {
        return sottocategoria;
    }

    public void setSottocategoria(String sottocategoria) {
        this.sottocategoria = sottocategoria;
    }

    public char getVendoCerco() {
        return vendoCerco;
    }

    public void setVendoCerco(char vendoCerco) {
        this.vendoCerco = vendoCerco;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public double getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(double idUtente) {
        this.idUtente = idUtente;
    }
}
