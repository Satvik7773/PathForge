package com.example.pathforge.controller;

import com.example.pathforge.repo.JobRepo;
import com.example.pathforge.model.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private  JobRepo jobRepo;


    @GetMapping("/all-jobs")
    public List<Job> getAllJobs() {
        return jobRepo.findAll();
    }

    @GetMapping("/job-id/{id}")
    public Job getJobById(@PathVariable UUID id) {
        return jobRepo.findJobById(id);
    }



    @PutMapping("/update-job")
    public ResponseEntity<String> updateJob(@RequestBody Job newJob) {
        jobRepo.updateJobById(newJob.getId(), newJob);
        return new ResponseEntity<>("Job updated", HttpStatus.OK);
    }

    @PostMapping("/create-job")
    public ResponseEntity<String> createJob(@RequestBody Job newJob) {
        jobRepo.save(newJob);
        System.out.println("Inserting User: " + newJob);
        return ResponseEntity.status(HttpStatus.CREATED).body("Job Added Successfully");
    }

    @DeleteMapping("/delete-job/{id}")
    public void deleteJob(@PathVariable UUID id) {
        jobRepo.deleteById(id);
    }
}
