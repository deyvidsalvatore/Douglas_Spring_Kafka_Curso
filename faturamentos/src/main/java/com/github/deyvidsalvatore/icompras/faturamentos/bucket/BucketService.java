package com.github.deyvidsalvatore.icompras.faturamentos.bucket;

import com.github.deyvidsalvatore.icompras.faturamentos.config.props.MinioProps;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BucketService {

    private final MinioClient client;
    private final MinioProps props;

    public void upload(BucketFile file) {
        try {
            var object = PutObjectArgs.builder()
                    .bucket(props.getBucketName())
                    .object(file.name())
                    .stream(file.is(), file.size(), -1)
                    .contentType(file.type().toString())
                    .build();

            client.putObject(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getUrl(String file) {
        try {
            var object = GetPresignedObjectUrlArgs.builder()
                    .bucket(props.getBucketName())
                    .build();
            return "";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
