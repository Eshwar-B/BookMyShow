CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE movies (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    description VARCHAR(1000),
    duration_minutes INT NOT NULL,
    language VARCHAR(50) NOT NULL,
    genre VARCHAR(80) NOT NULL,
    release_date DATE,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE theatres (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    city VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE screens (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    theatre_id BIGINT NOT NULL,
    name VARCHAR(80) NOT NULL,
    total_rows INT NOT NULL,
    total_seats_per_row INT NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_screens_theatre FOREIGN KEY (theatre_id) REFERENCES theatres(id)
);

CREATE TABLE shows (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    movie_id BIGINT NOT NULL,
    screen_id BIGINT NOT NULL,
    show_start_time TIMESTAMP NOT NULL,
    show_end_time TIMESTAMP NOT NULL,
    base_price DECIMAL(10,2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_shows_movie FOREIGN KEY (movie_id) REFERENCES movies(id),
    CONSTRAINT fk_shows_screen FOREIGN KEY (screen_id) REFERENCES screens(id)
);

CREATE TABLE seats (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    screen_id BIGINT NOT NULL,
    seat_row VARCHAR(10) NOT NULL,
    seat_number INT NOT NULL,
    seat_type VARCHAR(20) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uk_seat_screen_row_num UNIQUE (screen_id, seat_row, seat_number),
    CONSTRAINT fk_seats_screen FOREIGN KEY (screen_id) REFERENCES screens(id)
);

CREATE TABLE show_seat_inventory (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    seat_id BIGINT NOT NULL,
    show_id BIGINT NOT NULL,
    status VARCHAR(20) NOT NULL,
    lock_expires_at TIMESTAMP NULL,
    version BIGINT NOT NULL DEFAULT 0,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uk_show_seat UNIQUE (seat_id, show_id),
    CONSTRAINT fk_inventory_seat FOREIGN KEY (seat_id) REFERENCES seats(id),
    CONSTRAINT fk_inventory_show FOREIGN KEY (show_id) REFERENCES shows(id)
);

CREATE TABLE bookings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    show_id BIGINT NOT NULL,
    booking_reference VARCHAR(40) NOT NULL UNIQUE,
    booking_status VARCHAR(20) NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    booked_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_bookings_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_bookings_show FOREIGN KEY (show_id) REFERENCES shows(id)
);

CREATE TABLE booking_seats (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    booking_id BIGINT NOT NULL,
    seat_id BIGINT NOT NULL,
    seat_price DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uk_booking_seat UNIQUE (booking_id, seat_id),
    CONSTRAINT fk_booking_seats_booking FOREIGN KEY (booking_id) REFERENCES bookings(id),
    CONSTRAINT fk_booking_seats_seat FOREIGN KEY (seat_id) REFERENCES seats(id)
);

CREATE TABLE payments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    booking_id BIGINT NOT NULL UNIQUE,
    payment_method VARCHAR(30) NOT NULL,
    payment_status VARCHAR(20) NOT NULL,
    transaction_reference VARCHAR(80),
    amount DECIMAL(10,2) NOT NULL,
    paid_at TIMESTAMP NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_payments_booking FOREIGN KEY (booking_id) REFERENCES bookings(id)
);
