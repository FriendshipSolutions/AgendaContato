package com.example.william.agendacontato.database;

/**
 * Created by william on 12/08/2015.
 */
public class ScriptSQL {

    public static String getCreateContato()
    {
        StringBuilder objSql = new StringBuilder();

        objSql.append("CREATE TABLE IF NOT EXISTS CONTATO ( ");
        objSql.append(" _id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        objSql.append(" NOME VARCHAR(200), ");
        objSql.append(" TELEFONE VARCHAR(14), ");
        objSql.append(" TIPOTELEFONE VARCHAR(1), ");
        objSql.append(" EMAIL VARCHAR(255), ");
        objSql.append(" TIPOEMAIL VARCHAR(1), ");
        objSql.append(" ENDERECO VARCHAR(255), ");
        objSql.append(" TIPOENDERECO VARCHAR(1), ");
        objSql.append(" DATASESPECIAIS DATE, ");
        objSql.append(" TIPODATASESPECIAIS VARCHAR(1), ");
        objSql.append(" GRUPOS VARCHAR(255) ");
        objSql.append(");");

        return  objSql.toString();

    }
}
