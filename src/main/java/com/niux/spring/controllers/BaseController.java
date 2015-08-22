package com.niux.spring.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by haochen208928 on 2015/8/14.
 */
public class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final String ERR_CODE = "errorCode";
    public static final String ERR_MSG = "errorMessage";
    public static final String DATA = "data";
    public static final int DEFAULT_SUCCESS_CODE = 0;
    public static final int DEFAULT_ERROR_CODE = 1;
    public static final int ERROR_CODE_PARAM_ILLEGAL  =  -1;

    public JsonObject error(int code, String msg) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(ERR_CODE, code);
        jsonObject.addProperty(ERR_MSG, msg);
        return jsonObject;
    }

    public JsonObject error(String msg) {
        return error(DEFAULT_ERROR_CODE, msg);
    }

    public JsonObject success(int code, String msg, Object data) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(ERR_CODE, code);
        jsonObject.addProperty(ERR_MSG, msg);
        if (data != null) {
            jsonObject.addProperty(DATA, new Gson().toJson(data));
        }
        return jsonObject;
    }

    public JsonObject success(String msg, Object data) {
        return success(DEFAULT_SUCCESS_CODE, msg, data);
    }

    public JsonObject success(String msg) {
        return success(DEFAULT_SUCCESS_CODE, msg, null);
    }

    public JsonObject success(Object data) {
        return success(DEFAULT_SUCCESS_CODE, "success", data);
    }
}
