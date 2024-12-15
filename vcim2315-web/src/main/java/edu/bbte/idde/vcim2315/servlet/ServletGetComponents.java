package edu.bbte.idde.vcim2315.servlet;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import edu.bbte.idde.vcim2315.exception.ComponentInsertFailedException;
import edu.bbte.idde.vcim2315.exception.ComponentNotFoundException;
import edu.bbte.idde.vcim2315.exception.ComponentQueryFailedException;
import edu.bbte.idde.vcim2315.model.Component;
import edu.bbte.idde.vcim2315.service.ComponentService;
import edu.bbte.idde.vcim2315.service.Service;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;



@WebServlet("/api/components")
public class ServletGetComponents extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(ServletGetComponents.class);
    private Service service;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init() {
        service = new ComponentService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        try {
            response.setContentType("application/json");

            if (idParam == null) {
                Map<Long, Component> components = service.getAllComponents();
                objectMapper.writeValue(response.getWriter(), components);
            } else {
                try {
                    long id = Long.parseLong(idParam);
                    Component component = service.getComponent(id);
                    objectMapper.writeValue(response.getWriter(), component);
                } catch (NumberFormatException e) {
                    sendJsonError(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid ID format");
                }
            }
        } catch (ComponentNotFoundException e) {
            sendJsonError(response, HttpServletResponse.SC_NOT_FOUND, "Component not found");
        } catch (ComponentQueryFailedException e) {
            sendJsonError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Query failed");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        try {
            Component component = objectMapper.readValue(request.getInputStream(), Component.class);
            service.addComponent(component);
            response.setStatus(HttpServletResponse.SC_CREATED);
            objectMapper.writeValue(response.getOutputStream(), component);
        } catch (JsonMappingException | JsonParseException e) {
            sendJsonError(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid JSON input: " + e.getMessage());
        } catch (ComponentInsertFailedException e) {
            sendJsonError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Insert failed");
        } catch (IOException e) {
            sendJsonError(response, HttpServletResponse.SC_BAD_REQUEST, "Failed to read input");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        try {
            Component component = objectMapper.readValue(request.getInputStream(), Component.class);
            service.updateComponent(component);
            objectMapper.writeValue(response.getOutputStream(), component);
        } catch (JsonMappingException | JsonParseException e) {
            sendJsonError(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid JSON input: " + e.getMessage());
        } catch (ComponentNotFoundException e) {
            sendJsonError(response, HttpServletResponse.SC_NOT_FOUND, "Component not found");
        } catch (ComponentQueryFailedException e) {
            sendJsonError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Update failed");
        } catch (IOException e) {
            sendJsonError(response, HttpServletResponse.SC_BAD_REQUEST, "Failed to read input");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idParam = request.getParameter("id");
        response.setContentType("application/json");

        try {
            if (idParam == null) {
                sendJsonError(response, HttpServletResponse.SC_BAD_REQUEST, "ID parameter is required");
                return;
            }
            try {
                long id = Long.parseLong(idParam);
                service.deleteComponent(id);
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } catch (NumberFormatException e) {
                sendJsonError(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid ID format");
            }
        } catch (ComponentNotFoundException e) {
            sendJsonError(response, HttpServletResponse.SC_NOT_FOUND, "Component not found");
        } catch (ComponentQueryFailedException e) {
            sendJsonError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Delete failed");
        }
    }

    private void sendJsonError(HttpServletResponse response, int statusCode, String message) throws IOException {
        response.setContentType("application/json");
        response.setStatus(statusCode);
        objectMapper.writeValue(response.getWriter(), Map.of("error", message));
    }

}
