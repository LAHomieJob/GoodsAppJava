package com.ecwid.goodsappjava.interfaces;

import android.support.v4.app.DialogFragment;

/*Custom listener to interact with AlerDialog*/
public interface EditAlertDialogListener {
    void onEditDialogPositiveClick(DialogFragment dialog);

    void onDeleteNegativeClick(DialogFragment dialog);
}
