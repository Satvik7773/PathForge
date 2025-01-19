package com.example.pathforge.repo;

import com.example.pathforge.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Repository
public interface JobRepo extends JpaRepository<Job, UUID> {
    Job findJobById(UUID id);

    @Modifying
    @Transactional
    @Query("UPDATE Job j SET j = :j WHERE j.id = :id")
    void updateJobById(@Param(value = "id") UUID id, @Param(value = "j") Job newJob);

//    public List<Job> findAllJobs() {
//        return jdbcClient.sql("SELECT * FROM JOBS")
//                .query(jobRowMapper)
//                .list();
//    }
//
//    public Job findJobById(UUID id) {
//        return jdbcClient.sql("SELECT * FROM JOBS WHERE id = ?")
//                .param(1, id)  // Binding the UUID parameter
//                .query(jobRowMapper)
//                .stream()
//                .findFirst()
//                .orElse(null);
//    }
//
//    public void updateJob(Job newJob) {
//        log.info("Updating job " + newJob);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String tagJson = null;
//
//        try {
//            tagJson = objectMapper.writeValueAsString(newJob.getTags());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        StringBuilder updateQuery = new StringBuilder("UPDATE jobs SET ");
//
//        List<Object> params = new ArrayList<>();
//
//        if (newJob.getTitle() != null) {
//            updateQuery.append("title = ?, ");
//            params.add(newJob.getTitle());
//        }
//
//        if (newJob.getDescription() != null) {
//            updateQuery.append("description = ?, ");
//            params.add(newJob.getDescription());
//        }
//
//        if (newJob.getCompanyName() != null) {
//            updateQuery.append("company_name = ?, ");
//            params.add(newJob.getCompanyName());
//        }
//
//        if (newJob.getLocation() != null) {
//            updateQuery.append("location = ?, ");
//            params.add(newJob.getLocation());
//        }
//
//        if (newJob.getJobType() != null) {
//            updateQuery.append("job_type = ?, ");
//            params.add(newJob.getJobType());
//        }
//
//        if (newJob.getSalaryRange() != null) {
//            updateQuery.append("salary_range = ?, ");
//            params.add(newJob.getSalaryRange());
//        }
//
//        if (tagJson != null) {
//            updateQuery.append("tags = ?::jsonb, ");
//            params.add(tagJson);
//        }
//
//        if (newJob.getRequirements() != null) {
//            updateQuery.append("requirements = ?, ");
//            params.add(newJob.getRequirements());
//        }
//
//        if (newJob.getUpdatedAt() != null) {
//            updateQuery.append("updated_at = ?, ");
//            params.add(newJob.getUpdatedAt());
//        }
//
//        updateQuery.setLength(updateQuery.length() - 2);
//        updateQuery.append(" WHERE id = ?");
//
//        params.add(newJob.getId());
//
//        // Execute the update SQL query
//        jdbcClient.sql(updateQuery.toString())
//                .params(params.toArray())
//                .update();
//    }
//
//    public void updateJobStatus(Job newJob) {
//
//        StringBuilder updateQuery = new StringBuilder("UPDATE jobs SET ");
//        List<Object> params = new ArrayList<>();
//
//        updateQuery.append("is_active = ?, ");
//        params.add(newJob.getIsActive());
//
//        updateQuery.setLength(updateQuery.length() - 2);
//        updateQuery.append(" WHERE id = ?");
//
//        params.add(newJob.getId());
//
//        // Execute the update SQL query
//        jdbcClient.sql(updateQuery.toString())
//                .params(params.toArray())
//                .update();
//    }
//
//    public void CreateJob(Job newJob) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        String tagJson = null;
//
//        try {
//            tagJson = objectMapper.writeValueAsString(newJob.getTags());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // Hash the password before storing
//
//        jdbcClient.sql("INSERT INTO jobs (id, title, description, company_name, location, job_type, salary_range, tags, requirements, created_at, updated_at, is_active) " +
//                "VALUES (?, ?, ?, ?, ?, ?, ?, ?::jsonb, ?, COALESCE(?, CURRENT_TIMESTAMP), COALESCE(?, CURRENT_TIMESTAMP), ?)")
//                .param(newJob.getId())
//                .param(newJob.getTitle())
//                .param(newJob.getDescription())
//                .param(newJob.getCompanyName())
//                .param(newJob.getLocation())
//                .param(newJob.getJobType())
//                .param(newJob.getSalaryRange())
//                .param(tagJson)
//                .param(newJob.getRequirements())
//                .param(newJob.getCreatedAt())
//                .param(newJob.getUpdatedAt())
//                .param(newJob.getIsActive())
//                .update();
//    }

}
