package ru.iu3.backend.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ru.iu3.backend.models.Painting;
import ru.iu3.backend.repositories.PaintingRepository;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class PaintingController {
    @Autowired
    PaintingRepository paintingRepository;

    @GetMapping("/paintings")
    public List getAllPaintings() {
        return paintingRepository.findAll();
    }

    @PostMapping("/paintings")
    public ResponseEntity<Object> createPainting(@RequestBody Painting painting) throws Exception {
        try {
            Painting nc = paintingRepository.save(painting);
        return ResponseEntity.ok(nc);
        }
        catch(Exception ex) {
            String error;
            if (ex.getMessage().contains("not-null property references"))
                error = "Bad Request: name field is not specified.";
            else
                error = "Bad Request: Undefined error.";
            Map<String, String>
            map =  new HashMap<>();
            map.put("error", error);
            return ResponseEntity.ok(map);
        }
    }

    @PutMapping("/painting/{id}")
    public ResponseEntity<Painting> updatePainting(@PathVariable(value = "id") Long paintingId,
                            @RequestBody Painting paintingDetails) {
        Painting painting = null;
        Optional<Painting>
        pp = paintingRepository.findById(paintingId);
        if (pp.isPresent()) {
            painting = pp.get();
            painting.name = paintingDetails.name;
            paintingRepository.save(painting);
            return ResponseEntity.ok(painting);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "country not found");
        }
    }

    @DeleteMapping("/paintings/{id}")
    public ResponseEntity<Object> deleteMuseum(@PathVariable(value = "id") Long paintingId) {
        Optional<Painting>
        painting = paintingRepository.findById(paintingId);
        Map<String, Boolean>
        resp = new HashMap<>();
        if (painting.isPresent()) {
            paintingRepository.delete(painting.get());
            resp.put("deleted", Boolean.TRUE);
        }
        else
            resp.put("deleted", Boolean.FALSE);
        return ResponseEntity.ok(resp);
    }
}