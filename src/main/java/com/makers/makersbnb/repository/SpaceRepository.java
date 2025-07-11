package com.makers.makersbnb.repository;

import java.time.LocalDate;
import java.util.List;
import com.makers.makersbnb.model.Space;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SpaceRepository extends CrudRepository<Space, Long> {
    public List<Space> findSpaceByName(String name);

    @Query("SELECT s FROM Space s WHERE :searchDate NOT IN (SELECT bd FROM s.bookedDates bd)")
    List<Space> findSpacesAvailableOnDate(@Param("searchDate") LocalDate searchDate);
}

// Methods
//// create a new record
//Space treehouseSpace = new Space("treehouse");
//spaceRepository.save(treehouseSpace)
//
//// retrieve all records from the spaces table
//spaceRepository.findAll()
//
//// find a record by its primary key (id)
//spaceRepository.findById(id);
//
//// delete a record (space is an instance of Space)
//spaceRepository.delete(space)
