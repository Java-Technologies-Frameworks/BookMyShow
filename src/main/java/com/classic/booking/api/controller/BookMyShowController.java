package com.classic.booking.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.classic.booking.api.model.BookRequest;
import com.classic.booking.api.repository.BookMyShowRepository;



@RestController
@RequestMapping("BookMyShow/Service")
public class BookMyShowController {
	
	@Autowired
	private BookMyShowRepository repository;
	
	@PostMapping("/bookingShow")
	public String bookShow(@RequestBody BookRequest bookRequest)
	{
		BookRequest bookReq= repository.save(bookRequest);
		return ""+bookReq.getUserName()+"request for"+bookReq.getShowName()+"is successfully on date"+bookReq.getBookingTime();
	}
	
	@GetMapping("/getAllBooking")
	public java.util.List<BookRequest> getAllBooking()
	{
		return repository.findAll();
	}
	
	@GetMapping("/getBooking/{bookingId}")
	public BookRequest  getBooking(@PathVariable int bookingId)
	{
		return repository.findById(bookingId).get();
	}
	

	@DeleteMapping("/cancelBooking/{bookingId}")
	public String cancelBooking(@PathVariable int bookingId) {
		repository.deleteById(bookingId);
		return "Booking cancelled with bookingId : " + bookingId;
	}
	
	@PutMapping("/updateBooking/{bookingId}")
	public BookRequest updateBooking(@RequestBody BookRequest updateBookRequest, @PathVariable int bookingId) {
		BookRequest dbResponse = repository.findById(bookingId).get();
		dbResponse.setBookingTime(updateBookRequest.getBookingTime());
		dbResponse.setPrice(updateBookRequest.getPrice());
		dbResponse.setShowName(updateBookRequest.getShowName());
		dbResponse.setUserCount(updateBookRequest.getUserCount());
		repository.saveAndFlush(dbResponse);
		return dbResponse;
	}

}
