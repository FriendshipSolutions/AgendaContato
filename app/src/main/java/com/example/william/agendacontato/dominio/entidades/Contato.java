package com.example.william.agendacontato.dominio.entidades;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by william on 14/08/2015.
 */
public class Contato implements Serializable {

    public static String CONTATO = "CONTATO";
    public static String ID = "_id";
    public static String NOME = "NOME";
    public static String TELEFONE = "TELEFONE";
    public static String TIPOTELEFONE = "TIPOTELEFONE";
    public static String EMAIL = "EMAIL";
    public static String TIPOEMAIL = "TIPOEMAIL";
    public static String ENDERECO = "ENDERECO";
    public static String TIPOENDERECO = "TIPOENDERECO";
    public static String DATASESPECIAIS = "DATASESPECIAIS";
    public static String TIPODATASESPECIAIS = "TIPODATASESPECIAIS";
    public static String GRUPOS = "GRUPOS";

    private long    id;
    private String  nome;
    private String telefone;
    private String tipoTelefone;
    private String email;
    private String tipoEmail;
    private String endereco;
    private String tipoEndereco;
    private Date datasespeciais;
    private String tiposDatasEpeciais;
    private String grupo;

    public Contato()
    {
        id = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(String tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoEmail() {
        return tipoEmail;
    }

    public void setTipoEmail(String tipoEmail) {
        this.tipoEmail = tipoEmail;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTipoEndereco() {
        return tipoEndereco;
    }

    public void setTipoEndereco(String tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    public Date getDatasespeciais() {
        return datasespeciais;
    }

    public void setDatasespeciais(Date datasespeciais) {
        this.datasespeciais = datasespeciais;
    }

    public String getTiposDatasEpeciais() {
        return tiposDatasEpeciais;
    }

    public void setTiposDatasEpeciais(String tiposDatasEpeciais) {
        this.tiposDatasEpeciais = tiposDatasEpeciais;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    @Override
    public String toString()
    {
        return this.nome + " " + this.telefone;
    }

}
