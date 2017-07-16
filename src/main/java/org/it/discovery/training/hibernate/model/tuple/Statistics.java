package org.it.discovery.training.hibernate.model.tuple;

public class Statistics {
    private long bookCount;

    private long publisherCount;

    public Statistics(long bookCount, long publisherCount) {
        this.bookCount = bookCount;
        this.publisherCount = publisherCount;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "bookCount=" + bookCount +
                ", publisherCount=" + publisherCount +
                '}';
    }
}
