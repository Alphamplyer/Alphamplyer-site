package com.alphamplyer.microservice.products.models;

/**
 * Represent product type data
 */
public class ProductType {

    private Integer id;
    private Integer parentId;
    private String code;
    private String name;
    private String description;

    public ProductType () { }

    /**
     * Get product type ID
     * @return product type ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Get parent ID
     * @return parent ID
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * Get product type code
     * @return product type code
     */
    public String getCode() {
        return code;
    }

    /**
     * Get product type name
     * @return product type name
     */
    public String getName() {
        return name;
    }

    /**
     * Get product type description
     * @return product type description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Define product type ID
     * @param id product type ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Define product type parent ID
     * @param parentId product type parent ID
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * Define product type code
     * @param code product type code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Define product type name
     * @param name product type name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Define product type description
     * @param description product type description
     */
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
