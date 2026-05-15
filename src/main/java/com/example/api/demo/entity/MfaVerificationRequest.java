package com.example.api.demo.entity;

import lombok.Data;

@Data
public class MfaVerificationRequest {
    private String username;
    private String totp;
}
