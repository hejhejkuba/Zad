import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.junit.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class HelloWorldServletTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private ServletContext context;

    @Mock
    private RequestDispatcher requestDispatcher;
    private StringWriter stringWriter;
    private HelloWorldServlet servlet;

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        servlet = new HelloWorldServlet();
        stringWriter = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));
        when(request.getServletContext()).thenReturn(context);
        when(context.getContextPath()).thenReturn("");
    }

    @org.junit.jupiter.api.Test
    void doGetHal() throws ServletException, IOException {
        //request.getParameter("Hal");
        when(request.getParameter("fname")).thenReturn("Hal");
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

        // Act
        servlet.doGet(request, response);

        // Assert
        verify(request).getRequestDispatcher("halView.jsp");
        verify(requestDispatcher).forward(request, response);
    }

    @org.junit.jupiter.api.Test
    void doGetDavid() throws ServletException, IOException {
        when(request.getParameter("fname")).thenReturn("David");
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

        // Act
        servlet.doGet(request, response);

        // Assert
        verify(request).getRequestDispatcher("davidView.jsp");
        verify(requestDispatcher).forward(request, response);
    }

    @org.junit.jupiter.api.Test
    void doGetNull() throws ServletException, IOException {
        when(request.getParameter("fname")).thenReturn(null);

        // Act
        servlet.doGet(request, response);

        // Assert
        assertTrue(stringWriter.toString().contains("<h1>Hello World</h1>"));
        verify(response, atLeastOnce()).setContentType("text/html");
    }

    @org.junit.jupiter.api.Test
    void doGetAny() throws ServletException, IOException {
        when(request.getParameter("fname")).thenReturn("Anything");
        // Act
        servlet.doGet(request, response);

        // Assert
        assertTrue(stringWriter.toString().contains("<h1>Hello " + request.getParameter("fname") + "</h1>"));
        verify(response, atLeastOnce()).setContentType("text/html");
    }

    @org.junit.jupiter.api.Test
    void doPost() throws ServletException, IOException {
        // Act
        servlet.doGet(request, response);
    }

}