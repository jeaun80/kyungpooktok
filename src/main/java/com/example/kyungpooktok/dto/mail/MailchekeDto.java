package com.example.kyungpooktok.dto.mail;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MailchekeDto {

    private String email;
    private String num;
}
