package ru.otus;

import org.hibernate.LazyInitializationException;
import org.hibernate.cfg.Configuration;
import ru.otus.DataSets.UserDataSet;
import ru.otus.DbService.DbServiceImpl;
import ru.otus.DbService.DefaultConfigurationHolder;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserServlet extends HttpServlet {

    private Configuration configuration = DefaultConfigurationHolder.getConfiguration();
    private DbServiceImpl dbService = new DbServiceImpl(configuration);

    private TemplateProcessor templateProcessor = new TemplateProcessor();
    private Map<String, String[]> requestParameters = new HashMap<>();

    public UserServlet() throws IOException {
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        requestParameters = request.getParameterMap();
        if (requestParameters.containsKey("name")
                && requestParameters.containsKey("age")) {
            String userName = requestParameters.get("name")[0];
            String userAge = requestParameters.get("age")[0];
            if (!userName.isEmpty() && !userAge.isEmpty()) {
                UserDataSet user = new UserDataSet();
                user.setName(userName);
                user.setAge(Integer.parseInt(userAge));
                dbService.save(user);
                setOK(response);
                response.getWriter().println(getAdminPage(new HashMap<>()));
            } else {
                response.sendError(400, "Parameter Name or Age not filled!");
            }
        } else {
            response.sendError(400, "Parameter Name or Age not sended!");
        }

    }


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        requestParameters = request.getParameterMap();
        if (requestParameters.containsKey("id")) {
            String id = requestParameters.get("id")[0];
            if (!id.isEmpty()) {
                UserDataSet userDataSet = dbService.load(UserDataSet.class, Integer.parseInt(requestParameters.get("id")[0]));
                setOK(response);
                String userName;
                try {
                    userName = userDataSet.getName();
                } catch (LazyInitializationException e) {
                    userName = "User not found!";
                }
                response.getWriter().println(getAdminPage(Map.of("userName", userName)));
            } else {
                response.sendError(400, "Parameter id not filled!");
            }
        } else if (requestParameters.containsKey("getCount")) {
            setOK(response);
            response.getWriter().println(getAdminPage(Map.of("userCount",
                    String.valueOf(dbService.getRecordCount("UserDataSet")))));
        } else {
            setOK(response);
            response.getWriter().println(getAdminPage(new HashMap<>()));
        }
    }

    private String getAdminPage(Map<String, String> pageVariables) throws IOException {
        return templateProcessor.getPage("admin.html", pageVariables);
    }


    private void setOK(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
