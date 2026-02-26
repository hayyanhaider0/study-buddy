package com.studybuddy.backend.entity.auth.embedded;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class VerificationCode {
    private final String code;
    private final Instant expiry;
}
