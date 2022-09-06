package com.fbs.flightfareservice.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fbs.flightfareservice.models.Admin;
import com.fbs.flightfareservice.repository.AdminRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private AdminRepository adminRepository;
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<Admin> admin= adminRepository.findById(userName);
		if(admin.isPresent()){
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            Arrays.asList(admin.get().getRole().split(",")).stream().forEach(authority ->{
                authorities.add(new SimpleGrantedAuthority(authority));
            });
            return new User(admin.get().getUserName(), admin.get().getPassword(), authorities);
        }else {
            throw new UsernameNotFoundException("User " + userName + " does not exist...");
        }
		
	}

}
