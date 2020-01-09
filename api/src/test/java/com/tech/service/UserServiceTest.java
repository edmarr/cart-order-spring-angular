package com.tech.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.tech.domain.User;
import com.tech.dto.UserDTO;
import com.tech.exception.RequestException;
import com.tech.exception.ResourceNotFoundException;
import com.tech.mapper.MapperObject;
import com.tech.repository.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private CustomSequenceService sequenceService;

    @InjectMocks
    private UserService service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = RequestException.class)
    public void createRequestExceptionTest() {
        UserDTO dto = new UserDTO();
        service.create(dto);
    }

    @Test
    public void createTest() {
        UserDTO dto = new UserDTO();
        dto.setName("name");
        dto.setEmail("email@gmail.com");

        User entity = MapperObject.parse(dto, User.class);
        when(sequenceService.getNextSequence(anyString())).thenReturn(1);
        when(repository.save(any())).thenReturn(entity);
        UserDTO result = service.create(dto);
        assertEquals(result.getName(), dto.getName());
        assertEquals(result.getEmail(), dto.getEmail());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void deleteResourceNotFoundExceptionTest() {
        service.delete(anyInt());
    }

    @Test
    public void deleteTest() {

        UserDTO dto = new UserDTO();
        dto.setName("name");
        dto.setEmail("email@gmail.com");

        Optional<User> opt = Optional.of(MapperObject.parse(dto, User.class));
        when(repository.findById(anyInt())).thenReturn(opt);
        service.delete(anyInt());
    }

    @Test
    public void findByIdTest() {
        UserDTO dto = new UserDTO();
        dto.setName("name");
        dto.setEmail("email@gmail.com");

        Integer id = 1;

        Optional<User> opt = Optional.of(MapperObject.parse(dto, User.class));
        when(repository.findById(anyInt())).thenReturn(opt);

        assertEquals(dto, service.findById(id));

    }


    @Test
	public void updateTest() {
		UserDTO dto = new UserDTO();
		dto.setId(1);
		dto.setName("name");
		dto.setEmail("email@gmail.com");

		User entity = MapperObject.parse(dto, User.class);

		Optional<User> opt = Optional.of(entity);
		when(repository.findById(anyInt())).thenReturn(opt);
		when(repository.save(any())).thenReturn(entity);

		service.update(dto);
	}

}