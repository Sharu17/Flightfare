package com.fbs.flightfareservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fbs.flightfareservice.models.Fare;
import com.fbs.flightfareservice.repository.FareRepository;

@SpringBootTest
class FlightFareServiceApplicationTests {

	@Autowired
	FareRepository fareRepository;
	
	@Test
	void testReadAll() {
		List<Fare> list= fareRepository.findAll();
		assertThat(list).size().isPositive();
	}
	
	@Test
    void testCreate() {
		Fare fare= new Fare();
		fare.setId(5L);
		fare.setBookingId(87L);
		fare.setName("Sharu");
		fare.setCreditCardNo("78725762");
		fare.setCvv(564);
		fare.setExpiryDate("2026-07");
		fare.setAmountOfFare(4560);
		fareRepository.save(fare);
		assertNotNull(fareRepository.findById(5L).get());
	}
}
