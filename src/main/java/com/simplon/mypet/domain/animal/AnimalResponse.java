package com.simplon.mypet.domain.animal;

import com.simplon.mypet.db.entity.Animal;

public record AnimalResponse(Long id, String name, String type, Integer age, Integer numberOfDays, String description,
                             byte[] photos) {

    public static AnimalResponse fromAnimal(Animal animal) {
        return new AnimalResponse(animal.getId(), animal.getName(), animal.getType(), animal.getAge(), animal.getNumberOfDays(), animal.getDescription(), animal.getPhotos());
    }

}
