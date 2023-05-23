package com.web.application.gamequest.controller;

import com.web.application.gamequest.model.Answer;
import com.web.application.gamequest.model.Question;
import com.web.application.gamequest.model.Repository;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Collection;

public class QuestServlet extends HttpServlet {

    private final Repository repository = new Repository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String answerId = request.getParameter("answerid");
            Question question;
            if (answerId != null) {
                question = repository.getAnswerById(Integer.parseInt(answerId)).getTo();
            } else {
                question = repository.getQuestionById(1);
            }

            Collection<Answer> answers = repository.getAnswerByFromQuestionId(question.getId());
            request.setAttribute("question", question);
            request.setAttribute("answers", answers);
            addStatistics(request);
            request.getRequestDispatcher("/quest.jsp").forward(request, response);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
    private void addStatistics (HttpServletRequest request) {
        request.setAttribute("ip", request.getRemoteAddr());

        HttpSession session = request.getSession();
        request.setAttribute("userName", session.getAttribute("name"));

        Cookie [] cookies = request.getCookies();
        request.setAttribute("attempt", findCookiesValueByName("gameAttempt", cookies));
    }
private String findCookiesValueByName (String name, Cookie [] cookies) {
        for (Cookie cookie: cookies) {
            if (cookie.getName().equals(name)) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
