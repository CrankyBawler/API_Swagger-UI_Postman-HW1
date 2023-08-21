package pro.sky.API.SwaggerUI.Postman.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.API.SwaggerUI.Postman.Service.InfoService;

import javax.sound.sampled.Port;

@RestController
public class InfoController {
    private final InfoService infoService;

    public InfoController (InfoService infoService){
        this.infoService = infoService;
    }
    @GetMapping("/getPort")
    public Integer getPort() {
        return infoService.getPort();
    }

    @GetMapping("/getSum")
    public Integer getSum() {
        return infoService.getSum();
    }
    }
