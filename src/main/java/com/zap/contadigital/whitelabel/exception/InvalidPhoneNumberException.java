package com.zap.contadigital.whitelabel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid Phone number format")
public class InvalidPhoneNumberException extends RuntimeException{
}
