package com.makers.makersbnb.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// @Entity - instances of this class map to database records
@Entity
@Getter @Setter @NoArgsConstructor
// @Table - those records can be found in the spaces table
@Table(name = "SPACES")
public class Space {
    @Id
    // the value of id is generated automatically
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // the following field (id) is the primary key for this Entity
    private Long id;

    // a second field that holds the name of each space
    private String name;
    private String description;
    private  BigDecimal price;
    private String rules;

    @ElementCollection
    @CollectionTable(name = "SPACE_BOOKED_DATES", joinColumns = @JoinColumn(name = "space_id"))
    @Column(name = "booked_date")
    private List<LocalDate> bookedDates = new ArrayList<>();

    public Space(String name, String description, BigDecimal price, String rules) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.rules = rules;
    }

    public List<LocalDate> getAvailableDates() {
        List<LocalDate> allDates = generateDateRange(LocalDate.now(), LocalDate.now().plusMonths(1));
        allDates.removeAll(bookedDates);
        return allDates;
    }

    private List<LocalDate> generateDateRange(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            dates.add(currentDate);
            currentDate = currentDate.plusDays(1);
        }
        return dates;
    }

    public boolean isDateAvailable(LocalDate date) {
        return !bookedDates.contains(date) &&
                !date.isBefore(LocalDate.now()) &&
                !date.isAfter(LocalDate.now().plusMonths(1));
    }

    public void bookDate(LocalDate date) {
        if (isDateAvailable(date)) {
            bookedDates.add(date);
        }
    }

    public void cancelBooking(LocalDate date) {
        bookedDates.remove(date);
    }
}
