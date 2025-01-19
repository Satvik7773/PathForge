package com.example.pathforge.service;

import com.example.pathforge.model.Job;
import com.example.pathforge.repo.JobRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
public class JobService {

    JobRepo repo;

    public List<Job> getAllJobs() {
        return repo.findAll();
    }

    public ResponseEntity<String> createJob(@RequestBody Job newJob) {
        repo.save(newJob);
        System.out.println("Inserting User: " + newJob);
        return ResponseEntity.status(HttpStatus.CREATED).body("Job Added Successfully");
    }

    public Job getJobById(@RequestBody UUID id) {
        return repo.findById(id).orElse(null);
    }

    public ResponseEntity<String> updateJob(@RequestBody Job newJob) {
        repo.updateJobById(newJob.getId(),newJob);
        return new ResponseEntity<>("Job updated", HttpStatus.OK);
    }

    public void deleteJob(@RequestBody UUID id) {
        repo.deleteById(id);
    }




}
