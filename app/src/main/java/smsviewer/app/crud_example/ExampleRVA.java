package smsviewer.app.crud_example;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;
import smsviewer.app.R;
import smsviewer.app.util.OkCancelFragment;

public class ExampleRVA extends RecyclerView.Adapter<ExampleRVA.Holder> {
    private final ExampleRepository exampleRepository;
    private final ExamplesActivity examplesActivity;

    public ExampleRVA(ExampleRepository exampleRepository, ExamplesActivity examplesActivity) {
        this.exampleRepository = exampleRepository;
        this.examplesActivity = examplesActivity;
    }

    @NonNull
    @NotNull
    @Override
    public Holder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = inflate(parent);

        return new Holder(view);
    }

    private View inflate(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(Holder.HOLDER_VIEW, parent, false);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Holder holder, int position) {
        holder.bind(exampleRepository.getNth(position));
    }

    @Override
    public int getItemCount() {
        return exampleRepository.count();
    }

    public class Holder extends RecyclerView.ViewHolder {
        /**
         * the view of this holder
         */
        public static final int HOLDER_VIEW = R.layout.example_item;

        /**
         * a subview of this holder which contains the template "blanks"
         */
        private static final int CHILD_VIEW = R.id.itemLabel;

        private final TextView childView;

        public Holder(@NonNull @NotNull View itemView) {
            super(itemView);
            childView = itemView.findViewById(CHILD_VIEW);
            itemView.setOnClickListener(this::clicked);
        }

        private void clicked(View view) {
            DialogFragment dialog = new OkCancelFragment("Delete Example", "Are you sure you want to delete this example", () -> {
                exampleRepository.delNth(getAdapterPosition());
                examplesActivity.notifyDs();
            }, () -> {});
            dialog.show(examplesActivity.getSupportFragmentManager(), "OkCancelFragment");
        }

        public void bind(Example byId) {
            if (byId == null) childView.setText(R.string.example_blank);
            else childView.setText(byId.toString());
        }
    }
}
