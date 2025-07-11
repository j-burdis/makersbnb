ALTER TABLE bookings
ADD space_id int;

ALTER TABLE bookings
ADD foreign key(space_id) references spaces(id) on delete cascade;