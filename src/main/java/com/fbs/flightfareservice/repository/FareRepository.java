package com.fbs.flightfareservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fbs.flightfareservice.models.Fare;

@Repository
public interface FareRepository extends JpaRepository<Fare, Long>{

}
