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

    @PostMapping("trainingCenter")
    public TrainingCenter addCenter(@Valid @RequestBody TrainingCenter newCenter)
    {
        return trainingCenterService.addNewCenter(newCenter);
    }

    @GetMapping("centers")
    public List<TrainingCenter> getAllCenters(){
        return trainingCenterService.getAllCenters();
    }

    @PutMapping("newCourses")
    public String updateCourses(@RequestParam Integer centerId, @RequestParam List<String> newCourses){
        return trainingCenterService.updateCourses(centerId,newCourses);
    }
    @PutMapping("contactEmail")
    public String updateEmail(@RequestParam Integer centerId, @RequestParam String newEmail){
        return trainingCenterService.updateEmail(centerId,newEmail);
    }
    @PutMapping("contactPhone")
    public String updatePhoneNumber(@RequestParam Integer centerId, @RequestParam String newContactPhone){
        return trainingCenterService.updateContactPhone(centerId,newContactPhone);
    }

    @PutMapping("address")
    public String updateAddress(@RequestParam Integer centerId, @RequestBody Address address){
        return trainingCenterService.updateAddress(centerId,address);
    }

    @GetMapping("centerByCenterCode")
    public TrainingCenter getCenterByCode(@RequestParam String centerCode){
        return trainingCenterService.getCenterByCode(centerCode);
    }

    @GetMapping("centerByCenterName")
    public TrainingCenter getCenterByName(@RequestParam String centerName){
        return trainingCenterService.getCenterByName(centerName);
    }

    @GetMapping("centersInAState")
    public List<TrainingCenter> getCentersByState(@RequestParam String state){
        return trainingCenterService.getCentersByState(state);
    }

    @GetMapping("centersInACity")
    public List<TrainingCenter> getCentersByCity(@RequestParam String city){
        return trainingCenterService.getCentersByCity(city);
    }

    @GetMapping("centersByCapacity")
    public List<TrainingCenter> getCentersByCapacity(@RequestParam Integer capacity){
        return trainingCenterService.getCentersByStudentCapacity(capacity);
    }

    @GetMapping("centersWithCapacityGreaterThan")
    public List<TrainingCenter> getCentersWithCapacityGreater(@RequestParam Integer capacity){
        return trainingCenterService.getCentersWithCapacityGreater(capacity);
    }

    @GetMapping("centersWithCapacityLessThan")
    public List<TrainingCenter> getCentersWithCapacityLess(@RequestParam Integer capacity){
        return trainingCenterService.getCentersWithCapacityLess(capacity);
    }

    @GetMapping("centersFoundedBetween")
    public List<TrainingCenter> getCentersFoundedBetween(@RequestParam LocalDateTime dateTime1, @RequestParam LocalDateTime dateTime2){
        return trainingCenterService.getCentersFoundedBetween(dateTime1,dateTime2);
    }

    @DeleteMapping("center")
    public String deleteCenter(@RequestParam String centerCode){
        return trainingCenterService.deleteCenter(centerCode);
    }

    @GetMapping("centersWithMultipleCriteria")
    public List<TrainingCenter> getCentersByCriteria(@RequestParam (required = false) String centerName, @RequestParam (required = false) String centerCode , @RequestParam (required = false) String city, @RequestParam (required = false) String state , @RequestParam(required = false) Integer capacity){
        return trainingCenterService.getCentersByMoreThanOneCriteria(centerName, centerCode, city, state, capacity);
    }

}
