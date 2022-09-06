package com.fbs.flightfareservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.fbs.flightfareservice.controller.FlightFareController;
import com.fbs.flightfareservice.models.Fare;
import com.fbs.flightfareservice.repository.FareRepository;

@SpringBootTest(classes= FareServiceMockitoTests.class)
public class FareServiceMockitoTests {

	//mock the repo
	@Mock
	FareRepository fareRepo;
	
	//invoke methods from controller
	@InjectMocks
	FlightFareController flightFareController;
	
	public List<Fare> myFares; 
	
	@Test
	@Order(1)
	void testGetAllFare() {
		
		List<Fare> myFares= new ArrayList<Fare>();
	   myFares.add(new Fare(1L, 34L, "Harry", "77878979", "2027-01", 675, 5000));
	   myFares.add(new Fare(2L, 67L, "Fred", "76789787","2024-04", 761, 3500));
		
		when(fareRepo.findAll()).thenReturn(myFares); //mocking repository and returns our own data
		assertEquals(2,flightFareController.getAllFare().size());
		
	}

	@Test
	@Order(2)
	void testPostFare() {
		
		Fare fare= new Fare(3L, 90L, "George", "65434113", "2025-03", 211, 2000);
		
		//mock the original repository and add this above data 
		when(fareRepo.save(fare)).thenReturn(fare);
		assertEquals(fare, flightFareController.postFare(fare));
		
	}
	
	@Test
	@Order(3)
	void testUpdateFare() {
		
		Fare fare= new Fare(3L, 90L, "George", "65434113", "2025-03", 211, 2000);
		
		//mock the original repository and add this above data 
		when(fareRepo.save(fare)).thenReturn(fare);
		assertEquals(fare, flightFareController.updateFare(fare));
		
	}
}
