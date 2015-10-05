package com.example.william.agendacontato.dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.*;
import android.database.sqlite.*;
import android.widget.ArrayAdapter;

import com.example.william.agendacontato.ContatoArrayAdapter;
import com.example.william.agendacontato.R;
import com.example.william.agendacontato.dominio.entidades.Contato;

import java.util.Date;

/**
 * Created by william on 13/08/2015.
 */
public class RepositorioContato {

    private SQLiteDatabase conn;

    public RepositorioContato(SQLiteDatabase conn)
    {
        this.conn = conn;
    }

    private ContentValues preencheContentValues(Contato contato){
        ContentValues values = new ContentValues();

        values.put(Contato.NOME, contato.getNome());
        values.put(Contato.TELEFONE, contato.getTelefone());
        values.put(Contato.TIPOTELEFONE, contato.getTipoTelefone());
        values.put(Contato.EMAIL, contato.getEmail());
        values.put(Contato.TIPOEMAIL, contato.getTipoEmail());
        values.put(Contato.ENDERECO, contato.getEndereco());
        values.put(Contato.TIPOENDERECO, contato.getTipoEndereco());
        values.put(Contato.DATASESPECIAIS, contato.getDatasespeciais().getTime());
        values.put(Contato.TIPODATASESPECIAIS, contato.getTiposDatasEpeciais());
        values.put(Contato.GRUPOS, contato.getGrupo());

        return  values;
    }


    public void Inserir(Contato contato)
    {
        ContentValues values = preencheContentValues(contato);
        conn.insertOrThrow(Contato.CONTATO, null, values);
    }

    public void Alterar(Contato contato)
    {
        ContentValues values = preencheContentValues(contato);
        conn.update(Contato.CONTATO, values, "_ID = ?", new String[]{String.valueOf(contato.getId())});
    }

    public void Excluir(long id)
    {
        conn.delete(Contato.CONTATO, "_ID = ?", new String[]{String.valueOf(id)});
    }

    public ContatoArrayAdapter buscaContatos(Context context)
    {
        ContatoArrayAdapter adpContatos = new ContatoArrayAdapter(context, R.layout.item_contato);

        //rawQuery para passar a Querie necessária
        Cursor cursor = conn.query(Contato.CONTATO, null, null, null, null, null, null);

        if (cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            do
            {
                Contato contato = new Contato();

                contato.setId(cursor.getLong(cursor.getColumnIndex(Contato.ID)));
                contato.setNome(cursor.getString(cursor.getColumnIndex(Contato.NOME)));
                contato.setTelefone(cursor.getString(cursor.getColumnIndex(Contato.TELEFONE)));
                contato.setTipoTelefone(cursor.getString(cursor.getColumnIndex(Contato.TIPOTELEFONE)));
                contato.setEmail(cursor.getString(cursor.getColumnIndex(Contato.EMAIL)));
                contato.setTipoEmail(cursor.getString(cursor.getColumnIndex(Contato.TIPOEMAIL)));
                contato.setEndereco(cursor.getString(cursor.getColumnIndex(Contato.ENDERECO)));
                contato.setTipoEndereco(cursor.getString(cursor.getColumnIndex(Contato.TIPOENDERECO)));
                contato.setDatasespeciais(new Date(cursor.getLong(cursor.getColumnIndex(Contato.DATASESPECIAIS))));
                contato.setTiposDatasEpeciais(cursor.getString(cursor.getColumnIndex(Contato.TIPODATASESPECIAIS)));
                contato.setGrupo(cursor.getString(cursor.getColumnIndex(Contato.GRUPOS)));

                adpContatos.add(contato);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return  adpContatos;

    }



}
