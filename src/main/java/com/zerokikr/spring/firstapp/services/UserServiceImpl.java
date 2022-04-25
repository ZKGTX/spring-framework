package com.zerokikr.spring.firstapp.services;

import com.zerokikr.spring.firstapp.entities.Role;
import com.zerokikr.spring.firstapp.entities.User;
import com.zerokikr.spring.firstapp.repositories.RoleRepository;
import com.zerokikr.spring.firstapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public User findByUsername(String username) {
        return userRepository.findOneByUsername(username);
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // реализация данного метода необходима для преобразования сущности User к формату, с которой работает Spring Security
       User user = userRepository.findOneByUsername(username);
       if (user == null) {
           throw new UsernameNotFoundException("Invalid username or password");
       }
       return new org.springframework.security.core.userdetails.User(
               user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles())
       );
    }

    private Collection <? extends GrantedAuthority> mapRolesToAuthorities (Collection <Role> roles) { // метод преобразует список ролей в коллекцию понимаемых Spring ролей
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
