package org.Ged.ws.clientTestsApi;


import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;
import java.util.Optional;

import org.Ged.dto.ClientDto;
import org.Ged.dto.Pageable.ClientSearchPageableDto;
import org.Ged.dto.SearchRequest.ClientSearchRequestDto;
import org.Ged.ws.api.client.ClientApi;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.junit.runners.MethodSorters;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(Alphanumeric.class)

public class clientTestsApi {
	@Autowired
	ClientApi clientApi;
	
	@Test
	public void a_getAllClient() {
		List<ClientDto> result=clientApi.getAllClients();
		result.forEach(clt -> System.out.println("before delete : "+clt.getNom()));
		assertTrue(!result.isEmpty());
	}
	
	@Test
	public void b_getClientById() {
		Long expectedId=1L;
		ClientDto  result=clientApi.getClientById(expectedId);
		System.out.println("result of getById : "+result.getNom());
		assertEquals(expectedId,result.getId());		
	}
	
	@Test
	public void c_deleteClientById() {
		Long expectedId=3L;
	    clientApi.deleteClient(expectedId);
	    System.out.println("*************************deleeeeeeted");
		//assertNotEquals(expectedId,clientApi.getClientById(expectedId).getId());		
	}
	
	@Test
	public void d_getent() {
		List<ClientDto> result=clientApi.getAllClients();
		result.forEach(clt -> System.out.println("after delete : "+clt.getNom()));
		assertTrue(!result.isEmpty());
	}
	
	@Test
	public void e_should_data() {
		ClientSearchPageableDto searchRequest = new ClientSearchPageableDto();
		searchRequest.setClientSearchRequest(new ClientSearchRequestDto());	
		searchRequest.setPage(0);
		searchRequest.setSize(10);
		searchRequest.setSortedBy("nom");
		Page<ClientDto> result=clientApi.search(searchRequest);
		assertTrue(!result.isEmpty());
	}

}
