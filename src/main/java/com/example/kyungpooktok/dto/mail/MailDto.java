package com.example.kyungpooktok.dto.mail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class MailDto {

    private String email;
    private String title;
    private String message;
    private String num;
}
