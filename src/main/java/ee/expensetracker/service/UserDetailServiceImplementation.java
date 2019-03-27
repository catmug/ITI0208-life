package ee.expensetracker.service;

import ee.expensetracker.dao.UserDao;
import ee.expensetracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("userDetailsService")
public class UserDetailServiceImplementation implements UserDetailsService {

    @Autowired
    UserDao dao;

    @Autowired
    BCryptPasswordEncoder pw;

    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = dao.findUserByUsername(username);
        if(u == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new MyUserPrincipal(u, "USER");
    }
}

