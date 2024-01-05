package com.buddy.api.utils;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

public class PointBuilder {

    public Geometry createPoint(Double latitude, Double longitude) throws ParseException {
        return new WKTReader().read("POINT("+longitude+" "+latitude+")");
    }

    public Point getPoint(Double latitude, Double longitude) {
        GeometryFactory gf = new GeometryFactory();
        return gf.createPoint(new Coordinate(latitude, longitude));
    }
}
