package com.web.application.gamequest.controller;

import com.web.application.gamequest.model.Answer;
import com.web.application.gamequest.model.Question;
import com.web.application.gamequest.model.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuestServletTest {

    QuestServlet servlet;

    @BeforeEach
    void setup() {
        servlet = new QuestServlet();
    }

    @Test
    void doGet_withMinimalMock_shouldNotThrowException() throws Exception {
        Repository mockRepository = mock(Repository.class);
        Answer mockAnswer = mock(Answer.class);
        Question mockQuestion = new Question(1, "Test Q", Question.Type.USUAL);
        Collection<Answer> dummyAnswers = List.of();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getParameter("answerid")).thenReturn("2");
        when(mockRepository.getAnswerById(2)).thenReturn(mockAnswer);
        when(mockAnswer.getTo()).thenReturn(mockQuestion);
        when(mockRepository.getAnswerByFromQuestionId(1)).thenReturn(dummyAnswers);

        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(request.getRemoteAddr()).thenReturn("127.0.0.1");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("name")).thenReturn("TestUser");
        when(request.getCookies()).thenReturn(new Cookie[]{new Cookie("gameAttempt", "1")});

        Field field = QuestServlet.class.getDeclaredField("repository");
        field.setAccessible(true);
        field.set(servlet, mockRepository);

        servlet.doGet(request, response);

        verify(mockRepository).getAnswerById(2);
        verify(dispatcher).forward(request, response);
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


