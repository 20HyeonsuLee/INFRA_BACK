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

import com.example.demo.S3Service;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

@RestController
@Transactional(readOnly = true)
public class Test {

    private final MemberRepository memberRepository;
    private final S3Service s3Service;

    public Test(
        MemberRepository memberRepository,
        S3Service s3Service
    ) {
        this.memberRepository = memberRepository;
        this.s3Service = s3Service;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
        @RequestParam("file") MultipartFile file
    ) {
        try {
            String fileUrl = s3Service.uploadFile(file);
            return ResponseEntity.ok(fileUrl);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
        }
    }

    @GetMapping("/download/{key}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String key) {
        byte[] file = s3Service.downloadFile("images/" + key);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // 이미지 타입에 맞게 MediaType을 설정
        headers.setContentLength(file.length);

        return new ResponseEntity<>(file, headers, HttpStatus.OK);
    }

    @GetMapping("/infra-study/test")
    public String test() {
        List<Member> members = memberRepository.findAll();
        return members.stream().findFirst().get().getName();
    }
}
