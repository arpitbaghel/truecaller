package com.example.truecaller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.truecaller.service.UserContactService;

@RestController
public class UserContactController {

	@Autowired
	private UserContactService userContactSerivce;

}
