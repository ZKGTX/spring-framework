package com.zerokikr.spring.firstapp.repositories;

import com.zerokikr.spring.firstapp.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository <User, Long> {
    User findOneByUsername (String username);
}
