package ru.job4j.servlets;

import ru.job4j.model.Category;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = ((User) req.getSession().getAttribute("user")).getId();
        List<Category> categoryList = HbnStore.instOf().findAllCategory();
        List<Item> itemList;
        if ("all".equals(req.getParameter("show"))) {
            itemList = new ArrayList<>(HbnStore.instOf().findReallyAllItems(userId));
        } else {
            itemList = new ArrayList<>(HbnStore.instOf().findAllOffItems(userId));
        }
        req.setAttribute("tasks", itemList);
        req.setAttribute("user", req.getSession().getAttribute("user"));
        req.setAttribute("allCategory", categoryList);
        req.getRequestDispatcher("tasks.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String[] categoryIds = req.getParameterValues("categoryIds");
        HbnStore.instOf().saveItem(
                Item.init(
                        req.getParameter("description"),
                        new Timestamp(System.currentTimeMillis()),
                        false,
                        ((User) req.getSession().getAttribute("user"))
                ), categoryIds
        );
        resp.sendRedirect(req.getContextPath() + "/task");
    }
}