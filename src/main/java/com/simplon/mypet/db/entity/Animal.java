package com.simplon.mypet.db.entity;

import com.simplon.mypet.domain.animal.AnimalResponse;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "animals")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "number_of_days", nullable = false)
    private Integer numberOfDays;

    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "photos")
    private byte[] photos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL)
    private List<Adoption> adoptions;
    //getters and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getPhotos() {
        return photos;
    }

    public void setPhotos(byte[] photos) {
        this.photos = photos;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Adoption> getAdoptions() {
        return adoptions;
    }

    public void setAdoptions(List<Adoption> adoptions) {
        this.adoptions = adoptions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AnimalResponse toAnimalResponse() {
        return AnimalResponse.fromAnimal(this);
    }
}
