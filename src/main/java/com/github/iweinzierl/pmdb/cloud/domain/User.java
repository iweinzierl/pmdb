package com.github.iweinzierl.pmdb.cloud.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {

    private String name;
    private String email;
    private String profileImage;
}
