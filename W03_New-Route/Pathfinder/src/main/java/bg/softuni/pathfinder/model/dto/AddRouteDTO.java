package bg.softuni.pathfinder.model.dto;

import bg.softuni.pathfinder.model.CategoryType;
import bg.softuni.pathfinder.model.Level;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AddRouteDTO {
    private long id;

    @NotEmpty
    private String name;
    private String description;
    @NotNull
    private Level level;
    private String videoUrl;
    @NotNull
    private CategoryType categoryType;

    public AddRouteDTO() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }
}
