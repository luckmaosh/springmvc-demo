package com.niux.spring.controllers;

import com.niux.spring.car.service.CarSeatService;
import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@Path("/car")
public class CarController {

    @Autowired
    private CarSeatService carSeatService;

    @Get("regiter")
    public String downLoad(Invocation inv) throws IOException {

        return "@ddd";

    }
}
