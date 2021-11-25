package ru.job4j.servlets;

import ru.job4j.model.User;
import ru.job4j.store.HbnStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class CloseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String value = req.getParameter("check");
        HbnStore.instOf().wasDone(Integer.parseInt(value));
        resp.sendRedirect(req.getContextPath() + "/task");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        int userId = user.getId();
        req.setAttribute("tasks", new ArrayList<>(HbnStore.instOf().findAllOffItems(userId)));
        req.setAttribute("user", req.getSession().getAttribute("user"));
        req.getRequestDispatcher("tasks.jsp").forward(req, resp);
    }
}