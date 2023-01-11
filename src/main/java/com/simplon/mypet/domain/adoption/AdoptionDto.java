package com.simplon.mypet.domain.adoption;

import com.simplon.mypet.db.entity.Adoption;
import com.simplon.mypet.db.entity.Animal;

import java.io.Serializable;

/**
 * A DTO for the {@link com.simplon.mypet.db.entity.Adoption} entity
 */
public record AdoptionDto(String title, String description, String city, Double price,
                          Animal animal) implements Serializable {

    public Adoption toAdoption() {
        Adoption adoption = new Adoption();
        adoption.setTitle(this.title);
        adoption.setDescription(this.description);
        adoption.setCity(this.city);
        adoption.setPrice(this.price);
        adoption.setAnimal(this.animal);
        return adoption;
    }
}