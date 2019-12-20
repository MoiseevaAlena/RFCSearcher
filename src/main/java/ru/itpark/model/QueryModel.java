package ru.itpark.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itpark.enumeration.QueryStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryModel {
    private String id;
    private String query;
    private QueryStatus status;

    public QueryModel(String id, String query,String status) {
        this.id = id;
        this.query = query;
        if (status.equalsIgnoreCase("ENQUEUED")) {
            this.status = QueryStatus.ENQUEUED;
        } if (status.equalsIgnoreCase("INPROGRESS")) {
            this.status = QueryStatus.INPROGRESS;
        } if (status.equalsIgnoreCase("DONE")) {
            this.status = QueryStatus.DONE;
        }
    }
}

