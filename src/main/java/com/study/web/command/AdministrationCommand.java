package com.study.web.command;

import com.study.service.AdministrationService;
import com.study.service.SpeakerService;
import com.study.web.DTO.ConferenceDTO;
import com.study.web.DTO.SpeakerDTO;
import com.study.web.DTO.UserDTO;
import com.study.web.data.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.study.web.constant.PathConstants.ADMINISTRATION_PAGE;

public class AdministrationCommand implements Command {
    private static final Logger LOG = LoggerFactory.getLogger(AdministrationCommand.class);
    private static final AdministrationService administrationService = new AdministrationService();
    private static final SpeakerService speakerService = new SpeakerService();

    @Override
    public Page perform(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<ConferenceDTO> conferences = administrationService.getAllConferences();
        List<SpeakerDTO> speakers = speakerService.getAllSpeakers();
        List<UserDTO> users = administrationService.getAllUsers();

        if (!conferences.isEmpty()) {
            LOG.info("Adding conferences list to administration page");
            session.setAttribute("conferences", conferences);
        }

        if (!speakers.isEmpty()) {
            LOG.info("Adding speakers list to administration page");
            session.setAttribute("speakers", speakers);
        }
        if (!users.isEmpty()) {
            LOG.info("Adding users list to administration page");
            request.getSession().setAttribute("users", users);
        }
        return new Page(ADMINISTRATION_PAGE);
    }
}
