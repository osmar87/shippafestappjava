package br.com.gomesdev87.shippafestApi.config;

import br.com.gomesdev87.shippafestApi.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthConfig implements UserDetailsService {

    private final UserRepository repository;

    public AuthConfig(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        return repository.findUserByEmail(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuário não encontrado: " + username));
    }
}