package security;

import data.SpitterRepository;
import entity.Spitter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class SpitterUserService implements UserDetailsService {
    private final SpitterRepository spitterRepository;
    public SpitterUserService(SpitterRepository spitterRepository) {
        this.spitterRepository = spitterRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) {
        try {
            Spitter spitterUser = spitterRepository.findByUsername(username);
            BCryptPasswordEncoder encoder = passwordEncoder();
            List<GrantedAuthority> authorities = null;
            if (spitterUser != null) {
                authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority("ROLE_SPITTER"));
            }
            return new User(spitterUser.getUsername(),
                    encoder.encode(spitterUser.getPassword()),
                    authorities);
        }catch (Exception repoProblem) {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }
    }
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
