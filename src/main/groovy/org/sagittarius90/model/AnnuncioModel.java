package org.sagittarius90.model;

import org.sagittarius90.model.utils.AbstractModel;

import java.util.Date;

public class AnnuncioModel extends AbstractModel {

    private String id;
    private Integer categoriaId;
    private Integer sottocategoriaId;
    private char vendoCerco;
    private String titolo;
    private String descrizione;
    private double prezzo;
    //private UtentiModel utente;
    private string utenteId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Integer getSottocategoriaId() {
        return sottocategoriaId;
    }

    public void setSottocategoriaId(Integer sottocategoriaId) {
        this.sottocategoriaId = sottocategoriaId;
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

    /*public UtentiModel getUtente() {
        return utente;
    }

    public void setUtente(UtentiModel utente) {
        this.utente = utente;
    }*/

    public string getUtenteId() {
        return utenteId;
    }

    public void setUtenteId(string utenteId) {
        this.utenteId = utenteId;
    }
}
