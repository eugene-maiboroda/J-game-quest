package com.web.application.gamequest.controller;

import org.junit.jupiter.api.Test;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;


import static org.mockito.Mockito.*;

class AuthServletTest {
    String path = "index.html";

    @Test
    void doGet() throws IOException {

        final AuthServlet servlet = new AuthServlet();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);

        servlet.doGet(request, response);
        verify(response).sendRedirect(path);

    }

    @Test
    void doPostWithCookie() throws IOException {

        final AuthServlet servlet = new AuthServlet();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);

        Cookie cookie = new Cookie("gameAttempt", "1");
        when(request.getCookies()).thenReturn(new Cookie[]{cookie});
        doNothing().when(response).addCookie(any(Cookie.class));

        when(request.getParameter("name")).thenReturn("Javik");
        when(request.getSession(true)).thenReturn(session);
        when(request.getContextPath()).thenReturn("/doPostTest");

        servlet.doPost(request, response);

        verify(request).setCharacterEncoding("UTF-8");
        verify(request).getCookies();
        verify(request).getParameter("name");
        verify(request).getContextPath();

        verify(session).setAttribute("name", "Javik");

        verify(response).addCookie(cookie);
        verify(response).sendRedirect("/doPostTest/quest");

    }
}