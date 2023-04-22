package org.openprojectx.gateway.core.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class RefreshController {
    @GetMapping("/hello/refresh")
    public String helloRefresh() {
        return "hello refresh";
    }

}
