import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class PersonUserSerializer extends StdSerializer<Person> {

    public PersonUserSerializer() {
        this(null);
    }

    public PersonUserSerializer(Class<Person> t) {
        super(t);
    }

    @Override
    public void serialize(Person person, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("firstName", person.getFirstName());
        jsonGenerator.writeStringField("lastName", person.getLastName());
        jsonGenerator.writeNumberField("age", person.getAge());
        jsonGenerator.writeEndObject();
    }
}
