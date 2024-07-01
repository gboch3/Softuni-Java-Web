package com.paintingscollectors.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "owner")
    private List<Painting> paintings;

    @ManyToMany
    private List<Painting> favouritePaintings;

    @ManyToMany
    private List<Painting> ratedPaintings;

    public User() {
        this.paintings = new ArrayList<>();
        this.favouritePaintings = new ArrayList<>();
        this.ratedPaintings = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Painting> getPaintings() {
        return paintings;
    }

    public void setPaintings(List<Painting> paintings) {
        this.paintings = paintings;
    }

    public List<Painting> getFavouritePaintings() {
        return favouritePaintings;
    }

    public void setFavouritePaintings(List<Painting> favouritePaintings) {
        this.favouritePaintings = favouritePaintings;
    }

    public List<Painting> getRatedPaintings() {
        return ratedPaintings;
    }

    public void setRatedPaintings(List<Painting> ratedPaintings) {
        this.ratedPaintings = ratedPaintings;
    }
}
