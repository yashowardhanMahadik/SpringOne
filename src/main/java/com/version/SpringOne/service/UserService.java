package com.version.SpringOne.service;

import com.version.SpringOne.Model.UserData;
import com.version.SpringOne.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        //Logic to get the user form the Databases
        Optional<UserData> user = userRepo.findUserByName(userName);
//            String username = user.get().getName();
//            String password = user.get().getPassword();
//        ArrayList<org.springframework.security.core.GrantedAuthority> authorityArrayList= new ArrayList<org.springframework.security.core.GrantedAuthority>();
//        authorityArrayList.add(new GrantedAuthority() {
//            @Override
//            public String getAuthority() {
//                return "admin";
//            }
//        });
            //return new User(username,password,authorityArrayList);
        CustomUserDetails customUserDetails = new CustomUserDetails(user.get());
        return customUserDetails;
    }

    public UserData createUser(UserData user) {
        UserData newUser = new UserData();
        newUser.setEmail(user.getEmail());
        newUser.setName(user.getName());
        newUser.setPassword(user.getPassword());
        newUser.setRole(user.getRole());

        return userRepo.save(newUser);

    }
}
