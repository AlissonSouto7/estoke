package com.bfu.email.domain.dto;

import java.util.UUID;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Value
public class EmailResponse {
	private UUID emailId;
}
