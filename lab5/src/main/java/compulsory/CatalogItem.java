package compulsory;

import java.util.Objects;

public abstract class CatalogItem {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    String path;
    public CatalogItem(String name, String path){
        this.name = name;
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatalogItem that = (CatalogItem) o;
        return Objects.equals(name, that.name) && Objects.equals(path, that.path);
    }

    @Override
    public String
    toString() {
        return "CatalogItem{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", type='" + this.getClass() + '\'' +
                '}';
    }
}
