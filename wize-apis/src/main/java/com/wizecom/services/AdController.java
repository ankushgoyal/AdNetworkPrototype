package com.wizecom.services;

import java.net.URI;
import java.net.URISyntaxException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wizecom.model.RequestContext;
import com.wizecom.model.ResponseContext;
import com.wizecom.parser.RequestParser;

@Controller
public class AdController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdController.class);
	
	@Resource
	private RequestParser requestParser;
	
	@Resource
	private VisitorIdService visitorIdSvc;
	
	@Resource 
	private BuyerIdService buyerIdSvc;
	
	@RequestMapping(value = "/getAd", method=RequestMethod.GET)
	public HttpEntity<String> getAd(HttpEntity<byte[]> requestEntity) throws URISyntaxException {
		RequestContext reqContext = requestParser.getContext(requestEntity);
		ResponseContext respContext = new ResponseContext();
		
		HttpHeaders responseHeaders = generateResponseHeaders(respContext);
		if (true) {//Make a call to model/ hypothesis to figure out which ad to serve.
			responseHeaders.setLocation(new URI("http://wizecom.cloudfoundry.com/resources/1cameras.jpg")); //TODO: Redirect to CDN with appropriate ad id.
		}
		
		HttpEntity<String> response = new ResponseEntity<String>("", responseHeaders, HttpStatus.MOVED_TEMPORARILY);
		
		//Do logging and datastore adding in separate threads.
		
		return response;
	}
	
	private HttpHeaders generateResponseHeaders(ResponseContext respContext) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("DummyProperty", "IamAdummyValue");
		return responseHeaders;
	}

}
