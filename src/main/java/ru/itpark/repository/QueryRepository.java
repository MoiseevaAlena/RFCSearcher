package ru.itpark.repository;

import ru.itpark.model.QueryModel;

import java.sql.SQLException;
import java.util.List;

public interface QueryRepository {
    void init();
    List<QueryModel> getAll();
    String create(String id, String searchLine, String status) throws SQLException;
    void updateStatus(String id,String status) throws SQLException;
}
