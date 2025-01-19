package com.example.pathforge.controller;

import com.example.pathforge.repo.JobRepository;
import com.example.pathforge.model.Job;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobRepository jobRepository;

    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @GetMapping("/all-jobs")
    public List<Job> getAllJobs() {
        return jobRepository.findAllJobs();
    }

    @GetMapping("/job-id/{id}")
    public Job getJobById(@PathVariable UUID id) {
        return jobRepository.findJobById(id);
    }

    @PutMapping("/update-job-status")
    public ResponseEntity<String> updateJobStatus(@RequestBody Job newJob) {
        jobRepository.updateJobStatus(newJob);
        return ResponseEntity.status(HttpStatus.OK).body("Job status updated successfully");
    }

    @PutMapping("/update-job")
    public ResponseEntity<String> updateJob(@RequestBody Job newJob) {
        jobRepository.updateJob(newJob);
        return new ResponseEntity<>("Job updated", HttpStatus.OK);
    }

    @PostMapping("/create-job")
    public ResponseEntity<String> createJob(@RequestBody Job newJob) {
        jobRepository.CreateJob(newJob);
        System.out.println("Inserting User: " + newJob);
        return ResponseEntity.status(HttpStatus.CREATED).body("Job Added Successfully");
    }
}
