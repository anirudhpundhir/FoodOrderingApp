package com.upgrad.FoodOrderingApp.service.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Table(name = "category")
@NamedQueries(
        {
                @NamedQuery(name = "allCategory", query = "select c from CategoryEntity c order by c.categoryName"),
                @NamedQuery(name = "categoryById", query = "select c from CategoryEntity c where c.uuid = :uuid"),
        }
)
public class CategoryEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "UUID")
    @NotNull
    @Size(max = 200)
    private UUID uuid;

    @Column(name = "CATEGORY_NAME")
    @NotNull
    @Size(max = 255)
    private String categoryName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}