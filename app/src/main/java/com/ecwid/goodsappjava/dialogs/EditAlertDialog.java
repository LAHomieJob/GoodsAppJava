package com.ecwid.goodsappjava.dialogs;

import android.app.Dialog;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.ecwid.goodsappjava.R;
import com.ecwid.goodsappjava.interfaces.EditAlertDialogListener;

public class EditAlertDialog extends DialogFragment implements LifecycleObserver {

    EditAlertDialogListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the  EditAlertDialogListenerso we can send events to the host
            mListener = (EditAlertDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement DeleteAlertDialogListener");
        }
    }

    @NonNull
    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder
                .setMessage(getContext().getString(R.string.edit_or_delete_product))
                .setPositiveButton(R.string.edit, (dialog, id) ->
                        mListener.onEditDialogPositiveClick(EditAlertDialog.this))
                .setNegativeButton(R.string.delete, (dialog, id) ->
                        mListener.onDeleteNegativeClick(EditAlertDialog.this));
        return builder.create();
    }
}
