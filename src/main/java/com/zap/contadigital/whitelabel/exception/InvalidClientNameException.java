package com.zap.contadigital.whitelabel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Name has less than 3 or more than 255 characters")
public class InvalidClientNameException extends RuntimeException{

}
