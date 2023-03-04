package smsviewer.app.crud_example;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import smsviewer.app.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExampleRepository extends SQLiteOpenHelper {
    private final ArrayList<Example> examples = new ArrayList<>();
    @NonNull
    private final Context context;

    public ExampleRepository(@NonNull Context context) {
        super(context, null, null, 1);
        this.context = context;
    }

    public ExampleRepository addSamples() {
        examples.addAll(IntStream.range(examples.size(), examples.size() + 3).boxed().map(this::nth).collect(Collectors.toList()));
        return this;
    }

    public Example getById(int id) {
        getReadableDatabase().rawQuery("select * from example where id = ?", new String[]{id})
        try {
            return examples.get(id);
        } catch (Exception e) {
            return null;
        }
    }

    public int count() {
        return examples.size();
    }

    public void addNext() {
        examples.add(
                nth(examples.size())
        );
    }

    private Example nth(int n) {
        return new Example()
                .setId(n)
                .setName("example " + n)
                .setCreated(new Date());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(context.getString(R.string.example_create_table_sql));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table example");
    }
}
