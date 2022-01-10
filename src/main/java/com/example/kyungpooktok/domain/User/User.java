package com.example.kyungpooktok.domain.User;


import com.example.kyungpooktok.domain.BaseTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 20,unique = true)
    private String username;

    @Column(nullable = false,length = 100)
    private String password;

    @Column(nullable = false,length = 50)
    private String email;

    @Column(nullable = false,length = 100)
    private String kakaotalkid;

    @Column(nullable = true,length = 100)
    private String instarid;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setKakaotalkid(String kakaotalkid){this.kakaotalkid=kakaotalkid;}
    public void setInstarid(String instarid){this.instarid=instarid;}
    /**
     * 권한 메소드
     */
    public String getRoleKey() {
        return this.role.getKey();
    }

    public void updatepass(String password){
        this.password=password;
    }
    public void updateid(String kakaotalkid,String instarid){
        this.kakaotalkid=kakaotalkid;
        this.instarid=instarid;
    }

}
