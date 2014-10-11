package com.hoserdude.toboot.web;


import com.hoserdude.toboot.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//No need to subclass anything, any class can be a controller.
@Controller
public class HomeController {

    private static Logger logger = LoggerFactory.getLogger(HomeController.class);
    /**
     * Names of the methods mean nothing, it's all about the RequestMapping defined above.
     * The RequestMapping is your Routing rule - all GET hits to the root of the context come here
     *
     * @param model where did this come from?  Automatically injected for your convenience.
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model, Authentication authentication) {
        User user = (User)authentication.getPrincipal();
        logger.info("User {} logged in.", user.getFullName());
        model.addAttribute("name", user.getFullName());
        return "home";
    }

    @RequestMapping("/foo")
    public String foo() {
        throw new RuntimeException("Expected exception in controller");
    }

}
