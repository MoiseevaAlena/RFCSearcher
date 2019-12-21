package ru.itpark.service;

import ru.itpark.model.QueryModel;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;


public interface QueryService {
    void init();
    void destroy();
    List<QueryModel> getAll();
    void search(Path path, String query) throws IOException;
}
