package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
        return null;
    }
}
