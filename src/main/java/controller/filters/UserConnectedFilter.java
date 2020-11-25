package controller.filters;

import controller.DAO.AccountDAO;
import model.Account;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserConnectedFilter implements Filter {
    private static final String PARAM_SESSION_USER_ACCOUNT = "user";

    private static final String URL_REDIRECT = "/connect";

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
        if(path.startsWith("/favicon.ico") || path.startsWith("/connect") || path.startsWith("/js") || path.startsWith("/style")){
            chain.doFilter(http_request, http_response);
        }
        // Else verify actual user
        else {
            AccountDAO accountDAO = AccountDAO.getInstance();

            HttpSession session = http_request.getSession();
            Account user = (Account) session.getAttribute(PARAM_SESSION_USER_ACCOUNT);

            // If null, redirect to connection
            if(user == null){
                http_response.sendRedirect(http_request.getContextPath() + URL_REDIRECT);
            }
            // Else verify up-to-date account in database
            else {
                user = accountDAO.getAccountByName(user.getUsername());

                // If delete redirect to connection
                if(user == null){
                    http_response.sendRedirect(http_request.getContextPath() + URL_REDIRECT);
                }
                // Else continue
                else {
                    session.setAttribute(PARAM_SESSION_USER_ACCOUNT, user);
                    chain.doFilter(http_request, http_response);
                }
            }
        }

    }
}
