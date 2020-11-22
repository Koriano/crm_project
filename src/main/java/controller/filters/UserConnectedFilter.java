package controller.filters;

import model.Account;
import model.Contact;
import model.Sector;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class UserConnectedFilter implements Filter {
    private static final String PARAM_SESSION_USER_ACCOUNT = "user";

    private static final String URL_REDIRECT = "/connection";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest http_request = (HttpServletRequest) request;
        HttpServletResponse http_response = (HttpServletResponse) response;

        // If standard resources or connection page, do not filter
        String path = http_request.getRequestURI().substring(http_request.getContextPath().length());
        if(path.startsWith("/connection") || path.startsWith("/js") || path.startsWith("/style")){
            chain.doFilter(http_request, http_response);
        }
        else {
//            AccountDAO accountDAO = AccountDAO.getInstance();

            HttpSession session = http_request.getSession();
            Account user = (Account) session.getAttribute(PARAM_SESSION_USER_ACCOUNT);

            if(user == null){
                http_response.sendRedirect(http_request.getContextPath() + URL_REDIRECT);
            }
            else {
//                user = accountDAO.getAccountByUsername(user.getName());

                // SIMULATION A SUPPR ****************
                Contact contact = new Contact("Hamon", "Alexandre", "Eleve", null, false, 0);
                ArrayList<Sector> sectors = new ArrayList<>();
                sectors.add(new Sector("slt"));

                user = new Account("alex29", "1234azerty", "Alex", "Administrateur", contact, sectors);
                // ***********************************
                if(user == null){
                    http_response.sendRedirect(http_request.getContextPath() + URL_REDIRECT);
                }
                else {
                    chain.doFilter(http_request, http_response);
                }
            }
        }

    }
}
