package ua.training.springtaxreports.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ua.training.springtaxreports.entity.Role;
import ua.training.springtaxreports.entity.User;
import ua.training.springtaxreports.repository.RoleRepository;
import ua.training.springtaxreports.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username)
    			throws UsernameNotFoundException
    {    	
    	Optional<User> optionalUser = Optional.of(
    					userRepository.findByUsername( username ) );

		if ( !optionalUser.isPresent() ) {
			throw new UsernameNotFoundException( "User not found" );
		}

		return optionalUser.get();
    }

    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public List<User> usergtList(Long idMin) {
        return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }
    
	public static String getCurrentUserId() {
		User user = (User) SecurityContextHolder.getContext()
												.getAuthentication()
												.getPrincipal();
	    return user.getUsername();		
	}
	
	public String getController( String prevController ) {
		String		controller = null;
		List<User> listUsers = userRepository.findAll();
		for ( User user : listUsers ) {
			Set<Role> setRoles = user.getRoles();
			for ( Role role : setRoles )
				if ( role.getName().equals( "ROLE_ADMIN" ) &&
						!user.getUsername().equals(prevController) ) {
					controller = user.getUsername();
					return controller;
				}

		}
		return controller;
	}
	
	public static boolean userHasRole( String role )
	{
		for ( GrantedAuthority authority : SecurityContextHolder.getContext()
					.getAuthentication().getAuthorities()) {
	        if ( role.equals( authority.getAuthority() ) )
	        	return true;
		}

	    return false;
	}
}