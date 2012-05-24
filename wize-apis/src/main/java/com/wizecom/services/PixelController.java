package com.wizecom.services;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

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
public class PixelController {
	
	private static final Logger logger = LoggerFactory.getLogger(PixelController.class);
	
	@Resource
	private RequestParser requestParser;
	
	@Resource
	private VisitorIdService visitorIdSvc;
	
	@Resource 
	private BuyerIdService buyerIdSvc;
	
	/**
	 * This is the client facing request handler for firing pixels.
	 * On page load: our embedded code on client's page will make a call to this service.
	 * This service needs to:
	 * <ul>
	 * <li>Send the request headers to RequestParser (TODO: STUB).</li>
	 * <li>Based on cookie info, find out if its a new user or old user.</li>
	 * <li>For an old user we reuse the Buyer-Id, and generate a Visitor-Id by making a call to Visitor-Id Service. (TODO: Stub)</li>
	 * <li>For a new user we generate a new Buyer-Id and Visitor-Id, by calling Buyer-Id and Visitor-Id Services. (TODO: Stub)</li>
	 * <li>Take the required params from request header parsing, and send to Model/ Hypothesis to get back an answer to whether fire a pixel or not.</li>
	 * <li>Create appropriate Response Headers.</li>
	 * <li>Return back with response headers and fire pixels if model returned back a yes, else no.</li>
	 * <li>Create 2 threads to do logging and adding information to datastore.</li>
	 * </ul>
	 * @param requestEntity
	 * @return
	 */
	@RequestMapping(value = "/getPixels", method = RequestMethod.GET)
	public HttpEntity<String> getPixels(HttpEntity<byte[]> requestEntity) {
		RequestContext reqContext = requestParser.getContext(requestEntity);
		ResponseContext respContext = new ResponseContext();
		
		if (isExistingUser(reqContext)) {
			//Set the existing buyer-id in response context
		} else {
			//set buyerIdSvc.getNewBuyerId() in respContext
		}
		//set visitorIdSvc.getNewVisitorId() in respContext
		
		String pixels = "";
		if (true) {//Make a call to model/ hypotheses
			pixels = "<html>Add a html div with img tags corresponding to ad network pixels</html>";
		}
		
		HttpEntity<String> response = new ResponseEntity<String>(pixels, generateResponseHeaders(respContext), HttpStatus.OK);
		
		//Do logging and datastore adding in separate threads.
		
		return response;
				
	}
	
	//TODO: STUB!
	private boolean isExistingUser(RequestContext reqContext) {
		return true;
	}
	
	private HttpHeaders generateResponseHeaders(ResponseContext respContext) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("DummyProperty", "IamAdummyValue");
		return responseHeaders;
	}

}
