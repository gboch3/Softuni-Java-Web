package bg.softuni.pathfinder.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "routes")
@Getter
@Setter
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "gpx_coordinates", columnDefinition = "LONGTEXT")
    private String gpxCoordinates;

    @Enumerated(EnumType.STRING)
    private Level level;

    @Column(name = "video_url")
    private String videoUrl;

    @ManyToOne(optional = false)
    private User author;

    @OneToMany(targetEntity = Comment.class, mappedBy = "route", fetch = FetchType.EAGER)
    private Set<Comment> comments;

    @OneToMany(targetEntity = Picture.class, mappedBy = "route",fetch = FetchType.EAGER)
    private Set<Picture> pictures;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Category> categories;

    public Route() {
        this.comments = new HashSet<>();
        this.pictures = new HashSet<>();
        this.categories = new HashSet<>();
    }

}
