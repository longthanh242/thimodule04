package com.ra.service;

import com.google.cloud.storage.*;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class UploadFileService {
    // Lấy Storage trong FirebaseConfig
    @Autowired
    private Storage storage;
    @Autowired
    private ServletContext servletContext;
    private final String BUCK_NAME = "exammodule04.appspot.com";

    // Upload ảnh lên local tomcat
    public String uploadFileToLocal(MultipartFile multipartFile) {
        //1. Tạo thư mục tạm uploads trong server tomcat
        // Tạo đường dẫn gốc đến file
        String pathUpload = servletContext.getRealPath("/");
        // Khai báo file = đường dẫn gốc + folder
        File uploadFolder = new File(pathUpload + "/uploads");
        // Nếu thư mục chưa tồn tại thì tạo
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }

        //2. Copy ảnh từ multipart sang thư mục uploads
        // Lấy ra tên file ảnh không kèm đuôi
        String fileName = multipartFile.getOriginalFilename();
        // fileUpload: đường dẫn foler + / + tên file ảnh
        // File.seperator = "/": có thể viết 1 trong 2
        File fileUpload = new File(uploadFolder + File.separator + fileName);
        try {
            FileCopyUtils.copy(multipartFile.getBytes(), fileUpload);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //3. Gọi phương thức uploadFileToFireBase và truyền đối số là đường dẫn ảnh trên tomcat
        return uploadFileToFireBase(uploadFolder + File.separator + fileName);
    }

    // Upload từ local lên firebase
    public String uploadFileToFireBase(String localFilePath) {
        Path localPath = Paths.get(localFilePath);
        // Lấy ra tên ảnh
        String fileName = localPath.getFileName().toString();
        // BUCK_NAME: Up lên bucket của firebase
        BlobId blobId = BlobId.of(BUCK_NAME, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        // Thiết lập quyền truy cập công cộng
        List<Acl> acls = new ArrayList<>();
        acls.add(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
        blobInfo = blobInfo.toBuilder().setAcl(acls).build();
        try {
            Blob blob = storage.create(blobInfo, Files.readAllBytes(localPath));
            return blob.getMediaLink();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
