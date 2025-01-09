package com.example.pathforge.run;

import jakarta.annotation.PostConstruct;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static java.time.temporal.ChronoUnit.HOURS;

@Repository
public class RunRepository {

    private final JdbcClient jdbcClient;

    public RunRepository(JdbcClient jdbcClient)
    {
        this.jdbcClient = jdbcClient;
    }

    List<Run> findAll()
    {
        return jdbcClient.sql("SELECT * FROM RUN").query(Run.class).list();

//        return runs;
    }

    public List<Run> findById(Integer id) {
        return jdbcClient.sql("SELECT * FROM RUN WHERE ID = ?")
                .param(1, id)
                .query(Run.class)
                .list();
    }

    public List<Run> findByRangeMiles(Integer start,Integer end) {

        return jdbcClient.sql("SELECT * FROM RUN WHERE MILES > ? AND MILES < ?")
                .param(1,start)
                .param(2,end)
                .query(Run.class)
                .list();
    }

    public void CreateRun(Run run) {
        System.out.println("Inserting Run: " + run);
        jdbcClient.sql("INSERT INTO RUN (id, title, starttime, endtime, miles, location) VALUES (?, ?, ?, ?, ?, ?)")
                .param(run.getId())                          // Bind the ID
                .param(run.getName())                        // Bind the name
                .param(run.getTimeStarted())                   // Bind the start time
                .param(run.getTimeEnded())                     // Bind the end time
                .param(run.getMiles())                       // Bind the miles
                .param(run.getLocation().toString().toUpperCase())  // Bind the location as a string
                .update();
    }
}
