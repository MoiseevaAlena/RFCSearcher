package ru.itpark.service;

import lombok.RequiredArgsConstructor;
import ru.itpark.model.QueryModel;
import ru.itpark.repository.QueryRepository;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class QueryServiceSimpleImpl implements QueryService {
    private final QueryRepository repository;

    @Override
    public void init() {
        repository.init();
    }

    @Override
    public void destroy() { }

    @Override
    public List<QueryModel> getAll() {
        return repository.getAll();
    }


    @Override
    public void search(String query) throws IOException {
        String url =  System.getenv("UPLOAD_PATH"); // "C:/Users/Alena/IdeaProjects/webApp/upload/";
        String urlResult = System.getenv("RESULTS"); // "C:/Users/Alena/IdeaProjects/webApp/uploadResult/";
        File folder = new File(url);

        boolean t;
        boolean search;
        String fileNameResult;
        String id = UUID.randomUUID().toString();
        try {
            repository.create(id, query, "INPROGRESS");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        fileNameResult = urlResult + id + ".txt";   //название файла будет как id
        FileWriter writer = new FileWriter(fileNameResult, false);

        for (File file : folder.listFiles()) {
            search = false;
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(file.getAbsolutePath())));
            String s;
            while ((s = reader.readLine()) != null) {

                t = s.toUpperCase().contains(query.toUpperCase());
                if (t) {
                    if(!search) {
                        writer.write("[" + file.getName() + "]: ");
                        search = true;
                    }
                    writer.write(s.trim());
                    writer.append('\n');
                }
            }
        }
        writer.flush();
        try {
            repository.updateStatus(id,"DONE");
        } catch (SQLException e) {
            e.printStackTrace();
        }
      //  return id;
    }
}


