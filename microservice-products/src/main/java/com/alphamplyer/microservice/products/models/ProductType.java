package com.alphamplyer.microservice.products.models;

public class ProductType {

    private Integer id;
    private Integer parentId;
    private String code;
    private String name;
    private String description;

    public ProductType () { }

    public Integer getId() {
        return id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ProductType{" +
            "id=" + id +
            ", parent_id=" + parentId +
            ", code='" + code + '\'' +
            ", description='" + description + '\'' +
            '}';
    }
}
