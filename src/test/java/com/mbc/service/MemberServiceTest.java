package com.mbc.service;

import com.mbc.dto.MemberFormDto;
import com.mbc.entity.Member;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Log4j2
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Member createMember() {

        MemberFormDto memberFormDto = MemberFormDto.builder()
                .email("test77@test77.com")
                .name("홍길동")
                .address("서울시 마포구 합정동")
                .password("1234")
                .phone("111-1111-1111")
                .build();

        return Member.createMember(memberFormDto, passwordEncoder);
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void saveMemberTest(){
        Member member = createMember();

        Member savedMember = memberService.saveMember(member);

        assertEquals(savedMember.getEmail(), member.getEmail());
        assertEquals(savedMember.getName(), member.getName());
        assertEquals(savedMember.getAddress(), member.getAddress());
        assertEquals(savedMember.getPhone(), member.getPhone());
        assertEquals(savedMember.getPassword(), member.getPassword());
        assertEquals(savedMember.getRole(), member.getRole());
    }

}