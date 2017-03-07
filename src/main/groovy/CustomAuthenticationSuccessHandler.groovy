import grails.plugin.springsecurity.web.authentication.AjaxAwareAuthenticationSuccessHandler
import org.springframework.security.core.Authentication

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CustomAuthenticationSuccessHandler extends AjaxAwareAuthenticationSuccessHandler{

    def authService

    @Override
    void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                 Authentication authentication) throws ServletException, IOException {

        // register successful login attempt
        def username = "" + (authentication.getPrincipal()?.getUsername() ?: "")
        authService.registerSuccessfulLoginAttempt(username)

        // call overloaded method
        super.onAuthenticationSuccess(request, response, authentication)
    }

}
