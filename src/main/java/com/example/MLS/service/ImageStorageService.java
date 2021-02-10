package com.example.MLS.service;

import com.example.MLS.entity.Image;
import com.example.MLS.repository.ImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ImageStorageService {

    @Autowired
    ImagesRepository imageRepository;

    public Image getImage(MultipartFile file) {
        String imgName = file.getOriginalFilename();
        try {
            Image image = new Image(imgName, file.getContentType(), file.getBytes());
            return image;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Optional<Image> getImage(Long fileId) {
        return imageRepository.findById(fileId);
    }
    public List<Image> getImages(){
        return imageRepository.findAll();
    }

}
