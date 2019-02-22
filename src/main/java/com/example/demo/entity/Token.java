package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="token")
@Getter
@Setter
@ApiModel
@EqualsAndHashCode
public class Token implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long token_id;
    @UniqueElements
    private Long tokenNumber;

    private Integer numberOfEntries;

    @OneToMany(cascade = CascadeType.ALL,
    orphanRemoval = true)
    private List<BRD> brdList = new ArrayList<>();

    private int brdCount;

    public int getBrdCount() {
        return brdCount;
    }

    public void setBrdCount(int brdCount) {
        this.brdCount = brdCount;
    }
}