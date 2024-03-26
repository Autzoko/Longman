package com.wms.longman.utilities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Result<T> {
    private String code;    //状态码
    private String msg;     //携带信息
    private T data;         //返回数据类型

    //Constructor with args
    public Result(T data) {
        this.data = data;
    }

    //Return success info without args
    public static Result success() {
        Result<Object> objectResult = new Result<>();
        objectResult.setCode("200");
        objectResult.setMsg("success");
        return objectResult;
    }

    //Return success info with args
    public static <T> Result<T> success(T data) {
        Result<T> tResult = new Result<>(data);
        tResult.setCode("200");
        tResult.setMsg("success");
        return tResult;
    }

    //Return error info without args
    public static Result error(String code, String msg) {
        Result<Object> objectResult = new Result<>();
        objectResult.setCode(code);
        objectResult.setMsg(msg);
        return objectResult;
    }

    //Return error info with args
    public static <T> Result<T> error(String code, String msg, T data) {
        Result<T> tResult = new Result<>(data);
        tResult.setCode(code);
        tResult.setMsg(msg);
        return tResult;
    }
}
