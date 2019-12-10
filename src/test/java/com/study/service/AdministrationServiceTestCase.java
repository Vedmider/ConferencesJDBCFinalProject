package com.study.service;

import com.study.persistence.dao.ConferenceDAO;
import com.study.persistence.entity.Conference;
import com.study.persistence.entity.Report;
import com.study.web.DTO.ConferenceDTO;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class AdministrationServiceTestCase {
    @Mock
    private ConferenceDAO conferenceDAO;
    @Mock
    private UserService userService;
    @Mock
    private ReportService reportService;

    private AdministrationService administrationService;
    @Rule
    public final MockitoRule rule = MockitoJUnit.rule();
    private List<Conference> conferences;
    private List<Report> reports;

    private static void setFinalStatic(Field field, Object newValue) throws Exception {
        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(null, newValue);
    }

    @Before
    public void setUp() throws Exception {
        setFinalStatic(AdministrationService.class.getDeclaredField("conferenceDAO"), conferenceDAO);
        setFinalStatic(AdministrationService.class.getDeclaredField("userService"), userService);
        setFinalStatic(AdministrationService.class.getDeclaredField("reportService"), reportService);
        administrationService = new AdministrationService();
        conferences = new ArrayList<>();
        reports = new ArrayList<>();
        Report report;
        Conference conference;
        report = new Report();
        report.setId(1);
        report.setRegistered(30);
        report.setTitle("JUnit tests");
        report.setConferenceId(1);
        reports.add(report);
        report = new Report();
        report.setId(2);
        report.setRegistered(40);
        report.setTitle("JUnit tests 2");
        report.setConferenceId(1);
        reports.add(report);
        report = new Report();
        report.setId(3);
        report.setRegistered(50);
        report.setTitle("JUnit tests 3");
        report.setConferenceId(2);
        reports.add(report);
        report = new Report();
        report.setId(4);
        report.setRegistered(100);
        report.setTitle("JUnit tests 4");
        report.setConferenceId(2);
        reports.add(report);
        conference = new Conference();
        conference.setId(1);
        conference.setTheme("JUnit testing");
        conferences.add(conference);
        conference = new Conference();
        conference.setId(2);
        conference.setTheme("JUnit testing and mockito");
        conferences.add(conference);
        when(conferenceDAO.getAll()).thenReturn(conferences);
        when(conferenceDAO.getAllReports(1)).thenReturn(reports.stream()
                .filter(reportEntity -> reportEntity
                        .getConferenceId() == 1).collect(Collectors.toList()));
        when(conferenceDAO.getAllReports(2)).thenReturn(reports.stream()
                .filter(reportEntity -> reportEntity
                        .getConferenceId() == 2).collect(Collectors.toList()));
    }


    @Test
    public void shouldReturnAllConferencesDTO(){
       List<ConferenceDTO> conferenceDTOList = administrationService.getAllConferences();

       assertNotNull(conferenceDTOList);
       assertFalse(conferenceDTOList.isEmpty());
       assertTrue(conferenceDTOList.size() == 2);
    }


}
