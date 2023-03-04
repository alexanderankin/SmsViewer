package smsviewer.app.crud_example;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;
import smsviewer.app.R;

public class ExampleRVA extends RecyclerView.Adapter<ExampleRVA.Holder> {
    private final ExampleRepository exampleRepository;

    public ExampleRVA(ExampleRepository exampleRepository) {
        this.exampleRepository = exampleRepository;
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
        holder.bind(exampleRepository.getById(position + 1));
    }

    @Override
    public int getItemCount() {
        return exampleRepository.count();
    }

    public static class Holder extends RecyclerView.ViewHolder {
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
        }

        public void bind(Example byId) {
            if (byId == null) childView.setText(R.string.example_blank);
            else childView.setText(byId.toString());
        }
    }
}
