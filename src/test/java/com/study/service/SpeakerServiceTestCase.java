package com.study.service;


import com.study.persistence.dao.RoleDAO;
import com.study.persistence.dao.SpeakerDAO;
import com.study.persistence.entity.Role;
import com.study.persistence.entity.Speaker;
import com.study.web.DTO.SpeakerDTO;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.exceptions.misusing.InvalidUseOfMatchersException;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

public class SpeakerServiceTestCase {
    @Rule
    public final MockitoRule rule = MockitoJUnit.rule();
    @Mock
    private SpeakerDAO speakerDAO;
    @Mock
    private RoleDAO roleDAO;
    private SpeakerService speakerService = new SpeakerService();
    private Speaker speaker;
    private Role role;
    private List<Speaker> speakers;
    private Map<Integer, Role> roles;

    private static void setFinalStatic(Field field, Object newValue) throws Exception {
        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(null, newValue);
    }

    @Before
    public void setUp() throws Exception {
        setFinalStatic(SpeakerService.class.getDeclaredField("speakerDAO"), speakerDAO);
        setFinalStatic(SpeakerService.class.getDeclaredField("roleDao"), roleDAO);

        speakers = new ArrayList<>();
        speaker = new Speaker();
        speaker.setId(1);
        speaker.setEmail("a@gmail.com");
        speaker.setFirstName("Sonya");
        speaker.setLastName("Rykiel");
        speaker.setLogin("rykiels");
        speaker.setPassword("rykiels");
        speaker.setUserRole(1);
        speakers.add(speaker);
        speaker = new Speaker();
        speaker.setId(2);
        speaker.setEmail("b@gmail.com");
        speaker.setFirstName("John");
        speaker.setLastName("Galliano");
        speaker.setLogin("gallianoj");
        speaker.setPassword("gallianoj");
        speaker.setUserRole(2);
        speakers.add(speaker);
        speaker = new Speaker();
        speaker.setId(3);
        speaker.setEmail("c@gmail.com");
        speaker.setFirstName("Gianni");
        speaker.setLastName("Versace");
        speaker.setLogin("versaceg");
        speaker.setPassword("versaceg");
        speaker.setUserRole(3);
        speakers.add(speaker);

        roles = new HashMap<>();
        role = new Role();
        role.setId(1);
        role.setTitle("ADMIN");
        roles.put(1, role);
        role = new Role();
        role.setId(2);
        role.setTitle("MODERATOR");
        roles.put(2, role);
        role = new Role();
        role.setId(3);
        role.setTitle("USER");
        roles.put(3, role);

        when(roleDAO.getRoleRights(anyInt())).thenReturn(new ArrayList<>());
        when(speakerDAO.getAll()).thenReturn(speakers);
        when(roleDAO.getById(anyInt())).then(invocation -> {
            int argument = (int) invocation.getArguments()[0];
            switch (argument) {
                case 1:
                    return roles.get(1);
                case 2:
                    return roles.get(2);
                case 3:
                    return roles.get(3);
                default:
                    throw new InvalidUseOfMatchersException(
                            String.format("Argument %d does not match", argument));
            }
        });
    }

    @Test
    public void shouldReturnAllAppropriateSpeakers() {
        List<SpeakerDTO> speakers = speakerService.getAllSpeakers();

        assertNotNull(speakers);
        assertFalse(speakers.isEmpty());
        assertEquals(speakers.size(), 3);

        speakers.stream().forEach(speakerDTO -> assertEquals(speakerDTO.getRole().getId(), speakerDTO.getId()));
    }
}
