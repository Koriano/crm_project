package controller.filters;

import model.Account;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * A filter that checks if the user has admin rights
 *
 * @author Alexandre HAMON
 */
public class UserAdminFilter implements Filter {
    /**
     * Session attributes
     */
    private static final String PARAM_SESSION_USER_ACCOUNT = "user";

    /**
     * View redirection
     */
    private static final String URL_REDIRECT = "/home";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Cast request and response into HTTP
        HttpServletRequest http_request = (HttpServletRequest) request;
        HttpServletResponse http_response = (HttpServletResponse) response;

        // Get session and get user account
        HttpSession session = http_request.getSession();
        Account user = (Account) session.getAttribute(PARAM_SESSION_USER_ACCOUNT);

        // If user has rights, continue
        if("Administrateur".equals(user.getRight())){
            chain.doFilter(http_request, http_response);
        }
        // Else redirect
        else {
            http_response.sendRedirect(http_request.getContextPath() + URL_REDIRECT);
        }
    }
}
