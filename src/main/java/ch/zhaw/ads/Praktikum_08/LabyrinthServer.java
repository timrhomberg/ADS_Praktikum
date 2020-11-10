package ch.zhaw.ads.Praktikum_08;

import ch.zhaw.ads.CommandExecutor;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Iterator;

public class LabyrinthServer implements CommandExecutor {
    private Graph<LabyrinthNode,Edge> graph;
    private final ServerGraphics serverGraphics;

    public LabyrinthServer() {
        this.graph = new AdjListGraph<>(LabyrinthNode.class, Edge.class);
        this.serverGraphics = new ServerGraphics();
    }

    @Override
    public String execute(String command) {
        try {
            insert(openFile());
            /*serverGraphics.setColor(Color.BLACK);
            serverGraphics.drawRect(0.1,0.1,0.8,0.8);
            serverGraphics.fillRect(0.1,0.1,0.8,0.8);*/
            makeBlack();
            Iterable<LabyrinthNode> nodes = graph.getNodes();
            for(LabyrinthNode lNode : nodes) {
                Iterable iter = lNode.getEdges();
                for(Object e : iter) {
                    Edge edge = (Edge) e;
                    serverGraphics.setColor(Color.WHITE);
                    drawPath(serverGraphics, lNode.getName(), edge.getDest().toString(), false);
                }
            }
            return serverGraphics.getTrace();
        } catch (Throwable e) {
            throw new RuntimeException();
        }
    }

    private void makeBlack() {
        serverGraphics.setColor(Color.BLACK);
        serverGraphics.fillRect(0, 0, 1, 1);
        serverGraphics.setColor(Color.WHITE);
    }

    public boolean insert(String routes) throws Throwable {
        String[] lines = routes.split("\n");
        Iterator<String> iterator = Arrays.stream(lines).iterator();
        while (iterator.hasNext()) {
            String line = iterator.next();
            String[] elements = line.split(" ");
            graph.addEdge(elements[0], elements[1], 1);
            graph.addEdge(elements[1], elements[0], 1);
        }
        return true;
    }

    String openFile()  throws Exception {
        String labyrinth = "/Users/tim/Google Drive/_Schule/Bachelor IT/Semester_3/Algorithmen und Datenstrukturen/Praktika und Lösungen/ADS_Praktikum/src/main/java/ch/zhaw/ads/Praktikum_08/Labyrinth.txt";
        BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(labyrinth), StandardCharsets.UTF_8));
        StringBuffer b = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            b.append(line);
            b.append('\n');
        }
        return b.toString();
    }

    final double SCALE = 10;
    private void drawPath(ServerGraphics g, String from, String
            to, boolean mouse) {
        double scale = 10;
        double xh0 = from.charAt(0) - '0';
        double yh0 = from.charAt(2) - '0';
        double xh1 = to.charAt(0) - '0';
        double yh1 = to.charAt(2) - '0';
        double x0 = Math.min(xh0,xh1)/SCALE;
        double y0 = Math.min(yh0,yh1)/SCALE;
        double x1 = Math.max(xh0,xh1)/SCALE;
        double y1 = Math.max(yh0,yh1)/SCALE;
        double w = 1/SCALE;
        if (mouse) g.drawLine(x0+w/2,y0+w/2,x1+w/2,y1+w/2); else {
            if (y0 == y1) g.fillRect(x0,y0,x1-x0+w,w);
            else g.fillRect(x0,y0,w,y1-y0+w);
        } }
}
