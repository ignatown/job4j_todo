package ru.job4j.servlets;

import com.google.gson.Gson;
import ru.job4j.model.Item;
import ru.job4j.store.HbnStore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CloseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String id = new Gson().fromJson(req.getParameter("value"), String.class);
        HbnStore.instOf().wasDone(Integer.parseInt(id));
    }
}