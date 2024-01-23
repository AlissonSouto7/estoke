package com.bfu.email.application.service;

import com.bfu.email.domain.dto.EmailRequest;
import com.bfu.email.domain.dto.EmailResponse;

import jakarta.validation.Valid;

public interface EmailService {
	EmailResponse sendEmail(@Valid EmailRequest request);
}
