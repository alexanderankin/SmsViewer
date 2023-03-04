package smsviewer.app.crud_example;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExampleRepository {
    private final ArrayList<Example> examples = new ArrayList<>();

    public ExampleRepository addSamples() {
        examples.addAll(IntStream.range(examples.size(), examples.size() + 3).boxed().map(this::nth).collect(Collectors.toList()));
        return this;
    }

    public Example getById(int id) {
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
}
