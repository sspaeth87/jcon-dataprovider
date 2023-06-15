package de.xdevsoftware.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class Customer {

    @Builder.Default
    private String id = UUID.randomUUID().toString();

    private String firstname;

    private String lastname;

    private String email;
}
