package com.github.deyvidsalvatore.icompras.faturamentos.api;

import com.github.deyvidsalvatore.icompras.faturamentos.bucket.BucketFile;
import com.github.deyvidsalvatore.icompras.faturamentos.bucket.BucketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Objects;

@RestController
@RequestMapping("/bucket")
@RequiredArgsConstructor
public class BucketController {

    private final BucketService service;

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file) {
        try(InputStream is = file.getInputStream()) {
            MediaType type = MediaType.parseMediaType(Objects.requireNonNull(file.getContentType()));
            BucketFile bucketFile = new BucketFile(file.getOriginalFilename(), is, type, file.getSize());
            this.service.upload(bucketFile);
            return ResponseEntity.status(HttpStatus.CREATED).body("Arquivo criado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao enviar o arquivo: " + e.getMessage());
        }
    }
}
