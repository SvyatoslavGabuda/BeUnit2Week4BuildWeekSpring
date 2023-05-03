package it.epicode.bw.auth.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.epicode.bw.auth.entity.ERole;
import it.epicode.bw.auth.entity.Role;
import it.epicode.bw.auth.exception.MyAPIException;
import it.epicode.bw.auth.payload.LoginDto;
import it.epicode.bw.auth.payload.RegisterDto;
import it.epicode.bw.auth.security.JwtTokenProvider;
import it.epicode.bw.models.Cliente;
import it.epicode.bw.repository.ClienteRepo;
import it.epicode.bw.repository.RoleRepository;

@Service
public class AuthServiceImpl implements AuthService{
    private AuthenticationManager authenticationManager;
    private ClienteRepo userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;


    public AuthServiceImpl(AuthenticationManager authenticationManager,
    		ClienteRepo userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(LoginDto loginDto) {
        
    	Authentication authentication = authenticationManager.authenticate(
        		new UsernamePasswordAuthenticationToken(
        				loginDto.getUsername(), loginDto.getPassword()
        		)
        ); 
    	
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public String register(RegisterDto registerDto) {

        // add check for username exists in database
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new MyAPIException(HttpStatus.BAD_REQUEST, "Username is already exists!.");
        }

        // add check for email exists in database
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new MyAPIException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
        }

        Cliente user = new Cliente();
        user.setNomeContatto(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setCognomeContatto(registerDto.getLastname());
        user.setPsw(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        
        //if(registerDto.getRoles() != null) {
	      //  registerDto.getRoles().forEach(role -> {
	      //  	Role userRole = roleRepository.findByRoleName(getRole(role)).get();
	     //   	roles.add(userRole);
	     //   });
      //  } else {
        	Role userRole = roleRepository.findByRoleName(ERole.ROLE_USER).get();
        	roles.add(userRole);
     //   }
        
        user.setRoles(roles);
        System.out.println(user);
        userRepository.save(user);

        return "User registered successfully!.";
    }
    
    public ERole getRole(String role) {
    	if(role.equals("ROLE_ADMIN")) return ERole.ROLE_ADMIN;
    	else if(role.equals("ROLE_MODERATOR")) return ERole.ROLE_MODERATOR;
    	else return ERole.ROLE_USER;
    }
//    public void changePermissions(String s,ERole roles) {
//        Set<Role> role = new HashSet<Role>();
//        role.add(roleRepository.findByRoleName(roles).get());
//        com.security.auth.entity.User u = userRepository.findByEmail(s).get();
//        u.setRoles(role);
//        userRepository.save(u);
//    }
    public Cliente getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            Cliente user = userRepository.findByUsername(username).get();
            return user;
        } else {
            //user non autenicato 
            return null;
        }
    }
}
