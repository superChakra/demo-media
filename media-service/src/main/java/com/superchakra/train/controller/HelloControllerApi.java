package com.superchakra.train.controller;

import com.superchakra.train.reponse.Result;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface HelloControllerApi {

    @GetMapping("/hello")
    @Operation(method = "GET",summary = "hello方法")
    Result hello(@RequestParam("name") String name);
}
