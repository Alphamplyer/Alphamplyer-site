package com.alphamplyer.website.main.proxies;

import com.alphamplyer.website.main.beans.Role;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservice-users-permissions", url = "localhost:10301")
public interface MicroserviceUsersPermissionsProxy {

    @GetMapping(value = "/roles/{id}")
    Role getRole(@PathVariable(name = "id") Integer id);

}
