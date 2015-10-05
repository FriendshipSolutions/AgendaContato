package com.example.william.agendacontato.app;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by william on 19/08/2015.
 */
public class MessageBox {

    public static void show(Context context, String titulo, String msg)
    {
        show(context, titulo, msg, android.R.drawable.ic_dialog_alert);
    }

    public static void show(Context context, String titulo, String msg, int contId)
    {
        AlertDialog.Builder dlg = new AlertDialog.Builder(context);
        dlg.setIcon(contId);
        dlg.setTitle(titulo);
        dlg.setMessage(msg);
        dlg.setNeutralButton("OK", null);
        dlg.show();
    }
}
