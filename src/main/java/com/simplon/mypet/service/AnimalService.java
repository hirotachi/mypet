package com.simplon.mypet.service;

import com.simplon.mypet.db.entity.Animal;
import com.simplon.mypet.db.repository.AnimalRepository;
import com.simplon.mypet.domain.animal.AnimalDto;
import com.simplon.mypet.domain.animal.AnimalResponse;
import com.simplon.mypet.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public AnimalResponse createAnimal(AnimalDto animalDto) {
        Animal animal = animalDto.toAnimal();
        animal = animalRepository.save(animal);
        return animal.toAnimalResponse();
    }

    public Page<AnimalResponse> getAllAnimals(Pageable pageable) {
        Page<Animal> animal = animalRepository.findAll(pageable);
        return animal.map(Animal::toAnimalResponse);
    }

    public AnimalResponse getAnimalsById(Long id) {
        Optional<Animal> optionalAnimals = animalRepository.findById(id);
        if (optionalAnimals.isEmpty()) {
            throw new NotFoundException("Animal with id '" + id + "' not found");
        }
        return optionalAnimals.get().toAnimalResponse();
    }

    public AnimalResponse updateAnimals(Long id, AnimalDto animalDto) {
        Animal animalUpdates = animalDto.toAnimal();
        Optional<Animal> optionalAnimals = animalRepository.findById(id);
        if (optionalAnimals.isEmpty()) {
            throw new NotFoundException("Animal with id '" + id + "' not found");
        }
        Animal animal = optionalAnimals.get();
        animal.setName(animalUpdates.getName());
        animal.setAge(animalUpdates.getAge());
        animal.setType(animalUpdates.getType());
        animal.setNumberOfDays(animalUpdates.getNumberOfDays());
        animal.setDescription(animalUpdates.getDescription());
        animal.setPhotos(animalUpdates.getPhotos());

        animal = animalRepository.save(animal);
        return animal.toAnimalResponse();
    }

    public void deleteAnimals(Long id) {
        if (!animalRepository.existsById(id)) {
            throw new NotFoundException("Animal with id '" + id + "' not found");
        }
        animalRepository.deleteById(id);
    }
}
