package org.proto.test;

import org.proto.test.model.AppProtos;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class TestApplication {

    @Bean
    public ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}

@RestController
class RouteController {

    private List<AppProtos.Route> routes = new ArrayList<>();

    public RouteController() {
        init();
    }

    @GetMapping
    public AppProtos.Routes getRoutes() {
        return AppProtos.Routes.newBuilder().addAllRoute(this.routes).build();
    }

    private void init() {
        routes.add(buildRoute(
                "Route 1",
                buildCargo(1, "Cargo 1", "Desc 1"),
                buildCargo(2, "Cargo 2")));
        routes.add(buildRoute("Route 2"));
        routes.add(buildRoute(
                "Route 2",
                buildCargo(3, "Cargo 3", "Desc 3")));
        routes.add(buildRoute(buildCargo(4, "Cargo 4")));
        routes.add(AppProtos.Route.newBuilder().build());
    }

    private AppProtos.Cargo buildCargo(int id, String name) {
        return AppProtos.Cargo.newBuilder()
                .setId(id)
                .setName(name)
                .build();
    }

    private AppProtos.Cargo buildCargo(int id, String name, String description) {
        return AppProtos.Cargo.newBuilder()
                .setId(id)
                .setName(name)
                .setDescription(description)
                .build();
    }

    private AppProtos.Route buildRoute(AppProtos.Cargo ... cargos) {
        return AppProtos.Route.newBuilder().addAllCargo(Arrays.asList(cargos)).build();
    }

    private AppProtos.Route buildRoute(String description, AppProtos.Cargo ... cargos) {
        return AppProtos.Route.newBuilder()
                .setDescription(description)
                .addAllCargo(Arrays.asList(cargos)).build();
    }

}