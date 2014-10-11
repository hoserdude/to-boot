package com.hoserdude.toboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(@RequestParam(value="name", required=false, defaultValue="dude") String name,
                        Model model) {
        //Models are just maps of keys + Objects, put whatever you want.  It will get passed to the
        //templating engine for rendering server-side templates. (see index.html).
        model.addAttribute("message", "Welcome To ToBoot " + name);
        model.addAttribute("title", "ToBoot");
        return "index";
    }
}
