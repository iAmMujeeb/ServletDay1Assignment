package com.demoservlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        String password = request.getParameter("password");

        Pattern pattern = Pattern.compile("^[A-Z][a-z]{3,8}$");
        Matcher matcher = pattern.matcher(user);

        Pattern pattern1 = Pattern.compile("^[a-zA-Z0-9!@#$%^&*]{8}$");
        Matcher matcher1 = pattern1.matcher(password);

        if (matcher.matches() && matcher1.matches()) {
            request.setAttribute("user", "admin");
            request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);
        } else {
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red> Either user or password incorrect</font>");
            requestDispatcher.include(request, response);
        }
    }
}
