package com.simplon.mypet.domain.animal;

import com.simplon.mypet.db.entity.Animal;

import java.io.Serializable;

/**
 * A DTO for the {@link com.simplon.mypet.db.entity.Animal} entity
 */
public record AnimalDto(String type, Integer age, Integer numberOfDays, String description,
                        byte[] photos) implements Serializable {

    public Animal toAnimal() {
        Animal animal = new Animal();
        animal.setType(this.type);
        animal.setAge(this.age);
        animal.setNumberOfDays(this.numberOfDays);
        animal.setDescription(this.description);
        animal.setPhotos(this.photos);
        return animal;
    }
}