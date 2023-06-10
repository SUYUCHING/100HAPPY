package com.aj.module.security.service;

import org.springframework.dao.DataAccessException;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UsernameNotFoundException;

public interface UserDetailsService extends org.springframework.security.userdetails.UserDetailsService
{

    public abstract UserDetails loadUserByUsername(String username)throws UsernameNotFoundException, DataAccessException;
}
