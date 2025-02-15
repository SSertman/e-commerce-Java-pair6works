package com.etiya.ecommercedemopair6.repository.abstracts;

import com.etiya.ecommercedemopair6.entities.concretes.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color, Integer> {
    boolean existsByColorName(String colorName);
}
