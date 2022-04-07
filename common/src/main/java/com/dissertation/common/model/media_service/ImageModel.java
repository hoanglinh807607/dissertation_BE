package com.dissertation.common.model.media_service;

import com.dissertation.common.entities.media_servcice.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageModel {
    private String id;
    private String roomId;
    private String url;
    private Boolean isPreview;
    private Boolean isDeleted;
    private Date createAt;
    private Date updateAt;

    public static ImageModel of(Image image) {
        if (image == null) {
            return null;
        }
        ImageModel imageModel = new ImageModel();
        imageModel.setId(image.getId());
        imageModel.setRoomId(image.getRoomId());
        imageModel.setUrl(image.getUrl());
        imageModel.setIsPreview(image.getIsPreview());
        imageModel.setIsDeleted(image.getIsDeleted());
        imageModel.setCreateAt(image.getCreateAt());
        imageModel.setUpdateAt(image.getUpdateAt());
        return imageModel;
    }
}
