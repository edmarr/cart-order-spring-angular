package com.tech.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.tech.domain.CartItem;
import com.tech.dto.CartItemDTO;
import com.tech.mapper.MapperObject;
import com.tech.repository.CartItemRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CarItemServiceTest {

    @Mock
    private CartItemRepository repository;

    @Mock
    private CustomSequenceService sequenceService;

    @InjectMocks
    private CarItemService service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createTest() {
        CartItemDTO dto = new CartItemDTO();
        CartItem entity = MapperObject.parse(dto, CartItem.class);
        when(repository.save(any())).thenReturn(entity);
        assertNotNull(service.create(dto));
    }


    @Test
    public void createDeleteExistTest() {
        CartItemDTO dto = new CartItemDTO();
        dto.setId(1);
        CartItem entity = MapperObject.parse(dto, CartItem.class);
        when(repository.save(any())).thenReturn(entity);
        assertNotNull(service.create(dto));
    }

    @Test
	public void findAllTest() {
		List<CartItem> list = new ArrayList<>();
		CartItem item = new CartItem();
		list.add(item);

		when(repository.findAll()).thenReturn(list);
		assertEquals(list.size(), service.findAll().size());
	}
}