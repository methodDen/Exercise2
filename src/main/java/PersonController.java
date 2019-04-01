import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import io.javalin.Context;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.SQLException;

public class PersonController {
    private Dao<Person, Long> dao;

    public PersonController() throws SQLException {
        dao = DaoManager.createDao(Connection.getSource(), Person.class);
    }

    public void create(@NotNull Context context) throws IOException {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Person.class, new PersonDeserializer());
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);

        String json = context.body();
        Person person = mapper.readValue(json, Person.class);
        try {
            dao.create(person);
            context.status(Constants.CREATED_201);
        } catch (Exception e) {
            e.printStackTrace();
            context.status(500);
            System.out.println("Error occured creating a record");
        }

    }


    public void getAll(@NotNull Context context) throws JsonProcessingException {
        SimpleModule module = new SimpleModule();
        module.addSerializer(Person.class, new PersonSerializer());
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);
        String serialized = mapper.writeValueAsString(new Person());
        try {
            context.json(dao.queryForAll());
            context.status(Constants.OK_200);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occured getting records");
            context.status(Constants.NOT_FOUND_404);
        }

    }
    public void getAllForUsers(@NotNull Context context) throws JsonProcessingException {
        SimpleModule module = new SimpleModule();
        module.addSerializer(Person.class, new PersonUserSerializer());
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);
        String serialized = mapper.writeValueAsString(new Person());
        try {
            context.json(dao.queryForAll());
            context.status(Constants.OK_200);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occured getting records");
            context.status(Constants.NOT_FOUND_404);
        }

    }


    public void getOne(@NotNull Context context, @NotNull String s) {
        long personId = Long.valueOf(s);
        try {
            Person person = dao.queryForId(personId);
            context.json(person);
            context.status(Constants.OK_200);
        } catch (SQLException e) {
            e.printStackTrace();
            context.status(Constants.BAD_REQUEST_400);
            System.out.println("Error occured getting a record");
        }

    }

}

/* Задание:
В зависимости от роли пользователя, отдавать ему разрешешнную или неразрешенную информацию
Access Manager -> javalin
2 Сериализатора (для админа и для пользователя)
почитать про аутентификацию в Javalin
 */

