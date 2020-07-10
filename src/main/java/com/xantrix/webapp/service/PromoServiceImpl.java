package com.xantrix.webapp.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xantrix.webapp.entities.DettPromo;
import com.xantrix.webapp.entities.Promo;
import com.xantrix.webapp.repository.PromoRepository;

@Service
@Transactional(readOnly = true)
public class PromoServiceImpl implements PromoService {

	@Autowired
	private PromoRepository promoRepository;
	
	@Override
	public List<Promo> selTutti() {
		return promoRepository.findAll();
	}
	
	@Override
	@Cacheable(value = "promo_cache",key = "#IdPromo",sync = true)
	public Promo selByIdPromo(String IdPromo) {
		Promo promo = promoRepository.findByIdPromo(IdPromo);
/*
		 if (promo != null)
		 {
			 List<DettPromo> PromoRows = promo.getDettPromo()
			 .stream()
			 .sorted(Comparator.comparing(DettPromo::getRiga))
			 .collect(Collectors.toList());

			 promo.setDettPromo(PromoRows);
		 }
*/
		 return promo;
	}

	@Override
	@Transactional
	@Caching(evict = { 
		@CacheEvict(cacheNames="przpromo_cache", allEntries = true),
		@CacheEvict(cacheNames="promo_cache",key = "#promo.idPromo")})
	public void insPromo(Promo promo) {
		promoRepository.saveAndFlush(promo);

	}

	@Override
	@Transactional
	@Caching(evict = { 
		@CacheEvict(cacheNames="przpromo_cache", allEntries = true),
		@CacheEvict(cacheNames="promo_cache",key = "#promo.idPromo")})
	public void delPromo(Promo promo) {
		promoRepository.delete(promo);

	}

	

}
