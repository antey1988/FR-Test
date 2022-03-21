package com.antey.fr.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter@Setter
public class Survey extends AbstractEntity {

    @Column(nullable = false, length = 50)
    private String name;
    private String description;
    @Column(nullable = false)
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    @BatchSize(size = 25)
    @JsonManagedReference
    private List<Question> questions;
}
