package com.study.web.command;

import com.study.service.AdministrationService;
import com.study.service.ReportService;
import com.study.service.SpeakerService;
import com.study.web.DTO.ConferenceDTO;
import com.study.web.DTO.SpeakerDTO;
import com.study.web.DTO.UserDTO;
import com.study.web.data.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.study.web.constant.PathConstants.ADMINISTRATION_PAGE;

public class AdministrationCommand implements Command {
    private static final Logger LOG = LoggerFactory.getLogger(AdministrationCommand.class);
    private static final AdministrationService administrationService = new AdministrationService();
    private static final SpeakerService speakerService = new SpeakerService();
    private static final ReportService reportService = new ReportService();
    private static final int LIMIT = 10;


    @Override
    public Page perform(HttpServletRequest request) {
        processRequest(request);
        return new Page(ADMINISTRATION_PAGE);
    }

    private void processRequest(HttpServletRequest request){
        int gotoPage;
        int maxRecords;
        int maxPages;
        List<Integer> recordsInTables  = new ArrayList<>();

        recordsInTables.add(administrationService.getAllConferences().size());
        recordsInTables.add(speakerService.getAllSpeakers().size());
        recordsInTables.add(administrationService.getAllUsers().size());
        recordsInTables.add(reportService.getAll().size());

        maxRecords = Collections.max(recordsInTables);
        maxPages = maxRecords/LIMIT;

        if (maxRecords % LIMIT > 0){
            maxPages++;
        }

        if(request.getParameter("gotoPage") == null){
            gotoPage = 1;
        } else {
            gotoPage = Integer.valueOf(request.getParameter("gotoPage"));
        }

        if (gotoPage > maxPages || gotoPage < 1){
            return;
        }

        if (gotoPage > 1){
            request.getSession().setAttribute("previous", gotoPage - 1);
        }

        if (gotoPage < maxPages){
            request.getSession().setAttribute("next", gotoPage + 1);
        }

        addPageDTO(gotoPage, request);
    }

    private void addPageDTO(int page, HttpServletRequest request){
        HttpSession session = request.getSession();
        List<ConferenceDTO> conferences = administrationService.getAllConferences(page - 1, LIMIT);
        List<SpeakerDTO> speakers = speakerService.getAllSpeakers(page - 1, LIMIT);
        List<UserDTO> users = administrationService.getAllUsers(page - 1, LIMIT);

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
    }

}
