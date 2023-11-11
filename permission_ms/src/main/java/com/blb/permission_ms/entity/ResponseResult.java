package com.blb.permission_ms.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 向前端返回数据的格式
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult<T> {

    //响应码
    private Long code;

    //文字信息
    private String message;

    //具体数据
    private T data;


    public static ResponseResult ok(Object data){
        return new ResponseResult(200L,"ok",data);
    }

    public static ResponseResult error(Long code,String message){
        return new ResponseResult(code,message,null);
    }

    /**
     * 向流中输出结果
     * @param resp
     * @param result
     * @throws IOException
     */
    public static void write(HttpServletResponse resp, ResponseResult result) throws IOException {
        //设置返回数据的格式
        resp.setContentType("application/json;charset=UTF-8");
        //jackson是JSON解析包，ObjectMapper用于解析 writeValueAsString 将Java对象转换为JSON字符串
        String msg = new ObjectMapper().writeValueAsString(result);
        //用流发送给前端
        resp.getWriter().print(msg);
        resp.getWriter().close();
    }
}
