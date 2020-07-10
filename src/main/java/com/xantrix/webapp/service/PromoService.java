package com.xantrix.webapp.service;

import java.util.List;

import com.xantrix.webapp.entities.Promo;

public interface PromoService {
	
	public List<Promo> selTutti();
	public Promo selByIdPromo(String IdPromo);
	public void insPromo(Promo promo);
	public void delPromo(Promo promo);
}
