import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import io.javalin.Context;
import io.javalin.Javalin;
import io.javalin.UnauthorizedResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static io.javalin.apibuilder.ApiBuilder.*;


public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        Javalin app = Javalin.create().port(7000);

        app.accessManager(((handler, ctx, permittedRoles) -> { // Динамическое объявление AccesManager'a
            Role role = Connection.getUserRole(ctx);
            if (permittedRoles.contains(role)) {
                handler.handle(ctx); // handle - это строки номер 34 и 35
            } else {
                ctx.status(403);
            }
        }));

        app.start();
        app.routes(() -> {
            path("persons", ()-> {
                get(ctx -> new PersonController().getAll(ctx));
                post(ctx -> new PersonController().create(ctx));
            });
        });
    }
//

}
// Если строки пароля и логина не переданы (null) -> role = anonymous

