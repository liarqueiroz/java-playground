package com.liarqueiroz.streams;

public class Constellation {
    private Integer id;
    private String name;
    private Integer amountOfStars;
    private Boolean isVisibleInBrazil;

    public Constellation(Integer id, String name, Integer amountOfStars, Boolean isVisibleInBrazil) {
        this.id = id;
        this.name = name;
        this.amountOfStars = amountOfStars;
        this.isVisibleInBrazil = isVisibleInBrazil;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmountOfStars() {
        return amountOfStars;
    }

    public void setAmountOfStars(Integer amountOfStars) {
        this.amountOfStars = amountOfStars;
    }

    public Boolean getVisibleInBrazil() {
        return isVisibleInBrazil;
    }

    public void setVisibleInBrazil(Boolean isVisibleInBrazil) {
        isVisibleInBrazil = isVisibleInBrazil;
    }

    @Override
    public String toString() {
        return "Constellation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amountOfStars=" + amountOfStars +
                ", isVisibleInBrazil=" + isVisibleInBrazil +
                '}';
    }

    public void addStar() {
        this.amountOfStars = this.amountOfStars + 1;
    }
}
