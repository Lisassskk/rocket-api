package com.github.alenfive.rocketapi.controller;

import com.github.alenfive.rocketapi.RocketAPIApplication;
import com.github.alenfive.rocketapi.config.RocketApiProperties;
import com.github.alenfive.rocketapi.datasource.DataSourceManager;
import com.github.alenfive.rocketapi.utils.LoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Api ui 页面显示
 */
@Controller
@RequestMapping("${spring.rocket-api.base-path:/api-ui}")
public class ViewController {

    @Autowired
    private DataSourceManager dataSourceManager;

    @Autowired
    private RocketApiProperties properties;

    @Value("${spring.application.name}")
    private String service;

    @GetMapping
    public String index(Model model,HttpServletRequest request){
        model.addAttribute("dataSourceList",dataSourceManager.getDialectMap().keySet());
        model.addAttribute("user", LoginUtils.getUser(request));
        model.addAttribute("service", service);
        model.addAttribute("basePath",buildBasePath(request));
        model.addAttribute("version", getVersion());
        return "rocketapi/api-index";
    }

    @GetMapping("/{id}/{page}")
    public String index(Model model, @PathVariable String id, @PathVariable String page, HttpServletRequest request){
        model.addAttribute("dataSourceList",dataSourceManager.getDialectMap().keySet());
        model.addAttribute("currApi",id);
        model.addAttribute("currPage",page);
        model.addAttribute("user", LoginUtils.getUser(request));
        model.addAttribute("service", service);
        model.addAttribute("basePath",buildBasePath(request));
        model.addAttribute("version",getVersion());
        return "rocketapi/api-index";
    }

    public static String getVersion() {
        Package pkg = RocketAPIApplication.class.getPackage();
        return (pkg != null ? pkg.getImplementationVersion() : null);
    }

    private String buildBasePath(HttpServletRequest request){
        String basePath = request.getContextPath()+properties.getBasePath();
        basePath = basePath.replace("//","/");
        if (basePath.endsWith("/")){
            basePath = basePath.substring(0,basePath.length()-1);
        }
        return basePath;
    }
}
