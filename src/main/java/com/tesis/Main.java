package com.tesis;

import com.tesis.jooq.tables.daos.UsersDao;
import com.tesis.jooq.tables.pojos.Users;
import org.jooq.Configuration;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class Main {

    public static void main(String[] args) {

//        new Application().init();
        String userName = "ferjobfmeqjprm";
        String password = "ec949c7ef858b46668966285a5cfa165e42d8a481444c6f8a42b0f1c0b481076";
        String url = "jdbc:postgresql://ec2-23-21-128-35.compute-1.amazonaws.com:5432/d6em601ppakco6?sslmode=require";

        // Connection is the only JDBC resource that we need
        // PreparedStatement and ResultSet are handled by jOOQ, internally
        try (Connection connection = DriverManager.getConnection(url, userName, password)) {

//            db.insertInto(USERS)
//                    .set(USERS.ID, 12345678L)
//                    .set(USERS.USER_NAME, "Diego")
//                    .set(USERS.PASSWORD, "lkasdkal931")
//                    .set(USERS.NAME, "Diego")
//                    .set(USERS.LAST_NAME, "Hernandez")
//                    .set(USERS.DNI, "36354805")
//                    .set(USERS.ADDRESS, "Address")
//                    .set(USERS.PHONE, "123453421")
//                    .set(USERS.EMAIL, "drhernandez92@gmail.com")
//                    .execute();

            Configuration configuration = new DefaultConfiguration().set(connection).set(SQLDialect.POSTGRES_10);

            UsersDao usersDao = new UsersDao(configuration);
            List<Users> users = usersDao.findAll();

            Users user = usersDao.fetchByDni("2037374105").get(0);

            connection.close();
        }

        // For the sake of this tutorial, let's keep exception handling simple
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
