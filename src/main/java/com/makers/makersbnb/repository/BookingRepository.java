package com.makers.makersbnb.repository;

import com.makers.makersbnb.model.Booking;
import org.springframework.data.repository.CrudRepository;


public interface BookingRepository extends CrudRepository<Booking, Long> {

}
