package com.example.pathforge.run;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api") // This is for the base url
public class RunController {

    private final RunRepository runRepository;

    public RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @GetMapping("/runs")
    List<Run> findAll ()
    {
        return runRepository.findAll();
    }

    @GetMapping("/runs/{id}")
    List<Run> findById (@PathVariable("id") Integer id)
    {
        List<Run> run = runRepository.findById(id);
        if(run == null)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return runRepository.findById(id);
    }

    @GetMapping("/runs/{start}/{end}")
    List<Run> findByRangeMiles(@PathVariable("start") Integer startMiles, @PathVariable("end") Integer endMiles)
    {
        return runRepository.findByRangeMiles(startMiles,endMiles);
    }

    @PostMapping("/runs/create")
    public ResponseEntity<String> addRun(@RequestBody Run newRun) {
        runRepository.CreateRun(newRun); // Add the new run to the repository
        return ResponseEntity.status(HttpStatus.CREATED).body("Run added successfully!");
    }

    @RequestMapping("/TomCat") // This is the sub url for the method here
    public String home()
    {
        return "This is my string";
    }

    @RequestMapping("/check")
    public String Check()
    {
        return "This is Something Else";
    }
}
