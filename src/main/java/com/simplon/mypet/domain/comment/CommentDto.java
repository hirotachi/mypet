package com.simplon.mypet.domain.comment;

import com.simplon.mypet.db.entity.Adoption;
import com.simplon.mypet.db.entity.Comment;

import java.io.Serializable;

/**
 * A DTO for the {@link com.simplon.mypet.db.entity.Comment} entity
 */
public record CommentDto(String description, Adoption adoption) implements Serializable {

        public Comment toComment() {
            Comment comment = new Comment();
            comment.setDescription(this.description);
            comment.setAdoption(this.adoption);
            return comment;
        }
}