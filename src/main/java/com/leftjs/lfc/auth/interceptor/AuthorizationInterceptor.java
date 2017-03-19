package com.leftjs.lfc.auth.interceptor;

import com.leftjs.lfc.auth.annotation.Authorization;
import com.leftjs.lfc.auth.manager.TokenManager;
import com.leftjs.lfc.model.domain.Admin;
import com.leftjs.lfc.model.domain.Clerk;
import com.leftjs.lfc.repository.AdminRepository;
import com.leftjs.lfc.repository.ClerkRepository;
import com.leftjs.lfc.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by jason on 2017/3/12.
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter{


    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ClerkRepository clerkRepository;


    @Override
    /**
     * 没有登录就不需要传Authorization， 如果传递了Authorization后过期或者是伪造token就直接返回401，不管调的是何种接口
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        String authorization = request.getHeader("Authorization");
        if (!Utils.isNullOrEmpty(authorization)) {

            String idString = TokenManager.getSubject(authorization);
            String[] idArr = idString.split(":");
            if (idArr.length == 2) {
                switch (idArr[0]) {
                    case "admin": {
                        Admin admin = adminRepository.findOne(Long.decode(idArr[1]));
                        if (!Utils.isNullOrEmpty(admin)) {
                            request.setAttribute("currentUser", admin);
                        } else {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            return false;
                        }
                        break;
                    }
                    case "clerk": {
                        Clerk clerk = clerkRepository.findOne(Long.decode(idArr[1]));
                        if (!Utils.isNullOrEmpty(clerk)) {
                            request.setAttribute("currentUser", clerk);
                        }else {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            return false;
                        }
                        break;
                    }
                    default:
                        break;
                }
                return true;
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
        }
        //如果验证token失败，并且方法注明了Authorization，返回401错误
        if (method.getAnnotation(Authorization.class) != null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        return true;
    }
}
