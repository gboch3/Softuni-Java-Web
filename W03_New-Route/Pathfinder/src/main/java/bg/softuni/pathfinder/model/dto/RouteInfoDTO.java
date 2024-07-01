package bg.softuni.pathfinder.model.dto;

import bg.softuni.pathfinder.model.Comment;
import bg.softuni.pathfinder.model.Level;
import bg.softuni.pathfinder.model.Picture;
import bg.softuni.pathfinder.model.User;

import java.util.HashSet;
import java.util.Set;

public class RouteInfoDTO{
    private String name;
    private String description;
    private User author;
    private Level level;
    private String gpxCoordinates;
    private String videoUrl;
    private Set<Picture> pictures;
    private Set<Comment> comments;

    public RouteInfoDTO() {
        this.pictures = new HashSet<>();
        this.comments = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getGpxCoordinates() {
        return gpxCoordinates;
    }

    public void setGpxCoordinates(String gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
