package ru.iu3.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.iu3.backend.models.Artist;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    public List findAll();

    public Artist save(Artist artist);

    public Optional<Artist> findById(Long artistId);

}