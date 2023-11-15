package com.youngsquad.common.s3;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.youngsquad.common.config.S3Config;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class S3Service {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final S3Config s3Config;

    public String upload(MultipartFile uploadFile, String folderName) throws IOException {

        log.info("upload folderName :: "+folderName);
        String originName = uploadFile.getOriginalFilename();
        String pathName = System.getProperty("user.dir") + '\\'+ "uploadFile\\";
        log.info("pathName :: "+pathName);
        File file = new File(pathName);
        uploadFile.transferTo(file);

        String s3key = folderName+getUuid();
        log.info("s3 key :: "+s3key);
        s3Config.amazonS3().putObject(new PutObjectRequest(bucket, s3key, file));
        file.delete();
        return s3key;
    }
    private String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    public String getDownloadPresignedURL(String s3Key){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 15);
        URL presignedUrl = s3Config.amazonS3().generatePresignedUrl(bucket, s3Key, cal.getTime());
        return presignedUrl.toString();
    }

    public String getUploadS3PresignedURL(String s3Key){
        GeneratePresignedUrlRequest generatePresignedUrlRequest = getGeneratePreSignedUrlRequest(bucket, s3Key);
        URL url = s3Config.amazonS3().generatePresignedUrl(generatePresignedUrlRequest);
        return url.toString();
    }

    private GeneratePresignedUrlRequest getGeneratePreSignedUrlRequest(String bucket, String fileName) {
        Calendar cal = Calendar.getInstance();
        //12시간동안 접근 허용
        cal.add(Calendar.DATE, 3);
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucket, fileName)
                        .withMethod(HttpMethod.PUT)
                        .withExpiration(cal.getTime());
        generatePresignedUrlRequest.addRequestParameter(
                Headers.S3_CANNED_ACL,
                CannedAccessControlList.PublicRead.toString());
        return generatePresignedUrlRequest;
    }

}
