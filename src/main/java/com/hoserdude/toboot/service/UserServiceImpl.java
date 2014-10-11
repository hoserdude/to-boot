package com.hoserdude.toboot.service;

import com.hoserdude.toboot.domain.User;
import com.hoserdude.toboot.domainRepository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAttribute;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserDetails(OpenIDAuthenticationToken token) throws UsernameNotFoundException {
        final List<OpenIDAttribute> attributes = token.getAttributes();
        String emailAddress = null;
        String fullName = null;
        String firstName = null;
        String lastName = null;
        //Grab their name and email
        for (OpenIDAttribute attribute : attributes) {
            String name = attribute.getName();
            if (name.equals("email")) {
                if (attribute.getCount() == 1) {
                    emailAddress = attribute.getValues().get(0);
                }
            }
            if (name.equals("fullname")) {
                if (attribute.getCount() == 1) {
                    fullName = attribute.getValues().get(0);
                }
            }
            if (name.equals("firstname")) {
                if (attribute.getCount() == 1) {
                    firstName = attribute.getValues().get(0);
                }
            }
            if (name.equals("lastname")) {
                if (attribute.getCount() == 1) {
                    lastName = attribute.getValues().get(0);
                }
            }
        }
        if (emailAddress != null) {
            User user = userRepository.findByEmail(emailAddress);
            if (user == null) {
                //Create one
                logger.info("Creating new user account for {}", emailAddress);
                if (fullName == null) {
                    if (firstName != null && lastName != null) {
                        fullName = firstName + " " + lastName;
                    } else {
                        fullName = emailAddress;
                    }
                }
                user = new User((String)token.getPrincipal(), fullName, emailAddress);
                user = userRepository.save(user);
            } else {
                logger.info("User account exists for {}", emailAddress);
            }
            return user;
        } else {
            throw new UsernameNotFoundException("No Email found in Identity Exchange");
        }
    }
}
