package com.excilys.cdb.service.impl;

import com.excilys.cdb.persistence.UserDao;
import com.excilys.cdb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The Class UserServiceImpl.
 */
@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    /**
     * The user dao.
     */
    @Autowired
    private UserDao userDao;

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.security.core.userdetails.UserDetailsService#
     * loadUserByUsername(java.lang.String)
     */
    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {
        final com.excilys.cdb.persistence.dto.UserJPA userJPA = userDao.findByName(username);
        final List<GrantedAuthority> authorities = buildUserAuthority(userJPA
                .getRole());
        return buildUserForAuthentication(userJPA, authorities);
    }

    /**
     * Builds the user for authentication.
     *
     * @param userJPA        the user
     * @param authorities the authorities
     * @return the user
     */
    private User buildUserForAuthentication(com.excilys.cdb.persistence.dto.UserJPA userJPA,
                                            List<GrantedAuthority> authorities) {
        return new User(userJPA.getUsername(), userJPA.getPassword(), true, true,
                true, true, authorities);
    }

    /**
     * Builds the user authority.
     *
     * @param role the role
     * @return the list
     */
    private List<GrantedAuthority> buildUserAuthority(int role) {

        final Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
        switch (role) {
            case 1:
                setAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                break;
            default:
                setAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        final List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(
                setAuths);
        return Result;
    }

}
