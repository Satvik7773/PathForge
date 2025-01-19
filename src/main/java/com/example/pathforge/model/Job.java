package com.example.pathforge.model;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

// Define a record for the Job class
public record Job(
        UUID id,
        String title,
        String description,
        String company_name,
        String location,
        String jobType,
        String salaryRange,
        Map<String,Object> tags,
        String requirements,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        boolean is_active
) {

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCompanyName() {
        return company_name;
    }

    public String getLocation() {
        return location;
    }

    public String getJobType() {
        return jobType;
    }

    public String getSalaryRange() {
        return salaryRange;
    }

    public Map<String, Object> getTags() {
        return tags;
    }

    public String getRequirements() {
        return requirements;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public boolean getIsActive() {
        return is_active;
    }
}
