package com.example.kyungpooktok.dto.mail;


import lombok.*;

@Data
@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MailchekeDto {

    private String email;
    private String num;
}
