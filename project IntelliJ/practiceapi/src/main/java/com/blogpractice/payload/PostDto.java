package com.blogpractice.payload;

import javax.validation.constraints.*;
import javax.validation.constraints.Size;

public class PostDto {

    private Long id;

    @NotEmpty
    @Size(min=2, message = "title Should be atleast 2 characters")
    private String title;

    @NotEmpty
    @Size(min=4, message = "description Should be atleast 4 characters")
    private String description;

    @NotEmpty
    @Size(min=4, message = "content Should be atleast 4 characters")
    private String content;

    private String message;

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
