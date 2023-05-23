package com.web.application.gamequest.controller;

import com.web.application.gamequest.model.Question;
import com.web.application.gamequest.model.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuestServletTest {

    QuestServlet servlet;

    @BeforeEach
    void setup() {
        servlet = new QuestServlet();
    }

    @Test
    void doGet() throws ServletException, IOException {
        Repository repository = mock(Repository.class);

        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        final Question question = mock(Question.class);
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("answerid")).thenReturn("2");

        servlet.doGet(request, response);
        verify(request).getParameter("answerid");
        assertEquals("2", request.getParameter("answerid"));
    }

    @Test
    public void testAddStatistics() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        final HttpSession session = mock(HttpSession.class);

        final HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getRemoteAddr()).thenReturn("127.0.0.1");


        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("name")).thenReturn("John Doe");


        Cookie[] cookies = new Cookie[1];
        cookies[0] = new Cookie("gameAttempt", "3");
        when(request.getCookies()).thenReturn(cookies);

        Method method = QuestServlet.class.getDeclaredMethod("addStatistics", HttpServletRequest.class);
        method.setAccessible(true);
        method.invoke(servlet, request);

        verify(request).setAttribute("ip", "127.0.0.1");
        verify(request).getSession();
        verify(session).getAttribute("name");
        verify(request).getCookies();
        verify(request).setAttribute("attempt", "3");
    }
}


