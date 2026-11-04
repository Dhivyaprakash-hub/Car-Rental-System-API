package com.carrentalsystem.api;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import jakarta.persistence.LockModeType;
import java.util.UUID;
import java.util.Optional;
public interface VehicleRepository extends JpaRepository<Vehicle,UUID>{
 @Lock(LockModeType.PESSIMISTIC_WRITE) @Query("select v from Vehicle v where v.id=:id") Optional<Vehicle> lockById(@Param("id") UUID id);
}
