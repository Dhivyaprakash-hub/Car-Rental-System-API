package com.carrentalsystem.api;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.UUID;
public interface BookingRepository extends JpaRepository<Booking,UUID>{
 @Query("select (count(b)>0) from Booking b where b.vehicle.id=:vehicleId and b.status='CONFIRMED' and b.startDate<:endDate and b.endDate>:startDate")
 boolean hasOverlap(@Param("vehicleId") UUID vehicleId,@Param("startDate") LocalDate startDate,@Param("endDate") LocalDate endDate);
}
