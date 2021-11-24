package ru.job4j.servlets;

import com.google.gson.GsonBuilder;
import ru.job4j.model.Item;
import ru.job4j.store.HbnStore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class AllItemsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Item> itemList = HbnStore.instOf().findReallyAllItems();
        resp.setContentType("application/json; charset=urf-8");
        OutputStream outputStream = resp.getOutputStream();
        String json = new GsonBuilder().create().toJson(itemList);
        outputStream.write(json.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}