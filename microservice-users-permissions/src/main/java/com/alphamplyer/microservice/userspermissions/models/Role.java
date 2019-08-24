package com.alphamplyer.microservice.userspermissions.models;

public class Role {

    private Integer id;
    private Integer index;
    private String name;

    public Integer getId() { return id; }
    public Integer getIndex() { return index; }
    public String getName() { return name; }

    public void setId(Integer id) { this.id = id; }
    public void setIndex(Integer index) { this.index = index; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "Role{" +
            "id=" + id +
            ", index=" + index +
            ", name='" + name + '\'' +
            '}';
    }
}
