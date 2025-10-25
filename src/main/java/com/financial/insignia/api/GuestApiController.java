package com.financial.insignia.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.financial.insignia.data.entity.Guest;
import com.financial.insignia.data.repository.GuestRepository;

/**
 * API Controller
 * Allow to manage Guest information
 */
@RestController
@RequestMapping ("api/guest")
public class GuestApiController {
	
	private final GuestRepository guestRepository;

	public GuestApiController(GuestRepository guestRepository) {
		super();
		this.guestRepository = guestRepository;
	}
	
	/**
	 * Get all Guests
	 * @return
	 */
	@GetMapping
	public List<Guest> getAllGuests(){
		System.out.println("Get all guests");
		return this.guestRepository.findAll();
	}
	
	/**
	 * Get guest by ID
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/{id}")
	public Guest getGuestById (@PathVariable("id") long id) {
		System.out.println("Get guest by Id : " + id);
		return this.guestRepository.findById(id).get();
	}
	
	
	/**
	 * Allow add guest
	 * @param guest
	 * @return
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Guest addGuest(@RequestBody Guest guest) {
		System.out.println(" Create guest: " + guest.toString());
		return this.guestRepository.save(guest);
	}
	
	/**
	 * Allow update guest by ID
	 * @param id
	 * @param guest
	 * @return
	 */
	@PutMapping("/{id}")
	public Guest updateGuest (@PathVariable("id") long id, @RequestBody Guest guest) {
		return this.guestRepository.save(guest);
	}
	
	/**
	 * Delete guest by ID
	 * @param id
	 */
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.RESET_CONTENT)
	public void deleteGuest (@PathVariable("id") long id) {
		this.guestRepository.deleteById(id);
	}

}
