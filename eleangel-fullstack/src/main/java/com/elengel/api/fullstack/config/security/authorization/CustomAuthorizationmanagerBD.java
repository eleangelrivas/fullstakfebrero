package com.elengel.api.fullstack.config.security.authorization;

import com.elengel.api.fullstack.persistence.entity.security.Operation;
import com.elengel.api.fullstack.persistence.repository.security.OperationRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CustomAuthorizationmanagerBD implements AuthorizationManager<RequestAuthorizationContext> {
    @Autowired
    private OperationRepository operationRepository;

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext requestContext) {
        HttpServletRequest request = requestContext.getRequest();
        System.out.println("la url "+request.getRequestURL());//host
        System.out.println("la uri "+request.getRequestURI());//base path

        String url = extractURL(request);
        String httpMethod = request.getMethod();
        boolean isPublic = isPublico(url,httpMethod);

        return new AuthorizationDecision(true);

    }

    private boolean isPublico(String url,String httpMethod) {
        List<Operation> publicAccessEndPoint = operationRepository.findByPubliccAccess();
        boolean isPublic = publicAccessEndPoint.stream().anyMatch(each->{
            String basePath = each.getModule().getBasePath();
            Pattern pattern = Pattern.compile(basePath.concat(each.getPath()));
            Matcher matcher = pattern.matcher(url);
            return matcher.matches() && each.getHttpMethod().equals(httpMethod);
        });

        System.out.println("Verificacion de endpoint publico ? : "+isPublic+" la url: "+url);
        return isPublic;

    }

    private String extractURL(HttpServletRequest request) {

        String contextPath = request.getContextPath();
        String url = request.getRequestURI();
        url = url.replace(contextPath,"");
        System.out.println("Url sin contexpath : "+url);
        return url;


    }
}
