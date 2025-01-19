package com.example.pathforge.model;

import com.example.pathforge.converter.MapConverter;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
public class Job{

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        UUID id;
        String title;
        String description;
        String company_name;
        String location;
        String jobType;
        String salaryRange;
        @Convert(converter = MapConverter.class)
        Map<String,Object> tags;
        String requirements;
        LocalDateTime createdAt;
        LocalDateTime updatedAt;
        boolean isActive;



    public Job(UUID id, String title, String description, String company_name, String location, String jobType, String salaryRange, Map<String, Object> tags, String requirements, LocalDateTime createdAt, LocalDateTime updatedAt, boolean isActive) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.company_name = company_name;
        this.location = location;
        this.jobType = jobType;
        this.salaryRange = salaryRange;
        this.tags = tags;
        this.requirements = requirements;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isActive = isActive;
    }

    public Job() {

    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompanyName() {
        return company_name;
    }

    public void setCompanyName(String company_name) {
        this.company_name = company_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(String salaryRange) {
        this.salaryRange = salaryRange;
    }

    public Map<String, Object> getTags() {
        return tags;
    }

    public void setTags(Map<String, Object> tags) {
        this.tags = tags;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean is_active) {
        this.isActive = is_active;
    }
}
