package ru.itpark.repository;

import lombok.RequiredArgsConstructor;
import ru.itpark.model.QueryModel;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class QueryRepositorySqliteImpl implements QueryRepository {
    private final DataSource dataSource;

    @Override
    public void init() {
        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();

        ) {
           statement.execute("CREATE TABLE IF NOT EXISTS  queries (id TEXT PRIMARY KEY, query TEXT NOT NULL, status TEXT NOT NULL)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<QueryModel> getAll() {
        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT id, query, status FROM queries");
        ) {
            var list = new ArrayList<QueryModel>();
            while (resultSet.next()) {
                list.add(new QueryModel(resultSet.getString("id"),
                        resultSet.getString("query"),
                        resultSet.getString("status"))
                );
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public String create(String id, String query, String status) throws SQLException {
        try (

                Connection connection = dataSource.getConnection();
                var statement = connection.prepareStatement("INSERT INTO queries (id, query,status) VALUES (?, ?, ?)");
        ) {
            statement.setString(1, id);
            statement.setString(2, query);
            statement.setString(3, status);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void updateStatus(String id, String status) throws SQLException {
        try (

                Connection connection = dataSource.getConnection();
                var statement = connection.prepareStatement("UPDATE queries SET status = ? where id = ?");
        ) {
            statement.setString(1, status);
            statement.setString(2, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
