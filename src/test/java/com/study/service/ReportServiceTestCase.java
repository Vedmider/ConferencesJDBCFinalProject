package com.study.service;

import com.study.persistence.dao.ReportDAO;
import com.study.persistence.entity.Report;
import com.study.persistence.entity.Speaker;
import com.study.web.DTO.ReportDTO;
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

public class ReportServiceTestCase {
    @Mock
    private ReportDAO reportDAO;
    @Mock
    private SpeakerService speakerService;
    @Rule
    public final MockitoRule rule = MockitoJUnit.rule();
    private ReportService reportService = new ReportService();
    private List<Report> reports;
    private Map<Integer, SpeakerDTO> speakers;
    private SpeakerDTO speaker;
    private Report report;

    private static void setFinalStatic(Field field, Object newValue) throws Exception {
        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(null, newValue);
    }

    @Before
    public void setUp() throws Exception {
        setFinalStatic(ReportService.class.getDeclaredField("reportDAO"), reportDAO);
        setFinalStatic(ReportService.class.getDeclaredField("speakerService"), speakerService);

        reports = new ArrayList<>();
        speakers = new HashMap<>();

        speaker = new SpeakerDTO();
        speaker.setId(1);
        speakers.put(1, speaker);
        speaker = new SpeakerDTO();
        speaker.setId(2);
        speakers.put(2, speaker);
        speaker = new SpeakerDTO();
        speaker.setId(3);
        speakers.put(3, speaker);

        report = new Report();
        report.setId(1);
        report.setTitle("Monkey business. Features");
        report.setSpeakerId(1);
        reports.add(report);
        report = new Report();
        report.setId(2);
        report.setTitle("Monkey business. Disadvantages");
        report.setSpeakerId(2);
        reports.add(report);
        report = new Report();
        report.setId(3);
        report.setTitle("Monkey business. Advantages");
        report.setSpeakerId(3);
        reports.add(report);

        when(reportDAO.getAll()).thenReturn(reports);
        when(speakerService.getById(anyInt())).thenAnswer(invocation -> {
            int argument = (int) invocation.getArguments()[0];
            switch (argument) {
                case 1:
                    return speakers.get(1);
                case 2:
                    return speakers.get(2);
                case 3:
                    return speakers.get(3);
                default:
                    throw new InvalidUseOfMatchersException(
                            String.format("Argument %d does not match", argument));
            }
        });

    }

    @Test
    public void shouldReturnAllReportsWithAppropriateSpeakers(){
        List<ReportDTO> reports = reportService.getAll();

        assertNotNull(reports);
        assertFalse(reports.isEmpty());
        assertEquals(reports.size(), 3);

        reports.stream().forEach(reportDTO -> assertEquals(reportDTO.getSpeaker().getId(), reportDTO.getId()));
    }
}
