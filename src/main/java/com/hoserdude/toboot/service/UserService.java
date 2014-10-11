package com.hoserdude.toboot.service;

import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.openid.OpenIDAuthenticationToken;

public interface UserService extends AuthenticationUserDetailsService<OpenIDAuthenticationToken> {
}
