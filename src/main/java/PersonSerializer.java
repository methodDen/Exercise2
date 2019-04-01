import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class PersonSerializer extends StdSerializer<Person> {
    public PersonSerializer() {
        this(null);
    }
    public PersonSerializer(Class<Person> t) {
        super(t);
    }

    @Override
    public void serialize(Person person, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("firstName", person.getFirstName());
        jsonGenerator.writeStringField("lastName", person.getLastName());
        jsonGenerator.writeStringField("emailAddress", person.getEmailAddress());
        jsonGenerator.writeNumberField("age", person.getAge());
        jsonGenerator.writeStringField("passwordOfUser", person.getPassword());
        jsonGenerator.writeStringField("roleOfUser", String.valueOf(person.getRole()));
        jsonGenerator.writeEndObject();
    }
}
