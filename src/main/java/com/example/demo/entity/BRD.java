package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="brd")
@Getter
@Setter
@ApiModel
@EqualsAndHashCode
public class BRD implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bid;

    private Long brdNum;
    private Long brdAmt;
    private Long brdResp;
}