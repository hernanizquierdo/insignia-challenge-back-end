package com.financial.insignia.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.financial.insignia.data.entity.Allergies;
import com.financial.insignia.data.entity.Guest;
import com.financial.insignia.data.repository.AllergiesRepository;
import com.financial.insignia.data.repository.GuestRepository;

/**
 * API Controller
 * Allow to manage Allergies information
 */

@RestController
@RequestMapping ("api/allergies")
public class AllergieApiController {
	
	private final AllergiesRepository allergiesRepository;
	private final GuestRepository guestRepository;

	public AllergieApiController(AllergiesRepository allergiesRepository, GuestRepository guestRepository) {
		super();
		this.allergiesRepository = allergiesRepository;
		this.guestRepository = guestRepository;
	}
	
	
	/**
	 * Get all Allergies 
	 * @return
	 */
	@GetMapping
	public List<Allergies> getAllAllergies(){
		System.out.println("Get all allergies");
		List<Allergies> allergies =  this.allergiesRepository.findAll();
		allergies.forEach(g -> {
			System.out.println(g.getName() + " <> " + g.getGuest().getName());
		});
		return this.allergiesRepository.findAll();
	}
	
	/**
	 * Get all Allergies by guestId
	 * @param guestId
	 * @return
	 */
	@GetMapping(path = "/guest/{guestid}")
	public List<Allergies> getAllergiesByGuestId (@PathVariable("guestid") long guestId) {
		System.out.println("get Allergies by guestId : " + guestId);
		return this.allergiesRepository.findAllergiesByGuestId(guestId);
	}
	
	
	/**
	 * Add allergies to a Guest by ID
	 * @param guestId
	 * @param allergies
	 * @return
	 */
	@PostMapping (path = "/add/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Allergies addAllergie(@PathVariable("id") Long guestId,@RequestBody Allergies allergies) {
		System.out.println("GuestId : " + guestId+ " Create allergie: " + allergies.toString());
		Guest guest = this.guestRepository.findById(guestId).get();
		allergies.setGuest(guest);
		return this.allergiesRepository.save(allergies);
	}

}
