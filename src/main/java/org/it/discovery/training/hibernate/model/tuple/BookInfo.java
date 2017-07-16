package org.it.discovery.training.hibernate.model.tuple;

public class BookInfo {
    private final int id;

    private final String name;

    public BookInfo(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
