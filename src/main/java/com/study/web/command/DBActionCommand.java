package com.study.web.command;

import com.study.service.*;
import com.study.web.data.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import static com.study.web.constant.PathConstants.ADMINISTRATION_PAGE;

public class DBActionCommand implements Command {
    private static final Logger LOG = LoggerFactory.getLogger(DBActionCommand.class);
    private static final AdministrationService administrationService = new AdministrationService();
    private static final SpeakerService speakerService = new SpeakerService();
    private static final ReportService reportService = new ReportService();
    private static final UserService userService = new UserService();


    @Override
    public Page perform(HttpServletRequest request) {

        performDBAction(request);
        return new Page(ADMINISTRATION_PAGE);
    }

    private void performDBAction(HttpServletRequest request) {
        Map<String, String> params = getAllParameters(request);
        DBActionsService service = getService(request.getParameter("entity"));
        service.perform(params);

    }

    private Map<String, String> getAllParameters(HttpServletRequest request) {
        Map<String, String> requestParams = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            requestParams.put(paramName, request.getParameter(paramName));
        }
        return requestParams;
    }

    private DBActionsService getService(String serviceType) {
        if (serviceType.equalsIgnoreCase("conference")) {
            return administrationService;
        } else if (serviceType.equalsIgnoreCase("report")) {
            return reportService;
        } else if (serviceType.equalsIgnoreCase("user")) {
            return userService;
        } else if (serviceType.equalsIgnoreCase("speaker")) {
            return speakerService;
        }
        return null;
    }


}
