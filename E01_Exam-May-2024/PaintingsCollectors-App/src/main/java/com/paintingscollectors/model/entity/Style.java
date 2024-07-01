package com.paintingscollectors.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "styles")
public class Style {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private StyleName name;

    private String description;

    @OneToMany(mappedBy = "style")
    private List<Painting> paintings;

    public Style() {
        this.paintings = new ArrayList<>();
    }

    public Style(StyleName name, String description) {
        this();

        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StyleName getName() {
        return name;
    }

    public void setName(StyleName name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Painting> getPaintings() {
        return paintings;
    }

    public void setPaintings(List<Painting> paintings) {
        this.paintings = paintings;
    }
}
