package com.infoshare.academy.highfive.servlet;

import com.infoshare.academy.highfive.freemarker.TemplateProvider;
import com.infoshare.academy.highfive.mapper.request.VacationRequestMapper;
import com.infoshare.academy.highfive.request.VacationRequest;
import com.infoshare.academy.highfive.service.VacationService;
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
import java.util.Map;

@WebServlet("employee/add-vacation")
public class AddVacationServlet extends HttpServlet {

  Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Inject
  private TemplateProvider templateProvider;

  @Inject
  private VacationRequestMapper vacationRequestMapper;

  @Inject
  VacationService vacationService;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    resp.setContentType("text/html;charset=UTF-8");

    PrintWriter writer = resp.getWriter();

    Map<String, Object> dataModel = new HashMap<>();

    Template template = this.templateProvider.getTemplate(getServletContext(), "template.ftlh");

    dataModel.put("method", req.getMethod());
    dataModel.put("contentTemplate", "add-vacation.ftlh");
    dataModel.put("title", "Add vacation");
    dataModel.put("pluginCssTemplate", "plugin-css-main-content.ftlh");
    dataModel.put("pluginJsTemplate", "plugin-js-main-content.ftlh");

    logger.info("User provided with vacation form.");

    try {
      template.process(dataModel, writer);
    } catch (
      TemplateException e) {
      logger.warn("Issue with processing Freemarker template.");
      e.getMessage();
    }

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    try {
      VacationRequest vacationRequest = vacationRequestMapper.mapParamsToRequest(req);
      this.vacationService.addVacation(vacationRequest);
    } catch (ParseException e) {
      logger.warn("Issue with parsing http request.");
      e.printStackTrace();
    }

  }
}
