package academy.devdojo.springboot2.repository;

import academy.devdojo.springboot2.domain.DevDojoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @autor adriano rabello 15/07/2021 5:10 PM
 **/

@Repository
public interface DevDojoUserRepository extends JpaRepository<DevDojoUser, Long> {

    DevDojoUser findByUsername(String username);
}
