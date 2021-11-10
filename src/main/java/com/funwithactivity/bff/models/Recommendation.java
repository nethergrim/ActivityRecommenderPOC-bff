package com.funwithactivity.bff.models;

import java.util.Objects;

public class Recommendation implements Comparable<Recommendation> {

    private final int priority;
    private final String title;
    private final String details;

    public Recommendation(int priority, String title, String details) {
        this.priority = priority;
        this.title = title;
        this.details = details;
    }

    public int getPriority() {
        return priority;
    }

    public String getTitle() {
        return title;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recommendation that = (Recommendation) o;

        if (!title.equals(that.title)) return false;
        return Objects.equals(details, that.details);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + (details != null ? details.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Recommendation{" +
                "priority=" + priority +
                ", title='" + title + '\'' +
                ", details='" + details + '\'' +
                '}';
    }

    @Override
    public int compareTo(Recommendation o) {
        return Integer.compare(o.priority, this.priority);
    }
}
