package org.melita.delegator;

import org.melita.api.AuthenticateApiDelegate;
import org.melita.domain.AuthenticateRequest;
import org.melita.domain.AuthenticateResponse;
import org.melita.service.MelitaUserDetailsService;
import org.melita.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateApiDelegateImpl implements AuthenticateApiDelegate {

    public static final Logger log = LoggerFactory.getLogger(AuthenticateApiDelegateImpl.class);
    private AuthenticationManager authenticationManager;
    private MelitaUserDetailsService userDetailsService;
    private JwtUtil jwtUtil;

    public AuthenticateApiDelegateImpl(AuthenticationManager authenticationManager,
                                       MelitaUserDetailsService userDetailsService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public ResponseEntity<AuthenticateResponse> authenticate(AuthenticateRequest authenticationRequest) {
        log.info("Authentication Request Received {}", authenticationRequest);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                authenticationRequest.getPassword()));
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok((new AuthenticateResponse()).jwt(jwt));
    }
}
