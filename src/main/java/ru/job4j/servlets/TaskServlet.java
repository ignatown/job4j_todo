package ru.job4j.servlets;

import ru.job4j.model.Item;
import ru.job4j.model.User;
import ru.job4j.store.HbnStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class TaskServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = ((User) req.getSession().getAttribute("user")).getId();
        if ("all".equals(req.getParameter("show"))) {
            req.setAttribute("tasks", new ArrayList<>(HbnStore.instOf().findReallyAllItems(userId)));
        } else {
            req.setAttribute("tasks", new ArrayList<>(HbnStore.instOf().findAllOffItems(userId)));
        }
        req.setAttribute("user", req.getSession().getAttribute("user"));
        req.getRequestDispatcher("tasks.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        HbnStore.instOf().saveItem(
                Item.init(
                        req.getParameter("description"),
                        new Timestamp(System.currentTimeMillis()),
                        false,
                        ((User) req.getSession().getAttribute("user"))
                )
        );
        resp.sendRedirect(req.getContextPath() + "/task");
    }
}