package Com.App;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class Myservlet
 */
@WebServlet("/Myservlet")
public class Myservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Myservlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        // Retrieve numbers from form input
        String num1Str = request.getParameter("txtnum1");
        String num2Str = request.getParameter("txtnum2");
        String operation = request.getParameter("operation"); // Get the operation type

        if (num1Str != null && num2Str != null && operation != null) {
            try {
                int num1 = Integer.parseInt(num1Str);
                int num2 = Integer.parseInt(num2Str);
                int result = 0;
                String operationSymbol = "";

                switch (operation) {
                    case "add":
                        result = num1 + num2;
                        operationSymbol = "+";
                        break;
                    case "sub":
                        result = num1 - num2;
                        operationSymbol = "-";
                        break;
                    case "mul":
                        result = num1 * num2;
                        operationSymbol = "×";
                        break;
                    case "div":
                        if (num2 != 0) {
                            result = num1 / num2;
                            operationSymbol = "÷";
                        } else {
                            pw.write("<br>Error: Division by zero is not allowed.");
                            return;
                        }
                        break;
                    default:
                        pw.write("<br>Error: Invalid operation.");
                        return;
                }

                pw.write("<br>Received num1: " + num1);
                pw.write("<br>Received num2: " + num2);
                pw.write("<br>Operation: " + operationSymbol);
                pw.write("<br>Result: " + result);
            } catch (NumberFormatException e) {
                pw.write("<br>Error: Invalid number format.");
            }
        } else {
            pw.write("<br>Error: Missing parameters.");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
