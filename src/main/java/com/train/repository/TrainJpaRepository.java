package com.train.repository;


import com.train.model.Train;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface TrainJpaRepository extends CrudRepository<Train, Integer> {

}
