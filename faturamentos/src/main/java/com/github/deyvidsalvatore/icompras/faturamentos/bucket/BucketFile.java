package com.github.deyvidsalvatore.icompras.faturamentos.bucket;

import org.springframework.http.MediaType;

import java.io.InputStream;

public record BucketFile(
        String name,
        InputStream is,
        MediaType type,
        long size
) {
}
