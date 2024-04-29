package com.traini8.trainingcenterregistry.service;

import com.traini8.trainingcenterregistry.model.Address;
import com.traini8.trainingcenterregistry.model.TrainingCenter;
import com.traini8.trainingcenterregistry.repo.ITrainingCenterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrainingCenterService {
    @Autowired
    ITrainingCenterRepo trainingCenterRepo;

    public TrainingCenter addNewCenter(TrainingCenter newCenter) {
        String centerCode = newCenter.getCenterCode();
        // Check if the center code is null or empty
        if (centerCode == null || centerCode.isEmpty()) {
            throw new IllegalArgumentException("Center code is required!");
        }
        // Check if a training center with the given center code already exists
        if (existsByCenterCode(centerCode)) {
            throw new IllegalStateException("Training center with code " + centerCode + " already exists!");
        }
        try {
            // Populate the createdOn field with the server timestamp
            newCenter.setCreatedOn(LocalDateTime.now());
            // Save the new training center
            TrainingCenter savedCenter = trainingCenterRepo.save(newCenter);
            return savedCenter;
        } catch (DataAccessException ex) {
            // Handle database errors
            throw new RuntimeException("Failed to add training center. Please try again later.", ex);
        }
    }
    public boolean existsByCenterCode(String code){
        for(TrainingCenter center : trainingCenterRepo.findAll()){
            if(center.getCenterCode().equals(code)){
                return true;
            }
        }
        return false;
    }

    public List<TrainingCenter> getAllCenters(){
        return trainingCenterRepo.findAll();
    }

    public TrainingCenter getCenter(Integer centerId){
        TrainingCenter center = trainingCenterRepo.findById(centerId).orElseThrow();
        return center;
    }

    public String updateCourses(Integer centerId, List<String> newCourses) {
       TrainingCenter center = getCenter(centerId);
        center.getCoursesOffered().addAll(newCourses);
        trainingCenterRepo.save(center);
        return "Courses updated successfully";
    }

    public String updateEmail(Integer centerId, String newEmail) {
        TrainingCenter center = getCenter(centerId);
        center.setContactEmail(newEmail);
        trainingCenterRepo.save(center);
        return  "Contact email updated successfully";
    }


    public String updateContactPhone(Integer centerId, String newContactPhone) {
        TrainingCenter center = getCenter(centerId);
        center.setContactPhone(newContactPhone);
        trainingCenterRepo.save(center);
        return "Contact phone number updated successfully";
    }


    public String updateAddress(Integer centerId, Address address) {
        TrainingCenter center = getCenter(centerId);
        center.setCenterAddress(address);
        trainingCenterRepo.save(center);
        return "Center address updated successfully";
    }

    public TrainingCenter getCenterByCode(String centerCode) {
        for(TrainingCenter center : trainingCenterRepo.findAll()){
            if(center.getCenterCode().equals(centerCode)){
                return center;
            }
        }
        throw new IllegalStateException("No training center found with code: " + centerCode);
    }

    public List<TrainingCenter> getCentersByState(String state) {
        List<TrainingCenter> centers = new ArrayList<>();
        for(TrainingCenter center : trainingCenterRepo.findAll()){
            if(center.getCenterAddress().getState().equals(state)){
                centers.add(center);
            }
        }
        return centers;
    }

    public List<TrainingCenter> getCentersByCity(String city) {
        List<TrainingCenter> centers = new ArrayList<>();
        for(TrainingCenter center : trainingCenterRepo.findAll()){
            if(center.getCenterAddress().getCity().equals(city)){
                centers.add(center);
            }
        }
        return centers;
    }


    public List<TrainingCenter> getCentersByStudentCapacity(Integer capacity) {
        List<TrainingCenter> centers = new ArrayList<>();
        for(TrainingCenter center : trainingCenterRepo.findAll()){
            if(center.getStudentCapacity().equals(capacity)){
                centers.add(center);
            }
        }
        return centers;
    }

    public List<TrainingCenter> getCentersFoundedBetween(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        List<TrainingCenter> centers = new ArrayList<>();
        for(TrainingCenter center : trainingCenterRepo.findAll()){
            LocalDateTime currentCenter = center.getCreatedOn();
            if(currentCenter.isAfter(dateTime1) && currentCenter.isBefore(dateTime2)){
                centers.add(center);
            }
        }
        return centers;
    }


    public List<TrainingCenter> getCentersWithCapacityGreater(Integer capacity) {
        List<TrainingCenter> centers = new ArrayList<>();
        for(TrainingCenter center : trainingCenterRepo.findAll()){
            if(center.getStudentCapacity() > capacity){
                centers.add(center);
            }
        }
        return centers;
    }

    public List<TrainingCenter> getCentersWithCapacityLess(Integer capacity) {
        List<TrainingCenter> centers = new ArrayList<>();
        for(TrainingCenter center : trainingCenterRepo.findAll()){
            if(center.getStudentCapacity() < capacity){
                centers.add(center);
            }
        }
        return centers;
    }

    public String deleteCenter(String centerCode) {
        if(existsByCenterCode(centerCode))
            return "Training Center with the center code " + centerCode + " has been deleted";
        else
            return "Training Center with the center code " + centerCode + " does not exists";
    }

    public TrainingCenter getCenterByName(String centerName) {
        for(TrainingCenter center : trainingCenterRepo.findAll()){
            if(center.getCenterName().equals(centerName)){
                return center;
            }
        }
        throw new IllegalStateException("No training center found with name " + centerName);
    }


    public List<TrainingCenter> getCentersByMoreThanOneCriteria(String centerName, String centerCode, String city, String state, Integer capacity) {
        List<TrainingCenter> centers = new ArrayList<>();
        for(TrainingCenter center : trainingCenterRepo.findAll()){
            if((city == null || center.getCenterAddress().getCity().equals(city)) &&
                    (capacity == null || center.getStudentCapacity().equals(capacity))  &&
                    (centerName == null || center.getCenterName().equals(centerName)) &&
                    (centerCode == null || center.getCenterCode().equals(centerCode)) &&
                    (state == null || center.getCenterAddress().getState().equals(state) )){
                centers.add(center);
            }
        }
        return centers;
    }
}
