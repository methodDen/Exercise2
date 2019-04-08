import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import io.javalin.Context;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Connection {
    private static ConnectionSource source;

    static {
        try {
            source = new JdbcConnectionSource("jdbc:sqlite:C:\\SQL\\Personas.db");
            TableUtils.createTableIfNotExists(source, Person.class);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occured connecting to database");
        }
    }

    public static ConnectionSource getSource() {
        return source;
    }

    public static Role getUserRole(Context ctx) throws SQLException {
        if (ctx.header("Authorization") == null) return Role.ANONYMOUS;
        String login = ctx.basicAuthCredentials().getUsername();
        String password = ctx.basicAuthCredentials().getPassword();
            Dao<Person, Integer> personDAO = DaoManager.createDao(Connection.getSource(), Person.class);
            List<Person> whoGotLogin = new ArrayList<>();
            whoGotLogin = personDAO.queryBuilder().where().eq("id", login).query();
            if (whoGotLogin.size() == 1) {
                Person p = whoGotLogin.get(0);
                if (BCrypt.checkpw(password, p.getPassword())) {
                    return p.getRole();
                }
            }
            return Role.ANONYMOUS;
    }
}