package com.example.kyungpooktok.controller.api;

import com.example.kyungpooktok.config.Email.EmailServie;
import com.example.kyungpooktok.dto.mail.MailDto;
import com.example.kyungpooktok.dto.mail.MailchekeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import com.example.kyungpooktok.api.response.CommonResponse;

@RestController
@RequiredArgsConstructor
@Log4j2
public class MailApiContoroller {
    private final EmailServie mailService;

    @PostMapping("/auth/mailduplicate")
    public ResponseEntity<CommonResponse> mailduplicate(@RequestBody MailDto mailDto){
        if(mailService.EmailDuplicate(mailDto)){
            return new ResponseEntity<>(CommonResponse.builder().msg("OK").build(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(CommonResponse.builder().msg("faild").build(), HttpStatus.BAD_REQUEST);

        }

    }
    @PostMapping("/auth/mail")
    public ResponseEntity<CommonResponse> execMail(@RequestBody MailDto mailDto) {

        mailService.EmailDuplicate(mailDto);
        mailService.mailSend(mailDto);

        return new ResponseEntity<>(CommonResponse.builder().msg("OK").build(), HttpStatus.OK);
    }

    @PostMapping("/auth/checkmail")
    public ResponseEntity<CommonResponse> check(@RequestBody MailchekeDto mailchekeDto) {
        if (mailService.checkNum(mailchekeDto)) {
            return new ResponseEntity<>(CommonResponse.builder().msg("OK").build(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(CommonResponse.builder().msg("failed").build(), HttpStatus.BAD_REQUEST);
        }
    }

}
