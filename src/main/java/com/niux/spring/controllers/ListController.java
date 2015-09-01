package com.niux.spring.controllers;

import com.google.gson.JsonObject;
import com.niux.spring.service.DemoService;
import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

/**
 * Created by maoshaohui [maoshh88@gmail.com]
 * on 15/8/22.下午1:28
 */
@Path("list")
public class ListController extends BaseController {


    @Get("dir")
    public JsonObject list(Invocation inv) {


        File file = new File(FileController.ROOT_FILE);
        File[] tempList = file.listFiles();
        System.out.println("该目录下对象个数：" + tempList.length);
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                System.out.println("文     件：" + tempList[i]);
            }
            if (tempList[i].isDirectory()) {
                System.out.println("文件夹：" + tempList[i]);
            }
        }

        String[] list = file.list();
        return success(list);
    }
}
