package com.makers.makersbnb.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "BOOKINGS")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email; // Email of the logged in user from Auth0

    @Column(name = "booking_date")
    private LocalDate bookingDate;

    @Column(name = "space_id")
    private Long spaceId;

    @ManyToOne
    @JoinColumn(name = "space_id", insertable = false, updatable = false)
    private Space space;

    public Booking(String email, LocalDate bookingDate, Long spaceId) {
        this.email = email;
        this.bookingDate = bookingDate;
        this.spaceId = spaceId;
    }
}
