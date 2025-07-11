CREATE TABLE space_booked_dates(
    space_id BIGINT NOT NULL,
    booked_date DATE NOT NULL,
    PRIMARY KEY (space_id, booked_date),
    FOREIGN KEY (space_id) REFERENCES spaces(id) ON DELETE CASCADE
);