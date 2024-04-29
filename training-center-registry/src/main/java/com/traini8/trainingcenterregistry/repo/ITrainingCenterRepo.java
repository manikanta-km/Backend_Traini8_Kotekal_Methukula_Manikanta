package com.traini8.trainingcenterregistry.repo;

import com.traini8.trainingcenterregistry.model.TrainingCenter;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ITrainingCenterRepo extends JpaRepository<TrainingCenter,Integer> {
}
