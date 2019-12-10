package com.study.service;

import com.study.web.DTO.UserDTO;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class LoginServiceTestCase {
    @Mock
    private AdministrationService administrationService;
    @Rule
    public final MockitoRule rule = MockitoJUnit.rule();
    private LoginService loginService = new LoginService();
    private List<UserDTO> users;
    private UserDTO user;

    private static void setFinalStatic(Field field, Object newValue) throws Exception {
        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(null, newValue);
    }

    @Before
    public void setUp() throws Exception {
        setFinalStatic(LoginService.class.getDeclaredField("administrationService"), administrationService);
        users = new ArrayList<>();
        user = new UserDTO();
        user.setId(1);
        user.setEmail("a@gmail.com");
        user.setFirstName("Sonya");
        user.setLastName("Rykiel");
        user.setLogin("rykiels");
        user.setPassword("rykiels");
        users.add(user);
        user = new UserDTO();
        user.setId(2);
        user.setEmail("b@gmail.com");
        user.setFirstName("John");
        user.setLastName("Galliano");
        user.setLogin("gallianoj");
        user.setPassword("gallianoj");
        users.add(user);
        user = new UserDTO();
        user.setId(3);
        user.setEmail("c@gmail.com");
        user.setFirstName("Gianni");
        user.setLastName("Versace");
        user.setLogin("versaceg");
        user.setPassword("versaceg");
        users.add(user);
        when(administrationService.getAllUsers()).thenReturn(users);
    }

    @Test
    public void shouldReturnAppropriateUserDTOWhenPerformLogin() {
        String loginAndPassword = "versaceg";
        UserDTO userDTO = loginService.performLogin(loginAndPassword, loginAndPassword);

        assertNotNull(userDTO);
        assertEquals(userDTO.getLogin(), loginAndPassword);
        assertEquals(userDTO.getPassword(), loginAndPassword);


    }
}
