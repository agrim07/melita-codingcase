package org.melita.repository;

import org.melita.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends CrudRepository<User, String> {
}
