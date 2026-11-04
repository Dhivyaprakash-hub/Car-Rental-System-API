CREATE TABLE vehicles (
 id UUID PRIMARY KEY,
 registration VARCHAR(12) NOT NULL UNIQUE,
 make VARCHAR(60) NOT NULL,
 model VARCHAR(80) NOT NULL,
 category VARCHAR(24) NOT NULL,
 seats INTEGER NOT NULL CHECK (seats BETWEEN 1 AND 12),
 daily_rate NUMERIC(10,2) NOT NULL CHECK (daily_rate > 0),
 active BOOLEAN NOT NULL DEFAULT TRUE
);
CREATE TABLE bookings (
 id UUID PRIMARY KEY,
 vehicle_id UUID NOT NULL REFERENCES vehicles(id),
 start_date DATE NOT NULL,
 end_date DATE NOT NULL,
 daily_rate NUMERIC(10,2) NOT NULL,
 total_price NUMERIC(12,2) NOT NULL,
 status VARCHAR(20) NOT NULL,
 CONSTRAINT ck_booking_dates CHECK (end_date > start_date)
);
CREATE INDEX idx_booking_vehicle_dates ON bookings(vehicle_id,start_date,end_date);
