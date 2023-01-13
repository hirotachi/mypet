package com.simplon.mypet.controller;

import com.simplon.mypet.domain.animal.AnimalDto;
import com.simplon.mypet.domain.animal.AnimalResponse;
import com.simplon.mypet.service.AnimalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/animals")
public class AnimalController {

    private final Logger LOGGER = LoggerFactory.getLogger(AnimalController.class);

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping
    public ResponseEntity<AnimalResponse> createAnimal(@RequestBody AnimalDto animalsDto) {
        LOGGER.info("Creating animal: {}", animalsDto);
        AnimalResponse createdAnimal = animalService.createAnimal(animalsDto);
        LOGGER.info("Created animal: {}", createdAnimal);
        return new ResponseEntity<>(createdAnimal, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<AnimalResponse>> getAllAnimals(Pageable pageable) {
        LOGGER.info("Retrieving all animals");
        Page<AnimalResponse> animals = animalService.getAllAnimals(pageable);
        LOGGER.info("Retrieved {} animals", animals.getTotalElements());
        return new ResponseEntity<>(animals, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalResponse> getAnimalById(@PathVariable Long id) {
        LOGGER.info("Retrieving animal with id: {}", id);
        AnimalResponse animal = animalService.getAnimalsById(id);
        LOGGER.info("Retrieved animal: {}", animal);
        return new ResponseEntity<>(animal, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalResponse> updateAnimal(@PathVariable Long id, @RequestBody AnimalDto animalsDto) {
        LOGGER.info("Updating animal with id: {}", id);
        AnimalResponse updatedAnimals = animalService.updateAnimals(id, animalsDto);
        LOGGER.info("Updated animal: {}", updatedAnimals);
        return new ResponseEntity<>(updatedAnimals, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable Long id) {
        LOGGER.info("Deleting animal with id: {}", id);
        animalService.deleteAnimals(id);
        LOGGER.info("Deleted animal with id: {}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

