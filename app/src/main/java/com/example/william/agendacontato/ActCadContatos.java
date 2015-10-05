package com.example.william.agendacontato;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import android.content.*;


import com.example.william.agendacontato.app.DateUtils;
import com.example.william.agendacontato.app.MessageBox;
import com.example.william.agendacontato.app.ViewHelper;
import com.example.william.agendacontato.database.Database;
import com.example.william.agendacontato.dominio.RepositorioContato;
import com.example.william.agendacontato.dominio.entidades.Contato;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class ActCadContatos extends AppCompatActivity  {

    private SQLiteDatabase objCon;
    private Database objDatabase;

    private RepositorioContato repositorioContato;
    private Contato contato;

    private EditText edtNome;
    private EditText edtTelefone;
    private EditText edtEmail;
    private EditText edtEndereco;
    private EditText edtDatasEspeciais;
    private EditText edtGrupos;

    private Spinner spnTipoTelefone;
    private Spinner spnTipoEmail;
    private Spinner spnTipoEndereco;
    private Spinner spnTipoDatasEspeciais;

    private ArrayAdapter<String> adpTipoTelefone;
    private ArrayAdapter<String> adpTipoEmail;
    private ArrayAdapter<String> adpTipoEndereco;
    private ArrayAdapter<String> adpTipoDatasEspeciais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cad_contatos);

        edtNome = (EditText)findViewById(R.id.edtNome);
        edtTelefone = (EditText)findViewById(R.id.edtTelefone);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtEndereco = (EditText)findViewById(R.id.edtEndereco);
        edtDatasEspeciais = (EditText)findViewById(R.id.edtDatasEpeciais);
        edtGrupos = (EditText)findViewById(R.id.edtGrupos);

        spnTipoTelefone = (Spinner)findViewById(R.id.spnTipoTelefone);
        spnTipoEmail = (Spinner)findViewById(R.id.spnTipoEmail);
        spnTipoEndereco = (Spinner)findViewById(R.id.spnTipoEndereco);
        spnTipoDatasEspeciais = (Spinner)findViewById(R.id.spnTipoDataEspecias);

        adpTipoTelefone = ViewHelper.createArrayAdapter(this, spnTipoTelefone);
        adpTipoEmail = ViewHelper.createArrayAdapter(this, spnTipoEmail);
        adpTipoEndereco = ViewHelper.createArrayAdapter(this, spnTipoEndereco);
        adpTipoDatasEspeciais = ViewHelper.createArrayAdapter(this, spnTipoDatasEspeciais);

        adpTipoEmail.add("Casa");
        adpTipoEmail.add("Trabalho");
        adpTipoEmail.add("Outros");

        adpTipoTelefone.add("Celular");
        adpTipoTelefone.add("Trabalho");
        adpTipoTelefone.add("Casa");
        adpTipoTelefone.add("Principal");
        adpTipoTelefone.add("Fax Trabalho");
        adpTipoTelefone.add("Fax Casa");
        adpTipoTelefone.add("Page");
        adpTipoTelefone.add("Outros");

        adpTipoEndereco.add("Casa");
        adpTipoEndereco.add("Trabalho");
        adpTipoEndereco.add("Outros");

        adpTipoDatasEspeciais.add("Aniversario");
        adpTipoDatasEspeciais.add("Data Comemorativa");
        adpTipoDatasEspeciais.add("Outros");

        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.containsKey(ActContato.PAR_CONTATO)) {
            contato = (Contato) bundle.getSerializable(ActContato.PAR_CONTATO);
            preencheDados();
        }
        else
            contato = new Contato();

        ExibeDataListener exibeDataListener =  new ExibeDataListener();

        edtDatasEspeciais.setOnClickListener(exibeDataListener);
        edtDatasEspeciais.setOnFocusChangeListener(exibeDataListener);
        edtDatasEspeciais.setKeyListener(null);

        try
        {
            objDatabase = new Database(this);
            objCon = objDatabase.getWritableDatabase();

            repositorioContato = new RepositorioContato(objCon);

        }
        catch (SQLException ex) {
            MessageBox.show(this, "Erro", "Erro ao criar o banco de dados: " + ex.getMessage());
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (objCon != null)
            objCon.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_act_cad_contatos, menu);

        if (contato.getId() != 0)
            menu.getItem(1).setVisible(true);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.mni_acao1:
                salvar();
                finish();
                break;
            case R.id.mni_acao2:
                excluir();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void preencheDados()
    {
        edtNome.setText(contato.getNome());
        edtTelefone.setText(contato.getTelefone());
        spnTipoTelefone.setSelection(Integer.parseInt(contato.getTipoTelefone()));
        edtEmail.setText(contato.getEmail());
        spnTipoEmail.setSelection(Integer.parseInt(contato.getTipoEmail()));
        edtEndereco.setText(contato.getEndereco());
        spnTipoEndereco.setSelection(Integer.parseInt(contato.getTipoEndereco()));

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String dt = dateFormat.format(contato.getDatasespeciais());
        edtDatasEspeciais.setText(dt);
        spnTipoDatasEspeciais.setSelection(Integer.parseInt(contato.getTiposDatasEpeciais()));

        edtGrupos.setText(contato.getGrupo());
    }

    private void salvar()
    {
        try {

            contato.setNome(edtNome.getText().toString());
            contato.setTelefone(edtTelefone.getText().toString());
            contato.setEmail(edtEmail.getText().toString());
            contato.setEndereco(edtEndereco.getText().toString());
            contato.setGrupo(edtGrupos.getText().toString());

            contato.setTipoTelefone(String.valueOf(spnTipoTelefone.getSelectedItemPosition()));
            contato.setTipoEmail(String.valueOf(spnTipoEmail.getSelectedItemPosition()));
            contato.setTipoEndereco(String.valueOf(spnTipoEndereco.getSelectedItemPosition()));
            contato.setTiposDatasEpeciais(String.valueOf(spnTipoDatasEspeciais.getSelectedItemPosition()));

            if (contato.getId() == 0) {
                repositorioContato.Inserir(contato);
            }
            else {
                repositorioContato.Alterar(contato);
            }
        }
        catch (Exception ex) {
            MessageBox.show(this, "Erro", "Erro ao gravar os dados: " + ex.getMessage());
        }
    }

    private void excluir()
    {
        try {

                repositorioContato.Excluir(contato.getId());
        }
        catch (Exception ex) {
            MessageBox.show(this, "Erro", "Erro ao excluir os dados: " + ex.getMessage());
        }
    }



    private void exibeData()
    {
        Calendar calendar = Calendar.getInstance();
        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dlg = new DatePickerDialog(this, new SelecionaDataListener(), ano, mes, dia);
        dlg.show();
    }

    private class ExibeDataListener implements View.OnClickListener, View.OnFocusChangeListener
    {

        @Override
        public void onClick(View v) {
            exibeData();
        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus)
                exibeData();
        }
    }

    private class SelecionaDataListener implements DatePickerDialog.OnDateSetListener
    {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            edtDatasEspeciais.setText(DateUtils.DataToString(year, monthOfYear, dayOfMonth));
            contato.setDatasespeciais(DateUtils.getDate(year, monthOfYear, dayOfMonth));
        }
    }

}
