package com.buddy.api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.locationtech.jts.geom.Point;


@Entity
@Getter
public class Pool {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pool_id")
    private int id;
    private String name;
    private String region;
    private int depth;
    private Boolean equipmentRent;
    @Column(columnDefinition = "GEOMETRY")
    private Point location;


    public Pool() {
    }

    public Pool(String name, String region, int depth, Boolean equipmentRent, Point location) {
        this.name = name;
        this.region = region;
        this.depth = depth;
        this.equipmentRent = equipmentRent;
        this.location = location;
    }

    public static Pool of(String name, String region, int depth, Boolean equipmentRent, Point location) {
        return new Pool(name, region,depth,equipmentRent,location);
    }

    public void updateTo(String name, String region, int depth, Boolean equipmentRent, Point location) {
        this.name = name;
        this.region = region;
        this.depth = depth;
        this.equipmentRent = equipmentRent;
        this.location = location;
    }
}
