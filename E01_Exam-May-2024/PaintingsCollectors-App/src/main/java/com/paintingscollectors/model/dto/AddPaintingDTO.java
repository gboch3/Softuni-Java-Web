package com.paintingscollectors.model.dto;

import com.paintingscollectors.model.entity.Style;
import com.paintingscollectors.model.entity.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddPaintingDTO {

    @NotBlank
    @Size(min = 5, max = 40, message = "Name length must be between 5 and 40 characters!")
    private String name;

    @NotBlank
    @Size(min = 5, max = 30, message = "Author length must be between 5 and 30 characters!")
    private String author;

    @NotBlank(message = "Please enter valid image URL")
    @Size(max = 150)
    private String imageUrl;

    @NotNull(message = "You must select a style!")
    private Style style;

    public AddPaintingDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }
}
