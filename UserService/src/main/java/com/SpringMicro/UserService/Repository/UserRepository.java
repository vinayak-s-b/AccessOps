package com.SpringMicro.UserService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SpringMicro.UserService.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User getUserById(long id);

}
