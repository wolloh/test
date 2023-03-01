package relex.repository;

import org.springframework.data.jpa.repository.Query;
import relex.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
      @Query(value = "select u from  User u where u.email= :email or u.username= :username")
      User checkEmailAndUserName(String email,String username);
}