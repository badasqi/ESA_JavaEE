package Servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import Bean.ProviderBean;
import Entity.Provider;

import java.util.List;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/providers")
public class ProviderListServlet extends HttpServlet {

    @EJB
    private ProviderBean providerBean;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Provider> providerList = providerBean.getAllProviders();
        request.setAttribute("providers", providerList);
        request.getRequestDispatcher("/providerList.jsp").forward(request, response);
    }

    private void addProviderAndRefreshPage(HttpServletRequest request, HttpServletResponse response, Provider provider) throws ServletException, IOException {
        providerBean.addProvider(provider);
        doGet(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        Provider provider = mapper.readValue(request.getInputStream(), Provider.class);
        addProviderAndRefreshPage(request, response, provider);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        Provider updatedProvider = mapper.readValue(request.getInputStream(), Provider.class);
        providerBean.updateProvider(updatedProvider);

        doGet(request, response);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String clientIdParam = request.getParameter("providerId");
        if (clientIdParam != null && !clientIdParam.isEmpty()) {
            Integer clientId = Integer.parseInt(clientIdParam);
            providerBean.deleteProvider(clientId);
        }

        doGet(request, response);
    }
}

