import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

public class PersonDeserializer extends StdDeserializer<Person> {
    public PersonDeserializer(Class<?> vc) {
        super(vc);
    }
    public PersonDeserializer() {
        this(null);
    }

    @Override
    public Person deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        String personFirstName = jsonNode.get("firstName").asText();
        String personLastName = jsonNode.get("lastName").asText();
        String emailAddress = jsonNode.get("emailAddress").asText();
        int age = Integer.valueOf(jsonNode.get("age").asText());
        String password = jsonNode.get("passwordOfUser").asText();
        String role = jsonNode.get("roleOfUser").asText();
        return new Person(personFirstName, personLastName, emailAddress, age, BCrypt.hashpw(password, BCrypt.gensalt()), Role.valueOf(role));

    }
}
