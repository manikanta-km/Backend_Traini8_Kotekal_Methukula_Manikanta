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

    // Method to add a new training center
    public TrainingCenter addNewCenter(TrainingCenter newCenter) {
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

    // Method to check if a training center exists by its center code
    public boolean existsByCenterCode(String code){
        for(TrainingCenter center : trainingCenterRepo.findAll()){
            if(center.getCenterCode().equals(code)){
                return true;
            }
        }
        return false;
    }

    // Method to retrieve all training centers
    public List<TrainingCenter> getAllCenters(){
        return trainingCenterRepo.findAll();
    }


    // Method to retrieve a training center by its ID
    public TrainingCenter getCenter(Integer centerId){
        TrainingCenter center = trainingCenterRepo.findById(centerId).orElseThrow();
        return center;
    }

    // Method to update courses offered by a training center
    public String updateCourses(Integer centerId, List<String> newCourses) {
       TrainingCenter center = getCenter(centerId);
        center.getCoursesOffered().addAll(newCourses);
        trainingCenterRepo.save(center);
        return "Courses updated successfully";
    }

    // Method to update contact email of a training center
    public String updateEmail(Integer centerId, String newEmail) {
        TrainingCenter center = getCenter(centerId);
        center.setContactEmail(newEmail);
        trainingCenterRepo.save(center);
        return  "Contact email updated successfully";
    }

    // Method to update contact phone number of a training center
    public String updateContactPhone(Integer centerId, String newContactPhone) {
        TrainingCenter center = getCenter(centerId);
        center.setContactPhone(newContactPhone);
        trainingCenterRepo.save(center);
        return "Contact phone number updated successfully";
    }

    // Method to update address of a training center
    public String updateAddress(Integer centerId, Address address) {
        TrainingCenter center = getCenter(centerId);
        center.setCenterAddress(address);
        trainingCenterRepo.save(center);
        return "Center address updated successfully";
    }

    // Method to retrieve a training center by its center code
    public TrainingCenter getCenterByCode(String centerCode) {
        for(TrainingCenter center : trainingCenterRepo.findAll()){
            if(center.getCenterCode().equals(centerCode)){
                return center;
            }
        }
        throw new IllegalStateException("No training center found with code: " + centerCode);
    }

    // Method to retrieve training centers in a particular state
    public List<TrainingCenter> getCentersByState(String state) {
        List<TrainingCenter> centers = new ArrayList<>();
        for(TrainingCenter center : trainingCenterRepo.findAll()){
            if(center.getCenterAddress().getState().equals(state)){
                centers.add(center);
            }
        }
        return centers;
    }

    // Method to retrieve training centers in a particular city
    public List<TrainingCenter> getCentersByCity(String city) {
        List<TrainingCenter> centers = new ArrayList<>();
        for(TrainingCenter center : trainingCenterRepo.findAll()){
            if(center.getCenterAddress().getCity().equals(city)){
                centers.add(center);
            }
        }
        return centers;
    }

    // Method to retrieve training centers by student capacity
    public List<TrainingCenter> getCentersByStudentCapacity(Integer capacity) {
        List<TrainingCenter> centers = new ArrayList<>();
        for(TrainingCenter center : trainingCenterRepo.findAll()){
            if(center.getStudentCapacity().equals(capacity)){
                centers.add(center);
            }
        }
        return centers;
    }

    // Method to retrieve training centers founded between two dates
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

    // Method to retrieve training centers within a capacity range
    public List<TrainingCenter> getCentersWithCapacityInRange(Integer minCapacity, Integer maxCapacity) {
        List<TrainingCenter> centers = new ArrayList<>();
        for(TrainingCenter center : trainingCenterRepo.findAll()){
            if(center.getStudentCapacity() >= minCapacity && center.getStudentCapacity() <= maxCapacity){
                centers.add(center);
            }
        }
        return centers;
    }

    // Method to delete a training center by its center code
    public String deleteCenter(String centerCode) {
        if(existsByCenterCode(centerCode)){
            TrainingCenter center = getCenterByCode(centerCode);
            Integer centerId = center.getCenterId();
            trainingCenterRepo.deleteById(centerId);
            return "Training Center with the center code " + centerCode + " has been deleted";
        }
        else
            return "Training Center with the center code " + centerCode + " does not exists";
    }

    // Method to retrieve a training center by its center name
    public TrainingCenter getCenterByName(String centerName) {
        for(TrainingCenter center : trainingCenterRepo.findAll()){
            if(center.getCenterName().equals(centerName)){
                return center;
            }
        }
        throw new IllegalStateException("No training center found with name " + centerName);
    }

    // Method to retrieve training centers offering a specific courses
    public List<TrainingCenter> getCentersByCourse(List<String> courses) {
        List<TrainingCenter> centersWithCourse = new ArrayList<>();
        for (TrainingCenter center : trainingCenterRepo.findAll()) {
            if (center.getCoursesOffered().containsAll(courses)) {
                centersWithCourse.add(center);
            }
        }
        return centersWithCourse;
    }

    // Method to retrieve training centers by multiple criteria
    public List<TrainingCenter> getCentersByMoreThanOneCriteria(String centerName, String city, String state, Integer capacity, List<String> courses) {
        List<TrainingCenter> centers = new ArrayList<>();
        for(TrainingCenter center : trainingCenterRepo.findAll()){
            if((city == null || center.getCenterAddress().getCity().equals(city)) &&
                    (capacity == null || center.getStudentCapacity().equals(capacity))  &&
                    (centerName == null || center.getCenterName().equals(centerName)) &&
                    (state == null || center.getCenterAddress().getState().equals(state)) &&
                    (courses == null || center.getCoursesOffered().containsAll(courses))){
                centers.add(center);
            }
        }
        return centers;
    }
}
