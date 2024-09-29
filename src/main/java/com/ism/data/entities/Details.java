package com.ism.data.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name="detail")

public class Details {

    private double qteDette;

    @OneToOne
    @JoinColumn(name = "detteId", referencedColumnName = "id")
    @ToString.Exclude
    private Dette dette;

    @OneToOne
    @JoinColumn(name = "articleId", referencedColumnName = "id")
    @ToString.Exclude
    private Article article;

}
