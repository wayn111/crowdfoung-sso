package com.wayn.ssoserver.manager;

import com.caucho.hessian.client.HessianProxyFactory;
import com.wayn.ssocore.service.AuthcationRpcService;
import com.wayn.ssocore.service.UserRpcService;
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

    @Bean(name = "/hessian/userRpcService")
    public HessianServiceExporter userRpcService(UserRpcService userRpcService) {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setServiceInterface(UserRpcService.class);
        exporter.setService(userRpcService);
        return exporter;
    }

    public static void main(String[] args) {
        HessianProxyFactory factory = new HessianProxyFactory();
        factory.setOverloadEnabled(true);
        try {
            UserRpcService o = (UserRpcService) factory.create(UserRpcService.class, "http://localhost:80/ssoserver/hessian/userRpcService");
            System.out.println(o.getUserByUserName("admin"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
