package com.Spring.EMailSender.Responses;

import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonInclude(value = Include.NON_DEFAULT)
public class HttpResponse {
	
	protected int  StatusCode;
	protected HttpStatus status;
	protected String timeStamp;
	protected String message;
	protected String path;
	protected String requestMethod;
	protected Map<?,?> data;

	
}
