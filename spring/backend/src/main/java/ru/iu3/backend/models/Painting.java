package ru.iu3.backend.models;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "paintings")
@Access(AccessType.FIELD)
public class Painting {
    public Painting() {}

    public Painting(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    public long id;

    @Column(name = "name", nullable = false, unique = true)
    public String name;

    @Column(name = "year")
    public long year;

    @ManyToOne()
    @JoinColumn(name = "artistid")
    public Artist artist;

    @ManyToOne()
    @JoinColumn(name = "museumid")
    public Museum museum;
}