package com.fbs.flightfareservice.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fbs.flightfareservice.config.MessagingConfig;
import com.fbs.flightfareservice.models.AuthenticationRequest;
import com.fbs.flightfareservice.models.AuthenticationResponse;
import com.fbs.flightfareservice.models.Fare;
import com.fbs.flightfareservice.models.PaymentStatus;
import com.fbs.flightfareservice.repository.FareRepository;
import com.fbs.flightfareservice.services.MyUserDetailsService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@RestController
public class FlightFareController {

	@Autowired
	private FareRepository fareRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private RabbitTemplate template;
	
	//get payment details
	@GetMapping("/fare")
	public List<Fare> getAllFare() {
		return fareRepository.findAll();
	}
	

	//enter details for payment
	@PostMapping("/fare/addfare")
	public Fare postFare(@RequestBody Fare fare)
	{
		return fareRepository.save(fare);
	}
	
	//update fare , can only be done by admin
	@PutMapping("/fare/updateFare")
	public Fare updateFare(@RequestBody Fare fare) {
		fareRepository.save(fare);
		return fare;
	}
	
	@GetMapping("/fare/{id}")
	public Optional<Fare> getByFlightId(@PathVariable("id") long id){
		return fareRepository.findById(id);
	}
	
	@PostMapping("/fare/farestatus")
    public String bookOrder(@RequestBody Fare fare) {
        fare.setId(UUID.randomUUID().node());
        //payment service
        PaymentStatus paymentStatus = new PaymentStatus(fare, "Process", "Payment successful");
        template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, paymentStatus);
        return "Success !!";
    }
	
	@PostMapping("/pay")
	@ResponseBody
	public String paymoneyyy(@RequestBody Map<String, Object> data, Principal principle) throws Exception {
		int amt=Integer.parseInt(data.get("amount").toString ());

		RazorpayClient razorpayClient = new RazorpayClient("rzp_test_fwH8eL6IVorLNF", "sBRegGyjrxNJ059rHOOSFLTk"); 
		JSONObject options = new JSONObject(); 
		options.put("amount", amt*100); 
		options.put("currency", "INR"); 
		options.put("receipt", "txn_123456"); 
		Order order = razorpayClient.orders.create(options);
		
		return order.toString();
	}
}

