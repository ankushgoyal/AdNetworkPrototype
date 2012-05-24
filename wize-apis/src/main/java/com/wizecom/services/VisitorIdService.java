package com.wizecom.services;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class VisitorIdService {
	
	//TODO: STUB!
	public String getNewVisitorId() {
		return new Date().toString();
	}

}
