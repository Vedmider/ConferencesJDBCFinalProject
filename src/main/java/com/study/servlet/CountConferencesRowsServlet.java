package com.study.servlet;


import com.study.persistence.dao.ConferenceDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CountConferencesRowsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        ConferenceDAO conferencesDAO = new ConferenceDAO();
        int numberOfRows = conferencesDAO.count();

        PrintWriter out = resp.getWriter();
        out.print("<h1>Table Conferences has </h1> " + numberOfRows + "  records in it");
    }
}
