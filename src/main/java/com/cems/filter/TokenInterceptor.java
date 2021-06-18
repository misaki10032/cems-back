package com.cems.filter;


import com.cems.util.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object hander) throws Exception {
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        System.out.println("得到的token是"+token);
        boolean verify = JWTUtil.verify(token);
        if (!verify){
            Map<String,Object> map = new HashMap<>();
            map.put("code",401);
            map.put("msg","登录失效");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValueAsString(map);
            PrintWriter writer = response.getWriter();
            writer.print(objectMapper.writeValueAsString(map));
            writer.flush();
            writer.close();
            System.out.println("失败");
            return false;
        }
        System.out.println("成功");
        return true;
    }

}