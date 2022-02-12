package academy.devdojo.springboot2.service;

import academy.devdojo.springboot2.repository.DevDojoUserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @autor adriano rabello 15/07/2021 5:11 PM
 **/
@Service
public class DevDojoUserDetailService implements UserDetailsService {

    private DevDojoUserRepository devDojoUserRepository;

    public DevDojoUserDetailService(DevDojoUserRepository devDojoUserRepository) {
        this.devDojoUserRepository = devDojoUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(devDojoUserRepository.findByUsername(username)).orElseThrow(()-> new UsernameNotFoundException("Username not found"));
    }
}
