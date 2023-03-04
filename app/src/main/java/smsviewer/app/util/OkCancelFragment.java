package smsviewer.app.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class OkCancelFragment extends DialogFragment {
    private final String title;
    private final String message;
    private final Runnable okCallback;
    private final Runnable cancelCallback;

    public OkCancelFragment(String title, String message, Runnable okCallback, Runnable cancelCallback) {
        this.title = title;
        this.message = message;
        this.okCallback = okCallback;
        this.cancelCallback = cancelCallback;
    }

    // https://stackoverflow.com/a/26458543
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", (dialog, id) -> okCallback.run());

        builder.setNegativeButton("Cancel", (dialog, which) -> cancelCallback.run());

        // Create the AlertDialog object and return it
        return builder.create();
    }
}
