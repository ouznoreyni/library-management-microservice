package sn.ouznoreyni.bookcatalogservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Welcome {

    @Value("${server.port}")
    private String serverPort;

    @Value("${springdoc.swagger-ui.path}")
    private String swaggerPath;

    @Value("${spring.h2.console.path}")
    private String h2ConsolePath;

    @Value("${application.openapi.dev-url}")
    private String devUrl;

    @Value("${application.openapi.prod-url}")
    private String prodUrl;

    @GetMapping("/")
    public Info getInfo() {
        return new Info(serverPort, swaggerPath, h2ConsolePath, devUrl, prodUrl);
    }

    // Inner class to represent the response structure
    static class Info {
        private String serverPort;
        private String swaggerPath;
        private String h2ConsolePath;
        private String devUrl;
        private String prodUrl;

        public Info(String serverPort, String swaggerPath, String h2ConsolePath, String devUrl, String prodUrl) {
            this.serverPort = serverPort;
            this.swaggerPath = swaggerPath;
            this.h2ConsolePath = h2ConsolePath;
            this.devUrl = devUrl;
            this.prodUrl = prodUrl;
        }

        public String getServerPort() {
            return serverPort;
        }

        public void setServerPort(String serverPort) {
            this.serverPort = serverPort;
        }

        public String getSwaggerPath() {
            return  getDevUrl() + swaggerPath;
        }

        public void setSwaggerPath(String swaggerPath) {
            this.swaggerPath = swaggerPath;
        }

        public String getH2ConsolePath() {
            return getDevUrl() + h2ConsolePath;
        }

        public void setH2ConsolePath(String h2ConsolePath) {
            this.h2ConsolePath = h2ConsolePath;
        }

        public String getDevUrl() {
            return devUrl;
        }

        public void setDevUrl(String devUrl) {
            this.devUrl = devUrl;
        }

        public String getProdUrl() {
            return prodUrl;
        }

        public void setProdUrl(String prodUrl) {
            this.prodUrl = prodUrl;
        }
    }
}
