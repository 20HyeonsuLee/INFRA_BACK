package com.example.demo.controller;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

@RestController
@Transactional(readOnly = true)
public class Test {

    private final MemberRepository memberRepository;

    public Test(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/infra-study/test")
    public String test() {
        List<Member> members = memberRepository.findAll();
        System.out.println("dsa: "+members.size());
        return members.stream().findFirst().get().getName();
    }
}
