package com.mbc.entity;

import com.mbc.constant.Role;
import com.mbc.dto.MemberFormDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "member")
@Getter
@Setter
@ToString
public class Member extends BaseEntity {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;
    private String password;
    private String address;
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    //MemberFormDto -> Member로 변환
    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {

        Member member = new Member();

        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setPassword(passwordEncoder.encode(memberFormDto.getPassword()));
        member.setAddress(memberFormDto.getAddress());
        member.setPhone(memberFormDto.getPhone());
        member.setRole(Role.ADMIN);

        return member;
    }
}
