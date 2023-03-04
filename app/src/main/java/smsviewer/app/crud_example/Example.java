package smsviewer.app.crud_example;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(chain = true)
@Data
public class Example {
    Integer id;
    String name;
    Date created;

    public Integer getId() {
        return id;
    }

    public Example setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Example setName(String name) {
        this.name = name;
        return this;
    }

    public Date getCreated() {
        return created;
    }

    public Example setCreated(Date created) {
        this.created = created;
        return this;
    }
}
