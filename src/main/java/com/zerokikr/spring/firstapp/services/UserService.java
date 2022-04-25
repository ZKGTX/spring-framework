package com.zerokikr.spring.firstapp.services;

import com.zerokikr.spring.firstapp.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

public interface UserService extends UserDetailsService { // необходимо унаследоваться от UserDetailsService, который Spring использует для получения инфы о Users
   User findByUsername (String username);
}
