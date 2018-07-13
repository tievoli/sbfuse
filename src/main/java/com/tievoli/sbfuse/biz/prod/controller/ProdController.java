package com.tievoli.sbfuse.biz.prod.controller;

import com.tievoli.sbfuse.framework.http.RestResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 商品控制器.
 */
@RestController
@RequestMapping("/prod")
@Api(tags = "商品服务相关接口")
public class ProdController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/sslProvider")
    public RestResponse providerService() {
        return new RestResponse();
    }

    @GetMapping("/testSSL")
    public ResponseEntity<RestResponse> consumerService() {
        String url = "https://sbfuse.com:8443/prod/sslProvider";

        ResponseEntity<RestResponse> result
                = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<RestResponse>() {
        });

        return result;
    }

}
