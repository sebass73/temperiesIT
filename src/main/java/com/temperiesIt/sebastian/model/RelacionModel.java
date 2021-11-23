package com.temperiesIt.sebastian.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "relacion")
public class RelacionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private Long id1;
    private Long id2;
    private String relacion;

    public RelacionModel(Long persona1, Long persona2, String relacion) {
        this.id1 = persona1;
        this.id2 = persona2;
        this.relacion = relacion;
    }

    public RelacionModel() {

    }
}
