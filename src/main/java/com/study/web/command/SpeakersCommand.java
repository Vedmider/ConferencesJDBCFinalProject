package com.study.web.command;

import com.study.service.AdministrationService;
import com.study.service.SpeakerService;
import com.study.web.DTO.ConferenceDTO;
import com.study.web.DTO.SpeakerDTO;
import com.study.web.data.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.study.web.constant.PathConstants.SPEAKERS_PAGE;

public class SpeakersCommand implements Command {
    private static final Logger LOG = LoggerFactory.getLogger(SpeakersCommand.class);
    private static final SpeakerService speakerService = new SpeakerService();
    private static final AdministrationService administrationService = new AdministrationService();

    @Override
    public Page perform(HttpServletRequest request) {
        List<SpeakerDTO> speakers = speakerService.getAllSpeakers();
        List<ConferenceDTO> conferences = administrationService.getAllConferences();
        if (!speakers.isEmpty()) {
            request.getSession().setAttribute("speakers", speakers);
        }
        if (!conferences.isEmpty()) {
            request.getSession().setAttribute("conferences", conferences);
        }

        return new Page(SPEAKERS_PAGE);
    }
}
