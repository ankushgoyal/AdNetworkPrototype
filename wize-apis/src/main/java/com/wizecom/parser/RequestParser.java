package com.wizecom.parser;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import com.wizecom.model.RequestContext;

@Service
public class RequestParser {
	
	public RequestContext getContext(HttpEntity<byte[]> requestEntity) {
		return new RequestContext();
	}

}
