package com.example.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
class ControllerFreeMarker {
    private static final Logger logger = LoggerFactory.getLogger(ControllerFreeMarker.class);

    /**
     * User go to http://localhost:8092/hello?name=User
     * <li>Front Controller get this request and sent them to ControllerFreeMarker</li>
     * <li>ControllerFreeMarker create model by parameters(name=User)</li>
     * <li>Front Controller get model and send it to View Template</li>
     * <li>View Template find template(hello.ftl) by application.yml and ControllerFreeMarker(hello), after it create HTML</li>
     * <li>Front Controller send HTML to user</li>
     * More details look at https://javarevisited.blogspot.com/2017/08/difference-between-restcontroller-and-controller-annotations-spring-mvc-rest.html
     *
     * @param model in which we put data
     * @param name  is parameter in URL
     * @return name of template
     */
    @GetMapping({"/hello"})
    String hello(ModelMap model,
                 @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        logger.info("we use MVC");
        model.addAttribute("name", name);
        return "hello";
    }

}