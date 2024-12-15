package edu.bbte.idde.vcim2315.servlet;

import edu.bbte.idde.vcim2315.exception.ComponentQueryFailedException;
import edu.bbte.idde.vcim2315.model.Component;
import edu.bbte.idde.vcim2315.service.ComponentService;
import edu.bbte.idde.vcim2315.service.Service;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/components")
public class ServletGetComponentsView extends HttpServlet {
    private Service service;

    @Override
    public void init() {
        service = new ComponentService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException {
        try {
            Map<Long, Component> componentsMap = service.getAllComponents();
            List<Component> components = new ArrayList<>(componentsMap.values());

            request.setAttribute("components", components);
            request.getRequestDispatcher("components.jsp").forward(request, res);
        } catch (ComponentQueryFailedException e) {
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to load components: " + e.getMessage());
        }
    }
}
