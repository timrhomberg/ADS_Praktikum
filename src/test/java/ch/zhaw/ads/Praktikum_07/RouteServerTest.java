package ch.zhaw.ads.Praktikum_07;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.*;

public class RouteServerTest {
    private RouteServer routeServer = new RouteServer();

    @Test
    public void testRouteServer() throws Exception {
        routeServer.execute(openFile("/Users/tim/Google Drive/_Schule/Bachelor IT/Semester_3/Algorithmen und Datenstrukturen/Praktika und Lösungen/ADS_Praktikum/src/main/java/ch/zhaw/ads/Praktikum_07/Swiss.txt"));

    }

    private String openFile(String name)  throws Exception {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(name), "ISO-8859-1"));
        StringBuffer b = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            b.append(line);
            b.append('\n');
        }
        return b.toString();
    }
}
