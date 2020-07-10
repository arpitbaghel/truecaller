package com.example.truecaller.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.truecaller.bean.AuthenticationRequest;
import com.example.truecaller.bean.AuthenticationResponse;
import com.example.truecaller.bean.SpamMarkBean;
import com.example.truecaller.bean.UserBean;
import com.example.truecaller.bean.UserSignupRequestBean;
import com.example.truecaller.entity.UserEntity;
import com.example.truecaller.service.UserService;
import com.example.truecaller.utils.JwtUtil;
import com.example.truecaller.validator.UserValidator;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserValidator validator;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/login")
	public AuthenticationResponse login(@RequestBody AuthenticationRequest request) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect Username Or Password ", e);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		final String token = jwtUtil.generateToken(userDetails);
		AuthenticationResponse response = new AuthenticationResponse(token);
		return response;
	}

	@PostMapping("/signup")
	public Long signUp(@RequestBody @Valid UserSignupRequestBean request, Errors errors) {
		validator.validateOnSave(request, errors);
		if (errors.hasErrors()) {
			throw new ValidationException((Throwable) errors.getAllErrors());
		}
		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(request, entity);
		return userService.registerUser(entity).getId();
	}

	@PutMapping("/user/mark-spam")
	public void markSpam(@RequestBody @Valid SpamMarkBean spam, Errors errors) {
		validator.validateOnSpamMark(spam.getMobile(), errors);
		if (errors.hasErrors()) {
			throw new ValidationException((Throwable) errors.getAllErrors());
		}
		userService.markSpam(spam.getMobile());
	}

	@GetMapping("/user/search")
	public List<UserBean> search(@RequestParam(required = false) String name, @RequestParam(required = false) String mobile) {
		return userService.search(name, mobile);
	}

	@GetMapping("/user/details")
	public UserBean showUserDetails(@RequestParam(required = true) String mobile) {
		return userService.showUserDetails(mobile);
	}

}
