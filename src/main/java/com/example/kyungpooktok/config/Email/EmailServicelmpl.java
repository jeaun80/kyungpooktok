package com.example.kyungpooktok.config.Email;

import com.example.kyungpooktok.domain.User.User;
import com.example.kyungpooktok.domain.User.UserRepository;
import com.example.kyungpooktok.domain.mail.Mail;
import com.example.kyungpooktok.domain.mail.MailRepository;
import com.example.kyungpooktok.dto.mail.MailDto;
import com.example.kyungpooktok.dto.mail.MailchekeDto;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class EmailServicelmpl implements EmailServie{
    private final MailRepository repository;
    private final UserRepository userRepository;

    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "YOUR_EMAIL_ADDRESS";

    public String getAuthCode(int n) {
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        int num = 0;

        while(buffer.length() < n) {
            num = random.nextInt(10);
            buffer.append(num);
        }
        return buffer.toString();
    }
    //인증코드 난수 발생
    @Override
    public void setNum(String email, String num) {
        repository.save(Mail.builder().email(email).num(num).build());
    }

    @Override
    public void mailSend(MailDto mailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        String email = mailDto.getEmail();
        String authKey = getAuthCode(6);

        mailDto.setTitle("회원가입 인증번호 도착!");
        mailDto.setMessage("아래의 번호는 이메일 인증 번호입니다.\n" + authKey + "\n 인증번호를 입력해주세요!");

        message.setTo(email);
        message.setFrom(EmailServicelmpl.FROM_ADDRESS);
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());

        mailSender.send(message);
        setNum(email, authKey);

    }
    @Override
    public boolean EmailDuplicate(MailDto mailDto) {
       Optional<User> user=userRepository.findByEmail(mailDto.getEmail());
       if(user.isPresent()){
           return false;
       }
       else{
           return true;
       }
    }

    @Override
    public boolean checkNum(MailchekeDto mailchekeDto) {
        Optional<Mail> res = repository.findById(mailchekeDto.getEmail());
        if (res.isPresent()){
            Mail entity = res.get();
            if (entity.getNum().equals(mailchekeDto.getNum())) {
                repository.delete(entity);
                return true;
            }
        }
        return false;
    }

    @Override
    public void register(MailDto dto) {
        repository.save(dtoToEntity(dto)); //dto -> dao -> repository -> db
    }


    @Override
    public void delete(MailDto dto) {
        repository.delete(dtoToEntity(dto));
    }


}
