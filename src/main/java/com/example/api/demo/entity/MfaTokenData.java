package com.example.api.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MfaTokenData {
    private String qrCode;
    private String mfaCode;
}
