package pro.sky.API.SwaggerUI.Postman.Service;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Service;

@Service
public class InfoServiceImp implements InfoService{
    private final ServerProperties server;
    public InfoServiceImp(ServerProperties server) {
        this.server = server;
    }

    @Override
    public Integer getPort() {
        return server.getPort();
    }
}
