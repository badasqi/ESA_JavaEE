package Servlets;


import Bean.ProviderBean;
import Entity.Provider;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import Bean.ClientBean;
import Entity.Client;
import java.util.List;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/clients")
public class ClientListServlet extends HttpServlet {

    @EJB
    private ClientBean clientBean;

    @EJB
    private ProviderBean providerBean;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Client> clientList = clientBean.getAllClients();

        request.setAttribute("clients", clientList);
        request.getRequestDispatcher("/clientList.jsp").forward(request, response);
    }

    private void addClientAndRefreshPage(HttpServletRequest request, HttpServletResponse response, Client client) throws ServletException, IOException {
        clientBean.addClient(client);
        doGet(request, response);
    }

    //protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
     //   ObjectMapper mapper = new ObjectMapper();
    //    Client client = mapper.readValue(request.getInputStream(), Client.class);
     //   addClientAndRefreshPage(request, response, client);
   // }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        Client client = mapper.readValue(request.getInputStream(), Client.class);

        // Получение имени провайдера из JSON
        String providerName = client.getProviderName();
        // Извлечение провайдера из базы данных по имени
        Provider foundProvider = providerBean.getProviderByName(providerName);
        client.setProvider(foundProvider);
        // Добавляем клиента в базу данных
        addClientAndRefreshPage(request, response, client);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        Client updatedClient = mapper.readValue(request.getInputStream(), Client.class);
        clientBean.updateClient(updatedClient);

        doGet(request, response);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String clientIdParam = request.getParameter("clientId");
        if (clientIdParam != null && !clientIdParam.isEmpty()) {
            Integer clientId = Integer.parseInt(clientIdParam);
            clientBean.deleteClient(clientId);
        }
        doGet(request, response);
    }
}

