
package com.tech.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.tech.domain.Item;
import com.tech.dto.ItemDTO;
import com.tech.exception.RequestException;
import com.tech.exception.ResourceNotFoundException;
import com.tech.mapper.MapperObject;
import com.tech.repository.ItemRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ItemServiceTest {

	@Mock
	private ItemRepository repository;

	@Mock
	private CustomSequenceService sequenceService;

	@InjectMocks
	private ItemService service;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test(expected = RequestException.class)
	public void createRequestExceptionTest() {
		ItemDTO dto = new ItemDTO();
		 service.create(dto);
	}

	@Test
	public void createTest() {
		ItemDTO dto = new ItemDTO();
		dto.setName("name");
		dto.setValueItem(BigDecimal.ONE);

		Item entity = MapperObject.parse(dto, Item.class);
		when(sequenceService.getNextSequence(anyString())).thenReturn(1);
		when(repository.save(any())).thenReturn(entity);
		ItemDTO result = service.create(dto);
		assertEquals(result.getName(), dto.getName());
		assertEquals(result.getValueItem(), dto.getValueItem());
	}

	@Test(expected = ResourceNotFoundException.class)
	public void deleteResourceNotFoundExceptionTest() {
		service.delete(anyInt());
	}

	@Test
	public void deleteTest() {

		ItemDTO dto = new ItemDTO();
		dto.setName("name");
		dto.setValueItem(BigDecimal.ONE);

		Optional<Item> opt = Optional.of(MapperObject.parse(dto, Item.class));
		when(repository.findById(anyInt())).thenReturn(opt);
		service.delete(anyInt());
	}

	@Test
	public void findByIdTest() {
		ItemDTO dto = new ItemDTO();
		dto.setName("name");
		dto.setValueItem(BigDecimal.ONE);

		Integer id = 1;

		Optional<Item> opt = Optional.of(MapperObject.parse(dto, Item.class));
		when(repository.findById(anyInt())).thenReturn(opt);

		assertEquals(dto, service.findById(id));

	}

	@Test
	public void findAllTest() {
		List<Item> list = new ArrayList<>();
		Item item = new Item();
		item.setName("name");
		item.setValueItem(BigDecimal.ONE);
		list.add(item);

		when(repository.findAll()).thenReturn(list);
		assertEquals(list.size(), service.findall().size());
	}

	@Test
	public void updateTest() {
		ItemDTO dto = new ItemDTO();
		dto.setId(1);
		dto.setName("name");
		dto.setValueItem(BigDecimal.ONE);

		Item entity = MapperObject.parse(dto, Item.class);

		Optional<Item> opt = Optional.of(entity);
		when(repository.findById(anyInt())).thenReturn(opt);
		when(repository.save(any())).thenReturn(entity);

		service.update(dto);
	}

}