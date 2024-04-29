package com.traini8.trainingcenterregistry.controller;

import com.traini8.trainingcenterregistry.model.Address;
import com.traini8.trainingcenterregistry.model.TrainingCenter;
import com.traini8.trainingcenterregistry.service.TrainingCenterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class TrainingCenterController {
    @Autowired
    TrainingCenterService trainingCenterService;

    // Endpoint to add a new training center
    @PostMapping("trainingCenter")
    public TrainingCenter addCenter(@Valid @RequestBody TrainingCenter newCenter)
    {
        return trainingCenterService.addNewCenter(newCenter);
    }

    // Endpoint to get all training centers
    @GetMapping("centers")
    public List<TrainingCenter> getAllCenters(){
        return trainingCenterService.getAllCenters();
    }

    // Endpoint to update courses offered by a training center
    @PutMapping("newCourses")
    public String updateCourses(@RequestParam Integer centerId, @RequestParam List<String> newCourses){
        return trainingCenterService.updateCourses(centerId,newCourses);
    }

    // Endpoint to update contact email of a training center
    @PutMapping("contactEmail")
    public String updateEmail(@RequestParam Integer centerId, @RequestParam String newEmail){
        return trainingCenterService.updateEmail(centerId,newEmail);
    }

    // Endpoint to update contact phone number of a training center
    @PutMapping("contactPhone")
    public String updatePhoneNumber(@RequestParam Integer centerId, @RequestParam String newContactPhone){
        return trainingCenterService.updateContactPhone(centerId,newContactPhone);
    }

    // Endpoint to update address of a training center
    @PutMapping("address")
    public String updateAddress(@RequestParam Integer centerId, @RequestBody Address address){
        return trainingCenterService.updateAddress(centerId,address);
    }

    // Endpoint to get a training center by its center code
    @GetMapping("centerByCenterCode")
    public TrainingCenter getCenterByCode(@RequestParam String centerCode){
        return trainingCenterService.getCenterByCode(centerCode);
    }

    // Endpoint to get a training center by its center name
    @GetMapping("centerByCenterName")
    public TrainingCenter getCenterByName(@RequestParam String centerName){
        return trainingCenterService.getCenterByName(centerName);
    }

    // Endpoint to get training centers in a particular state
    @GetMapping("centersInAState")
    public List<TrainingCenter> getCentersByState(@RequestParam String state){
        return trainingCenterService.getCentersByState(state);
    }

    // Endpoint to get training centers in a particular city
    @GetMapping("centersInACity")
    public List<TrainingCenter> getCentersByCity(@RequestParam String city){
        return trainingCenterService.getCentersByCity(city);
    }

    // Endpoint to get training centers by student capacity
    @GetMapping("centersByCapacity")
    public List<TrainingCenter> getCentersByCapacity(@RequestParam Integer capacity){
        return trainingCenterService.getCentersByStudentCapacity(capacity);
    }

    // Endpoint to get training centers within a capacity range
    @GetMapping("centersWithinCapacityRange")
    public List<TrainingCenter> getCentersWithCapacityInRange(@RequestParam Integer minCapacity, @RequestParam Integer maxCapacity){
        return trainingCenterService.getCentersWithCapacityInRange(minCapacity,maxCapacity);
    }

    // Endpoint to get training centers founded between two dates
    @GetMapping("centersFoundedBetween")
    public List<TrainingCenter> getCentersFoundedBetween(@RequestParam LocalDateTime dateTime1, @RequestParam LocalDateTime dateTime2){
        return trainingCenterService.getCentersFoundedBetween(dateTime1,dateTime2);
    }

    // Endpoint to delete a training center by its center code
    @DeleteMapping("center")
    public String deleteCenter(@RequestParam String centerCode){
        return trainingCenterService.deleteCenter(centerCode);
    }

    // Endpoint to get training centers by multiple criteria
    @GetMapping("centersWithMultipleCriteria")
    public List<TrainingCenter> getCentersByCriteria(@RequestParam (required = false) String centerName, @RequestParam (required = false) String centerCode , @RequestParam (required = false) String city, @RequestParam (required = false) String state , @RequestParam(required = false) Integer capacity){
        return trainingCenterService.getCentersByMoreThanOneCriteria(centerName, centerCode, city, state, capacity);
    }

}
