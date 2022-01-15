package com.example.kyungpooktok.config.Email;

import com.example.kyungpooktok.domain.mail.Mail;
import com.example.kyungpooktok.dto.mail.MailDto;
import com.example.kyungpooktok.dto.mail.MailchekeDto;

public interface EmailServie {
    void register(MailDto dto);
    void delete(MailDto dto);
    void setNum(String address, String num);
    void mailSend(MailDto mailDto);
    String getAuthCode(int n);
    boolean checkNum(MailchekeDto mailchekeDto);
    boolean EmailDuplicate(MailDto mailDto);

    default Mail dtoToEntity(MailDto dto) {
        Mail entity = Mail.builder()
                .email(dto.getEmail())
                .num(dto.getNum())
                .build();

        return entity;
    }

    default MailDto entityToDto(Mail entity){
        MailDto dto = MailDto.builder()
                .email(entity.getEmail())
                .num(entity.getNum())
                .build();
        return dto;
    }

}
