package com.zerokikr.spring.firstapp.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {

@Id
@GeneratedValue (strategy = GenerationType.IDENTITY)
@Column(name = "id")
private Long id;

@Column (name = "title")
private String title;

@Column (name = "price")
private Integer price;


}
