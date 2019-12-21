package ru.itpark.service;

import lombok.RequiredArgsConstructor;
import ru.itpark.model.QueryModel;
import ru.itpark.repository.QueryRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class QueryServiceThreadPoolImpl implements QueryService {
    private final QueryRepository repository;
    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    @Override
    public void init() {
        repository.init();
    }

    @Override
    public void destroy() {
        executor.shutdownNow();
    }

    @Override
    public List<QueryModel> getAll() {
        return repository.getAll();
    }

    @Override
    public void search(String query) {
        String url = System.getenv("UPLOAD_PATH");
        String urlResult = System.getenv("RESULTS");
        String id = UUID.randomUUID().toString();
        try {
            repository.create(id, query, "ENQUEUED");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        executor.execute(() -> {
            boolean t;
                    Path resultPath = Paths.get(urlResult + "\\"+id+".txt");
                    try {
                        //Path resultFile =
                        Files.createFile(resultPath);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        repository.updateStatus(id,"INPROGRESS");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try {
                        List<String> listLines = new ArrayList<>();
                        List<String> foundLines = new ArrayList<>();
                        List<Path> pathFiles = Files.list(Paths.get(System.getenv("UPLOAD_PATH")))
                                .collect(Collectors.toList());

                        for (Path pathFile : pathFiles) {
                            if (Files.exists(pathFile)) {

                                try {
                                    listLines = Files.readAllLines(pathFile)
                                            .stream()
                                            .filter(o -> o.toUpperCase().contains(query.toUpperCase()))
                                            .collect(Collectors.toList());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                t = true;
                                for (String s : listLines) {
                                    if(t) {
                                        foundLines.add(("[" + pathFile.getFileName().toString() + "] ").concat(s));
                                        t = false;
                                    } else {
                                        foundLines.add(s);
                                    }
                                }
                            }
                        }
                      Files.write(resultPath, foundLines);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        repository.updateStatus(id,"DONE");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
        );
    }
}
