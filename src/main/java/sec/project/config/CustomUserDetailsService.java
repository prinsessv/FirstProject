package sec.project.config;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private Map<String, String> accountDetails;

    @PostConstruct
    public void init() {
        // this data would typically be retrieved from a database
         this.accountDetails = new TreeMap<>();
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode("password");
        this.accountDetails = new TreeMap<>();
        this.accountDetails.put("ted", password);
        this.accountDetails.put("matt", password);
        this.accountDetails.put("john", password);
        this.accountDetails.put("susan", password);
        this.accountDetails.put("ellie", password);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!this.accountDetails.containsKey(username)) {
            throw new UsernameNotFoundException("No such user: " + username);
            String role = "USER";
        if (username.equals("ted") || username.equals("john")) {
            role = "ADMIN";
        }
        
                return new org.springframework.security.core.userdetails.User(
                username,
                this.accountDetails.get(username),
                true,
                true,
                true,
                true,
                Arrays.asList(new SimpleGrantedAuthority("USER")));
    }
}
