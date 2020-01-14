package com.wayn.ssoserver.manager;

import com.caucho.hessian.client.HessianProxyFactory;
import com.wayn.ssocore.service.AuthcationRpcService;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;

@Component
public class HessianManager {

    @Bean(name = "/hessian/authcationRpcService")
    public HessianServiceExporter authcationRpcService(AuthcationRpcService authcationRpcService) {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setServiceInterface(AuthcationRpcService.class);
        exporter.setService(authcationRpcService);
        return exporter;
    }
    public static void main(String[] args) {
        HessianProxyFactory factory = new HessianProxyFactory();
        factory.setOverloadEnabled(true);

        try {
            AuthcationRpcService o = (AuthcationRpcService) factory.create(AuthcationRpcService.class, "http://localhost:80/hessian");
            System.out.println(o.validate("123"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
