package com.study.web.command;

import com.study.persistence.DTO.ConferenceDTO;
import com.study.persistence.entity.Conference;
import com.study.service.AdministrationService;
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


    @Override
    public Page perform(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<ConferenceDTO> conferences = administrationService.getAllConferences();
        if (!conferences.isEmpty()) {
            session.setAttribute("conferences", conferences);
        }
        return new Page(ADMINISTRATION_PAGE);
    }
}
