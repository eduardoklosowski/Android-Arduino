package br.klosowski.eduardo.arduino;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class ConfirmDeleteDialog {
    private AlertDialog.Builder builder;

    public ConfirmDeleteDialog(Context context, DialogInterface.OnClickListener onPositive) {
        builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.msg_confirm_delete);
        builder.setPositiveButton(R.string.yes, onPositive);
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
    }

    public AlertDialog make() {
        return builder.create();
    }
}
