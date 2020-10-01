package ch.zhaw.ads.Praktikum_03;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.*;

public class RankingServerTest {
    RankingServer rankingServer;

    @Before
    public void setUp() throws Exception {
        rankingServer = new RankingServer();
    }

    @Test
    public void testXMLFile() throws Exception {
        String validXMLFilePath = "/Users/tim/Google Drive/_Schule/Bachelor IT/Semester_3/Algorithmen und Datenstrukturen/Praktika und Lösungen/ADS_Praktikum/src/main/resources/RangZuerich.csv";
        String validFile = openFile(validXMLFilePath);
        System.out.println(rankingServer.execute(validFile));
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
