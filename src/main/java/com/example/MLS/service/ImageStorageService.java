package com.example.MLS.service;

import com.example.MLS.entity.House;
import com.example.MLS.entity.Image;
import com.example.MLS.repository.ImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

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

    public String getBase64(byte[] byteData) {
        if(byteData!=null && byteData.length>0) {
            return Base64.getEncoder().encodeToString(byteData);
        }
        else
            return "";

    }

    public List<Image> orderImages(Set<Image> imageSet) {
        List<Image> ordered = new ArrayList<>();

        List<Image> images = new ArrayList<>();
        images.addAll(images);

        Collections.sort(images, Comparator.comparing(Image::getImageId));


        return images;
    }
}
