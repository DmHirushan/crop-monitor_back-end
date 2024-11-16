package lk.ijse.crop_monitor.repository;

import lk.ijse.crop_monitor.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query
    Optional<User> findByEmail(String email);
}
