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
}
