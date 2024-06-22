package com.youngsquad.travel.presentation.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MateOnboardRequest extends OnboardRequest{
    private String code;
}
