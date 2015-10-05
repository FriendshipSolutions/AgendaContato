package com.example.william.agendacontato;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;
import android.content.*;
import android.database.sqlite.*;
import android.database.*;

import com.example.william.agendacontato.app.MessageBox;
import com.example.william.agendacontato.database.Database;
import com.example.william.agendacontato.dominio.RepositorioContato;
import com.example.william.agendacontato.dominio.entidades.Contato;

public class ActContato extends AppCompatActivity implements OnClickListener, AdapterView.OnItemClickListener {

    private ImageButton btnAdicionar;
    private EditText edtPesquisa;
    private ListView lstContatos;
    private Database objDatabase;
    private SQLiteDatabase objCon;
    private ArrayAdapter<Contato> adpContatos;
    private RepositorioContato repositorioContato;
    private FiltraDados filtraDados;

    public static final String PAR_CONTATO = "CONTATO";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_contato);

        btnAdicionar = (ImageButton)findViewById(R.id.btnAdicionar);
        edtPesquisa = (EditText)findViewById(R.id.edtPesquisa);
        lstContatos = (ListView)findViewById((R.id.lstContatos));

        btnAdicionar.setOnClickListener(this);
        lstContatos.setOnItemClickListener(this);


        try
        {
            objDatabase = new Database(this);
            objCon = objDatabase.getWritableDatabase();

            repositorioContato = new RepositorioContato(objCon);
            adpContatos = repositorioContato.buscaContatos(this);

            lstContatos.setAdapter(adpContatos);

            filtraDados = new FiltraDados(adpContatos);

            edtPesquisa.addTextChangedListener(filtraDados);


        }
        catch (SQLException ex) {
            MessageBox.show(this, "Erro", "Erro ao gravar os dados: " + ex.getMessage());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (objCon != null)
            objCon.close();
    }

    @Override
    public void onClick(View v) {
        Intent it = new Intent(this, ActCadContatos.class);
        startActivityForResult(it, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        adpContatos = repositorioContato.buscaContatos(this);
        filtraDados.setArrayAdapter(adpContatos);
        lstContatos.setAdapter(adpContatos);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Contato contato =   adpContatos.getItem(position);
        Intent it = new Intent(this, ActCadContatos.class);
        it.putExtra(PAR_CONTATO, contato);
        startActivityForResult(it, 0);

    }

    private class FiltraDados implements TextWatcher {

        private ArrayAdapter<Contato> arrayAdapter;

        private FiltraDados(ArrayAdapter<Contato> arrayAdapter) {
            this.arrayAdapter = arrayAdapter;
        }

        public void setArrayAdapter(ArrayAdapter<Contato> arrayAdapter)
        {
            this.arrayAdapter = arrayAdapter;
        }


        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            arrayAdapter.getFilter().filter(s);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
