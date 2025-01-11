package com.example.pathforge.Job;

import com.example.pathforge.PathForgeApplication;
import com.example.pathforge.Users.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

@Repository
public class JobRepository {

    private final JdbcClient jdbcClient;
    private static final Logger log = Logger.getLogger(JobRepository.class.getName());

    public JobRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    private RowMapper<Job> jobRowMapper = (rs, rowNum) -> {
        UUID id = (UUID) rs.getObject("id");
        String title = rs.getString("title");
        String description = rs.getString("description");
        String companyName = rs.getString("company_name");
        String location = rs.getString("location");
        String jobType = rs.getString("job_type");
        String salaryRange = rs.getString("salary_range");
        String tagsJson = rs.getString("tags");
        String requirements = rs.getString("requirements");
        boolean isActive = rs.getBoolean("is_active");

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,Object> tags = null;

        try {
            if (tagsJson != null && !tagsJson.isEmpty()) {
                tags = objectMapper.readValue(tagsJson, new TypeReference<Map<String,Object>>() {});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;

        if (rs.getTimestamp("created_at") != null) {
            createdAt = rs.getTimestamp("created_at").toLocalDateTime();
        }

        if (rs.getTimestamp("updated_at") != null) {
            updatedAt = rs.getTimestamp("updated_at").toLocalDateTime();
        }

        return new Job(id, title, description, companyName, location, jobType, salaryRange, tags, requirements, createdAt, updatedAt, isActive);
    };

    public List<Job> findAllJobs() {
        return jdbcClient.sql("SELECT * FROM JOBS")
                .query(jobRowMapper)
                .list();
    }

    public Job findJobById(UUID id) {
        return jdbcClient.sql("SELECT * FROM JOBS WHERE id = ?")
                .param(1, id)  // Binding the UUID parameter
                .query(jobRowMapper)
                .stream()
                .findFirst()
                .orElse(null);
    }



    public void updateJob(Job newJob) {
        log.info("Updating job " + newJob);

        ObjectMapper objectMapper = new ObjectMapper();
        String tagJson = null;

        try {
            tagJson = objectMapper.writeValueAsString(newJob.getTags());
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder updateQuery = new StringBuilder("UPDATE jobs SET ");

        List<Object> params = new ArrayList<>();

        if (newJob.getTitle() != null) {
            updateQuery.append("title = ?, ");
            params.add(newJob.getTitle());
        }

        if (newJob.getDescription() != null) {
            updateQuery.append("description = ?, ");
            params.add(newJob.getDescription());
        }

        if (newJob.getCompanyName() != null) {
            updateQuery.append("company_name = ?, ");
            params.add(newJob.getCompanyName());
        }

        if (newJob.getLocation() != null) {
            updateQuery.append("location = ?, ");
            params.add(newJob.getLocation());
        }

        if (newJob.getJobType() != null) {
            updateQuery.append("job_type = ?, ");
            params.add(newJob.getJobType());
        }

        if (newJob.getSalaryRange() != null) {
            updateQuery.append("salary_range = ?, ");
            params.add(newJob.getSalaryRange());
        }

        if (tagJson != null) {
            updateQuery.append("tags = ?::jsonb, ");
            params.add(tagJson);
        }

        if (newJob.getRequirements() != null) {
            updateQuery.append("requirements = ?, ");
            params.add(newJob.getRequirements());
        }

        if (newJob.getUpdatedAt() != null) {
            updateQuery.append("updated_at = ?, ");
            params.add(newJob.getUpdatedAt());
        }

        updateQuery.setLength(updateQuery.length() - 2);
        updateQuery.append(" WHERE id = ?");

        params.add(newJob.getId());

        // Execute the update SQL query
        jdbcClient.sql(updateQuery.toString())
                .params(params.toArray())
                .update();
    }
    
    
    public void updateJobStatus(Job newJob)
    {
        log.info("Updating job " + newJob);
        StringBuilder updateQuery = new StringBuilder("UPDATE jobs SET ");
        List<Object> params = new ArrayList<>();

        updateQuery.append("is_active = ?, ");
        params.add(newJob.getIsActive());

        updateQuery.setLength(updateQuery.length() - 2);
        updateQuery.append(" WHERE id = ?");

        params.add(newJob.getId());

        // Execute the update SQL query
        jdbcClient.sql(updateQuery.toString())
                .params(params.toArray())
                .update();
    }

        public void CreateJob(Job newJob) {
        ObjectMapper objectMapper = new ObjectMapper();
        String tagJson = null;

        try {
            tagJson = objectMapper.writeValueAsString(newJob.getTags());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Hash the password before storing

        jdbcClient.sql("INSERT INTO jobs (id, title, description, company_name, location, job_type, salary_range, tags, requirements, created_at, updated_at, is_active) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?::jsonb, ?, COALESCE(?, CURRENT_TIMESTAMP), COALESCE(?, CURRENT_TIMESTAMP), ?)")
                .param(newJob.getId())
                .param(newJob.getTitle())
                .param(newJob.getDescription())
                .param(newJob.getCompanyName())
                .param(newJob.getLocation())
                .param(newJob.getJobType())
                .param(newJob.getSalaryRange())
                .param(tagJson)
                .param(newJob.getRequirements())
                .param(newJob.getCreatedAt())
                .param(newJob.getUpdatedAt())
                .param(newJob.getIsActive())
                .update();
    }

}
