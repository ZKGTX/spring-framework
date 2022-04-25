package com.zerokikr.spring.firstapp.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "products")
public class Product {

@Id
@GeneratedValue (strategy = GenerationType.IDENTITY)
@Column(name = "id")
private Long id;

@Column (name = "title")
private String title;

@Column (name = "price")
private int price;

@Column (name = "views")
private int views;



    @Override
    public String toString() {
        return title + " (просмотров: " + views + ")";
    }
}
