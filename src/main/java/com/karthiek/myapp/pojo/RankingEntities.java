package com.karthiek.myapp.pojo;

import java.util.Date;

public class RankingEntities {

    private final int rank;
    private final String entity;
    private final Date date;

    public RankingEntities(int rank, String entity, Date date) {
        this.rank = rank;
        this.entity = entity;
        this.date = date;
    }

    public int getRank() {
        return rank;
    }

    public String getEntity() {
        return entity;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object obj) {
        final RankingEntities other = (RankingEntities) obj;

        return  other.getRank() == this.getRank() &&
                other.getEntity().equals(this.getEntity()) &&
                other.getDate().equals(this.getDate());
    }

    @Override
    public String toString() {
        return "(" + getRank() + ") - " + getEntity();
    }
}
