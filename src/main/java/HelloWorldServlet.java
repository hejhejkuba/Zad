import java.io.IOException;
import java.io.Serial;
import java.lang.reflect.Field;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/HelloWorldServlet")
public class HelloWorldServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    final String message = "Hello World";
    final String textHtml = "text/html";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fname = request.getParameter("fname");

        Names names = new Names();


        if (fname == null || fname.isEmpty()) {
            response.setContentType(textHtml);
            response.getWriter().println("<h1>" + message + "</h1>");
        } else {
            switch (fname) {
                case "Hal":
                    requestDispatcher("hal", request, response);
                    break;
                case "David":
                    requestDispatcher("david", request, response);
                    break;
                default:
                    response.setContentType(textHtml);
                    response.getWriter().println("<h1>Hello " + fname + "</h1>");

            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    void requestDispatcher(String name, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Field nameField = null;
        try {
            nameField = Names.class.getDeclaredField(name.toLowerCase());
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        MyTarget nameFieldAnnotation = nameField.getAnnotation(MyTarget.class);
        String nameValue = nameFieldAnnotation.value();
        RequestDispatcher rd = request.getRequestDispatcher(nameValue + ".jsp");
        rd.forward(request, response);
    }

}




