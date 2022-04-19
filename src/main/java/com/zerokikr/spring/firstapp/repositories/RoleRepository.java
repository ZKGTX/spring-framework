package com.zerokikr.spring.firstapp.repositories;

import com.zerokikr.spring.firstapp.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository <Role, Long> {
    Role findOneByName (String name);
}
