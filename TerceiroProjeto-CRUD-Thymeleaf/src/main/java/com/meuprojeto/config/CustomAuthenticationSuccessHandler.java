package com.meuprojeto.config;

import com.meuprojeto.model.User;
import com.meuprojeto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, 
                                      HttpServletResponse response, 
                                      Authentication authentication) throws IOException, ServletException {
        
        User user = userService.findByUsername(authentication.getName());
        
        if (user != null && !user.isSenhaTrocada() && userService.isDefaultPassword(user)) {
            // Redireciona para troca de senha se ainda usar a senha padrão
            getRedirectStrategy().sendRedirect(request, response, "/trocar-senha");
            return;
        }
        
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
