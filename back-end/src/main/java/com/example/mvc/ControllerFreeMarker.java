package com.example.mvc;

import com.example.mvc.error.ServerUnavailableException;
import com.example.mvc.models.DataModel;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@Log4j2
class ControllerFreeMarker {

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
    Object hello(ModelMap model,
                 @RequestParam(value = "name", required = false, defaultValue = "World") String name,
                 @RequestParam(value = "page", required = false, defaultValue = "1") Integer page) {
        log.info("we use MVC");
        model.addAttribute("name", name);
        return "hello";
    }

    @GetMapping(value = "/hello/json",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    DataModel hello2(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
                     @RequestParam(value = "page", required = false, defaultValue = "1") Integer page
    ) {
        log.info("we use MVC json");
        return new DataModel("asdasdads");
    }

    @GetMapping("/simulate/error/server")
    String simulate(ModelMap modelMap) {
        if (true)
            throw new ServerUnavailableException();
        return "";
    }

}