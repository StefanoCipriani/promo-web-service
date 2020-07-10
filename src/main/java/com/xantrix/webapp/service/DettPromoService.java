package com.xantrix.webapp.service;

import java.util.List;

import com.xantrix.webapp.entities.DettPromo;

public interface DettPromoService {

	List<DettPromo> selDettPromoByCodFid(String CodFid);
	List<DettPromo> selDettPromoByCode(String Codice);
}
