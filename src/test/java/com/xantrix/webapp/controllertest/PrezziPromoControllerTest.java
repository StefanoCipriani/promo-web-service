package com.xantrix.webapp.controllertest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.xantrix.webapp.PromoWebServiceApplication;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PromoWebServiceApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PrezziPromoControllerTest
{
    private MockMvc mockMvc;

	@Autowired
    private WebApplicationContext wac;
    
    @Before
	public void setup()
	{
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    private String JsonData =
    		"{\"idPromo\": \"481AD25F-ED20-40FA-B01F-B031B20EB47C\",    \r\n" + 
    		" \"anno\": 2018,    \r\n" + 
    		" \"codice\": \"TS01\",    \r\n" + 
    		" \"descrizione\": \"PROMO TEST 1\",\r\n" + 
    		" \"dettPromo\": [\r\n" + 
    		" \r\n" + 
    		"	{ \"id\": \"-1\", \r\n" + 
    		"	  \"riga\": 1,            \r\n" + 
    		"	  \"codart\": \"002000301\",            \r\n" + 
    		"	  \"codfid\": null,            \r\n" + 
    		"	  \"inizio\": \"2018-10-01\",            \r\n" + 
    		"	  \"fine\": \"2018-12-31\",            \r\n" + 
    		"	  \"oggetto\": \"0,29\",            \r\n" + 
    		"	  \"isfid\": \"Si\",            \r\n" + 
    		"	  \"tipoPromo\": {                \r\n" + 
    		"		\"descrizione\": \"TAGLIO PREZZO\",\r\n" + 
    		"		\"idTipoPromo\": \"1\"  }        \r\n" + 
    		"	},\r\n" + 
    		"	{ \"id\": \"-1\", \r\n" + 
    		"	  \"riga\": 2,            \r\n" + 
    		"	  \"codart\": \"000087101\",            \r\n" + 
    		"	  \"codfid\": null,            \r\n" + 
    		"	  \"inizio\": \"2018-10-01\",            \r\n" + 
    		"	  \"fine\": \"2018-12-31\",            \r\n" + 
    		"	  \"oggetto\": \"1,99\",            \r\n" + 
    		"	  \"isfid\": \"Si\",            \r\n" + 
    		"	  \"tipoPromo\": {                \r\n" + 
    		"		\"descrizione\": \"TAGLIO PREZZO\",\r\n" + 
    		"		\"idTipoPromo\": \"1\"  }        \r\n" + 
    		"	},\r\n" + 
    		"	{ \"id\": \"-1\", \r\n" + 
    		"	  \"riga\": 3,            \r\n" + 
    		"	  \"codart\": \"007288701\",            \r\n" + 
    		"	  \"codfid\": null,            \r\n" + 
    		"	  \"inizio\": \"2018-10-01\",            \r\n" + 
    		"	  \"fine\": \"2018-12-31\",            \r\n" + 
    		"	  \"oggetto\": \"3,29\",            \r\n" + 
    		"	  \"isfid\": \"Si\",            \r\n" + 
    		"	  \"tipoPromo\": {                \r\n" + 
    		"		\"descrizione\": \"TAGLIO PREZZO\",\r\n" + 
    		"		\"idTipoPromo\": \"1\"  }        \r\n" + 
    		"	},\r\n" + 
    		"	{ \"id\": \"-1\", \r\n" + 
    		"	  \"riga\": 4,            \r\n" + 
    		"	  \"codart\": \"002000301\",            \r\n" + 
    		"	  \"codfid\": 67000028,            \r\n" + 
    		"	  \"inizio\": \"2018-10-01\",            \r\n" + 
    		"	  \"fine\": \"2018-12-31\",            \r\n" + 
    		"	  \"oggetto\": \"0,27\",            \r\n" + 
    		"	  \"isfid\": \"Si\",            \r\n" + 
    		"	  \"tipoPromo\": {                \r\n" + 
    		"		\"descrizione\": \"TAGLIO PREZZO\",\r\n" + 
    		"		\"idTipoPromo\": \"1\"  }        \r\n" + 
    		"	},\r\n" + 
    		"	{ \"id\": \"-1\", \r\n" + 
    		"	  \"riga\": 5,            \r\n" + 
    		"	  \"codart\": \"000087101\",            \r\n" + 
    		"	  \"codfid\": null,            \r\n" + 
    		"	  \"inizio\": \"2018-09-01\",            \r\n" + 
    		"	  \"fine\": \"2018-12-31\",            \r\n" + 
    		"	  \"oggetto\": \"1,89\",            \r\n" + 
    		"	  \"isfid\": \"Si\",            \r\n" + 
    		"	  \"tipoPromo\": {                \r\n" + 
    		"		\"descrizione\": \"TAGLIO PREZZO\",\r\n" + 
    		"		\"idTipoPromo\": \"1\"  }        \r\n" + 
    		"	}],\r\n" + 
    		"	\"depRifPromo\": [\r\n" + 
    		"		{\r\n" + 
    		"			\"id\": 4,\r\n" + 
    		"            \"idDeposito\": 525\r\n" + 
    		"        }\r\n" + 
    		"	]\r\n" + 
    		"}";

    @Test
	public void A_TestInsPromo() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.post("/promo/inserisci").contentType(MediaType.APPLICATION_JSON)
				.content(JsonData)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andDo(print());
	}
    
    @Test
	public void B_GetPrzCodArtPromoTest() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/prezzo/promo/codice/002000301")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$").value("0.29")) 
			.andReturn();
    }

    @Test
	public void C_GetPrzCodArtPromoTest2() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/prezzo/promo/codice/000087101")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$").value("1.89")) 
			.andReturn();
    }

    @Test
	public void D_GetPrzFidelityPromoTest() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/prezzo/promo/fidelity/67000028/002000301")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$").value("0.27")) 
			.andReturn();
    }
    
    @Test
	public void E_GetPrzCodArtNoPromoTest() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/prezzo/promo/codice/000087102")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$").doesNotExist()) 
			.andReturn();
    }

    @Test
	public void F_GetPrzFidelityNoPromoTest() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/prezzo/promo/fidelity/67000029/002000301")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$").doesNotExist()) 
			.andReturn();
    }
    
    @Test
	public void G_deletePromo() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.delete("/promo/elimina/481AD25F-ED20-40FA-B01F-B031B20EB47C")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andDo(print());
	}
}