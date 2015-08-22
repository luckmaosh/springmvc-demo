package com.niux.spring.controllers;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by maoshaohui [maoshh88@gmail.com]
 * on 15/8/22.下午1:22
 * <p/>
 * 文件下载
 */

@Path("file")
public class FileController {

    public static final String ROOT_FILE = "/opt/";

    @Get("download")
    public void downLoad(Invocation inv, @Param("fileName") String downFilename) throws IOException {

        HttpServletResponse response = inv.getResponse();

        response.setContentType("text/plain");
        response.setHeader("Location", downFilename);
        response.setHeader("Content-Disposition", "attachment; filename=" + downFilename);
        OutputStream outputStream = response.getOutputStream();

        String filepath = ROOT_FILE + downFilename;
        InputStream inputStream = new FileInputStream(filepath);
        byte[] buffer = new byte[1024];
        int i = -1;
        while ((i = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, i);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

}
