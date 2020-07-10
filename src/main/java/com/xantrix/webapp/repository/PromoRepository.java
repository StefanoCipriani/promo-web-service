package com.xantrix.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xantrix.webapp.entities.Promo;

public interface PromoRepository extends JpaRepository<Promo, Long> {

	Promo findByIdPromo(String IdPromo);
}
