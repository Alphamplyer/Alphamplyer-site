package com.alphamplyer.website.websiteadministration.beans.users_permissions;

public class Role {

    private Integer id;
    private Integer index;
    private String name;

    /**
     * Get role ID
     * @return role ID
     */
    public Integer getId() { return id; }

    /**
     * Get role index
     * @return role index
     */
    public Integer getIndex() { return index; }

    /**
     * Get role name
     * @return role name
     */
    public String getName() { return name; }


    /**
     * Define role ID
     * @param id role ID
     */
    public void setId(Integer id) { this.id = id; }

    /**
     * Define role index
     * @param index role index
     */
    public void setIndex(Integer index) { this.index = index; }

    /**
     * Define role name
     * @param name role name
     */
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
