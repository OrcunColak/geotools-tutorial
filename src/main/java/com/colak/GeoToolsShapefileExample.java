package com.colak;

import lombok.extern.slf4j.Slf4j;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.feature.FeatureIterator;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.MultiLineString;
import org.locationtech.jts.geom.MultiPolygon;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.locationtech.jts.geom.Point;
import org.opengis.geometry.aggregate.MultiPoint;
import org.opengis.geometry.coordinate.LineString;
import org.opengis.geometry.coordinate.Polygon;

import java.io.File;

@Slf4j
public class GeoToolsShapefileExample {

    public static void main() {
        try {
            // Specify the path to your shapefile
            File file = new File("src/main/resources/linestring/china_railways.shp");

            // Load the shapefile
            FileDataStore store = FileDataStoreFinder.getDataStore(file);
            SimpleFeatureSource featureSource = store.getFeatureSource();

            // Get feature type information
            SimpleFeatureType schema = featureSource.getSchema();
            log.info("Schema: {}", schema);

            // Iterate through features
            FeatureIterator<SimpleFeature> features = featureSource.getFeatures().features();
            while (features.hasNext()) {
                SimpleFeature feature = features.next();

                // Extract attributes
                String featureId = feature.getID();
                Object geometry = feature.getDefaultGeometry();
                log.info("Feature ID: {}", featureId);
                log.info("Geometry: {}", geometry);

                // Example: If the geometry is a Point, print the coordinates
                if (geometry instanceof Point point) {
                    log.info("Coordinates: {} , {}", point.getX(), point.getY());
                } else if (geometry instanceof LineString lineString) {
                    System.out.println("LineString: " + lineString);
                } else if (geometry instanceof Polygon polygon) {
                    System.out.println("Polygon: " + polygon);
                } else if (geometry instanceof MultiPoint multiPoint) {
                    System.out.println("MultiPoint: " + multiPoint);
                } else if (geometry instanceof MultiLineString multiLineString) {
                    System.out.println("MultiLineString: " + multiLineString);
                } else if (geometry instanceof MultiPolygon multiPolygon) {
                    System.out.println("MultiPolygon: " + multiPolygon);
                } else if (geometry instanceof GeometryCollection geometryCollection) {
                    System.out.println("GeometryCollection: " + geometryCollection);
                } else {
                    System.out.println("Unknown Geometry: " + geometry);
                }
            }

            // Close the iterator
            features.close();
        } catch (Exception exception) {
            log.error("Exception : ", exception);
        }
    }
}

