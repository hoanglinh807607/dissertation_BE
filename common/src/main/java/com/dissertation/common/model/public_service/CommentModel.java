package com.dissertation.common.model.public_service;

import com.dissertation.common.entities.public_service.Comment;
import com.dissertation.common.model.user_service.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentModel {
    private String id;
    private String userId;
    private String homestayId;
    private String content;
    private Integer reviewScore;
    private Boolean isDeleted;
    private Date createAt;
    private UserModel userModel;

    public static CommentModel of(Comment comment) {
        if (comment == null) {
            return null;
        }
        CommentModel commentModel = new CommentModel();
        commentModel.setId(comment.getId());
        commentModel.setUserId(comment.getUserId());
        commentModel.setHomestayId(comment.getHomestayId());
        commentModel.setContent(comment.getContent());
        commentModel.setReviewScore(comment.getReviewScore());
        commentModel.setIsDeleted(comment.getIsDeleted());
        commentModel.setCreateAt(comment.getCreateAt());
        return commentModel;
    }
}
