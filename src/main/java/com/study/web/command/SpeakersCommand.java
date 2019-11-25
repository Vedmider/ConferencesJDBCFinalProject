package com.study.web.command;

import com.study.persistence.DTO.SpeakerDTO;
import com.study.service.SpeakerService;
import com.study.web.data.Page;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.study.web.constant.PathConstants.SPEAKERS_PAGE;

public class SpeakersCommand implements Command {
    @Override
    public Page perform(HttpServletRequest request) {
        SpeakerService speakersService = new SpeakerService();
        List<SpeakerDTO> speakers = speakersService.getAllSpeakers();
        if (!speakers.isEmpty()) {
            request.getSession().setAttribute("speakers", speakers);
        }
        return new Page(SPEAKERS_PAGE);
    }
}
