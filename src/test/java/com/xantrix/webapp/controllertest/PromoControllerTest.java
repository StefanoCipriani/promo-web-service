package com.xantrix.webapp.controllertest;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
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
public class PromoControllerTest
{
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;
	
	@Before
	public void setup()
	{
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	String JsonData =  
			"{\"idPromo\": \"481AD25F-ED20-40FA-B01F-B031B20EB47C\",    \r\n" + 
			" \"anno\": 2018,    \r\n" + 
			" \"codice\": \"VL01\",    \r\n" + 
			" \"descrizione\": \"PROMO VOLANTINO 1\",\r\n" + 
			" \"dettPromo\": [\r\n" + 
			" \r\n" + 
			"	{ \"id\": \"-1\", \r\n" + 
			"	  \"riga\": 1,            \r\n" + 
			"	  \"codart\": \"002000301\",            \r\n" + 
			"	  \"codfid\": null,            \r\n" + 
			"	  \"inizio\": \"2018-10-01\",            \r\n" + 
			"	  \"fine\": \"2018-12-31\",            \r\n" + 
			"	  \"oggetto\": \"0,29\",            \r\n" + 
			"	  \"isfid\": \"No\",            \r\n" + 
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
			"	  \"isfid\": \"No\",            \r\n" + 
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
			"	  \"isfid\": \"No\",            \r\n" + 
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
	public void A_testInserimento() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.post("/promo/inserisci")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonData)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andDo(print());
	}
			 
	
	@Test
	public void B_testSelTutti() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/promo/cerca/tutti")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(1)))
				.andDo(print());
	}

	@Test
	public void C_listPromoById() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/promo/cerca/id/481AD25F-ED20-40FA-B01F-B031B20EB47C")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.codice").exists())
				.andExpect(jsonPath("$.codice").value("VL01"))
				.andExpect(jsonPath("$.descrizione").exists())
				.andExpect(jsonPath("$.descrizione").value("PROMO VOLANTINO 1"))

				.andExpect(jsonPath("$.dettPromo[0].codart").exists())
				.andExpect(jsonPath("$.dettPromo[0].codart").value("002000301")) 
				.andExpect(jsonPath("$.dettPromo[0].oggetto").exists())
				.andExpect(jsonPath("$.dettPromo[0].oggetto").value("0,29")) 
				.andExpect(jsonPath("$.dettPromo[0].tipoPromo.descrizione").exists())
				.andExpect(jsonPath("$.dettPromo[0].tipoPromo.descrizione").value("TAGLIO PREZZO")) 

				.andExpect(jsonPath("$.dettPromo[1].codart").exists())
				.andExpect(jsonPath("$.dettPromo[1].codart").value("000087101")) 
				.andExpect(jsonPath("$.dettPromo[1].oggetto").exists())
				.andExpect(jsonPath("$.dettPromo[1].oggetto").value("1,99")) 
				.andExpect(jsonPath("$.dettPromo[1].tipoPromo.descrizione").exists())
				.andExpect(jsonPath("$.dettPromo[1].tipoPromo.descrizione").value("TAGLIO PREZZO")) 


				.andExpect(jsonPath("$.dettPromo[2].codart").exists())
				.andExpect(jsonPath("$.dettPromo[2].codart").value("007288701")) 
				.andExpect(jsonPath("$.dettPromo[2].oggetto").exists())
				.andExpect(jsonPath("$.dettPromo[2].oggetto").value("3,29")) 
				.andExpect(jsonPath("$.dettPromo[2].tipoPromo.descrizione").exists())
				.andExpect(jsonPath("$.dettPromo[2].tipoPromo.descrizione").value("TAGLIO PREZZO"))
				
				.andExpect(jsonPath("$.depRifPromo[0].idDeposito").exists())
				.andExpect(jsonPath("$.depRifPromo[0].idDeposito").value("525")) 

				.andDo(print());
	}

	private String JsonData2 =
		"{\"idPromo\": \"481AD25F-ED20-40FA-B01F-B031B20EB47C\",    \r\n" + 
		" \"anno\": 2018,    \r\n" + 
		" \"codice\": \"VL01\",    \r\n" + 
		" \"descrizione\": \"PROMO VOLANTINO 1\",\r\n" + 
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
		"	}],\r\n" + 
		"	\"depRifPromo\": [\r\n" + 
		"		{\r\n" + 
		"			\"id\": 4,\r\n" + 
		"            \"idDeposito\": 525\r\n" + 
		"        }\r\n" + 
		"	]\r\n" + 
		"}";

	@Test
	public void D_testModifica() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.post("/promo/inserisci").contentType(MediaType.APPLICATION_JSON)
				.content(JsonData2)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andDo(print());
	}
	
	@Test
	public void E_listPromoById2() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/promo/cerca/id/481AD25F-ED20-40FA-B01F-B031B20EB47C")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.codice").exists())
				.andExpect(jsonPath("$.codice").value("VL01"))
				.andExpect(jsonPath("$.descrizione").exists())
				.andExpect(jsonPath("$.descrizione").value("PROMO VOLANTINO 1"))

				.andExpect(jsonPath("$.dettPromo[0].isfid").value("Si")) 
				.andExpect(jsonPath("$.dettPromo[1].isfid").value("Si")) 
				.andExpect(jsonPath("$.dettPromo[2].isfid").value("Si")) 
			 
				.andDo(print());
	}
	
	@Test
	public void F_PromoExceptionTest() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/promo/cerca/id/959325BE-B4F0-4F95-9DA4-0A3E5F3858S2")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andDo(print());
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
