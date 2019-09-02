package com.alphamplyer.website.main.proxies;

import com.alphamplyer.website.main.beans.Role;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservice-users-permissions", url = "localhost:10301")
public interface MicroserviceUsersPermissionsProxy {

    /**
     * Get role from its ID
     * @param id role ID
     * @return role or a NotFoundException error
     */
    @GetMapping(value = "/roles/{id}")
    Role getRole(@PathVariable(name = "id") Integer id);

}
