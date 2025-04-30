package com.ratna.microservices.limitsservice.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Limits {
    private int minimum;
    private int maximum;
}
