package pro.sky.API.SwaggerUI.Postman.Service;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class InfoServiceImp implements InfoService {
    private final ServerProperties server;

    public InfoServiceImp(ServerProperties server) {
        this.server = server;
    }

    @Override
    public Integer getPort() {
        return server.getPort();
    }

    public int getSum() {
      //  int sum = Stream.iterate(1, a -> a + 1).limit(1_000_000).reduce(0, (a, b) -> a + b);
       return Stream
               .iterate(1, a -> a + 1)
               .parallel()
               .limit(1_000_000)
               .reduce(0, (a, b) -> a + b);

    }
}
