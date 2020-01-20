package com.infoshare.academy.highfive.servlet.holiday;


import com.infoshare.academy.highfive.domain.Holiday;
import com.infoshare.academy.highfive.freemarker.TemplateProvider;
import com.infoshare.academy.highfive.service.holiday.HolidayService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/search-by-name")
public class SearchHolidayByNameServlet extends HttpServlet {

    Logger LOGGER = LoggerFactory.getLogger(getClass().getName());

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    HolidayService holidayService;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        PrintWriter writer = resp.getWriter();

        Map<String, Object> dataModel = new HashMap<>();

        Template template = this.templateProvider.getTemplate(getServletContext(), "template.ftlh");

        dataModel.put("method", req.getMethod());
        dataModel.put("contentTemplate", "holiday-search.ftlh");
        dataModel.put("title", "Search result by name");
        dataModel.put("pluginCssTemplate", "plugin-css-all-holiday.ftlh");
        dataModel.put("pluginJsTemplate", "plugin-js-all-holiday.ftlh");

        try {
            template.process(dataModel, writer);
        } catch (
                TemplateException e) {
            e.getMessage();
        }

      }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String searchByName = req.getParameter("search_by_name");
        Template template = this.templateProvider.getTemplate(getServletContext(), "template.ftlh");

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        Map<String, Object> dataModel = new HashMap<>();

        List<Holiday> holidays = holidayService.searchHolidayByName(searchByName);

        dataModel.put("method", req.getMethod());
        dataModel.put("contentTemplate", "holiday-search-by-name.ftlh");
        dataModel.put("title", "Search result by name");
        dataModel.put("pluginCssTemplate", "plugin-css-all-holiday.ftlh");
        dataModel.put("pluginJsTemplate", "plugin-js-all-holiday.ftlh");
        dataModel.put("holidays", holidays);


        try {
            template.process(dataModel, writer);
        } catch (
                TemplateException e) {
            LOGGER.warn("Issue with processing Freemarker template.");
            e.getMessage();
        }

    }
}