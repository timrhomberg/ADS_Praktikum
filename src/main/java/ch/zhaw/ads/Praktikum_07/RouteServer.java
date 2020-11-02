package ch.zhaw.ads.Praktikum_07;

import ch.zhaw.ads.CommandExecutor;

import java.util.*;

public class RouteServer implements CommandExecutor {
    Graph<DijkstraNode,Edge> graph = new AdjListGraph<DijkstraNode,Edge>(DijkstraNode.class,Edge.class);
    private final boolean GREEN = true;
    private final boolean RED = false;

    @Override
    public String execute(String command) throws Exception {
        if (computeInput(command)) {
            dijkstra("Winterthur", "Lugano");
            outputPath("Winterthur", "Lugano");
            return outputPath("Winterthur", "Lugano");
        } else {
            return "Nichts i.o.";
        }
    }

    private boolean computeInput(String input) {
        try {
            String[] lines = input.split("\n");
            Iterator<String> iterator = Arrays.stream(lines).iterator();
            while (iterator.hasNext()) {
                String line = iterator.next();
                String[] elements = line.split(" ");
                graph.addEdge(elements[0], elements[1], Integer.parseInt(elements[2]));
                graph.addEdge(elements[1], elements[0], Integer.parseInt(elements[2]));
            }
            return true;
        } catch (Throwable e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    private String displayInput() {
        Iterator<DijkstraNode> graphIterator = graph.getNodes().iterator();
        StringBuilder stringBuilder = new StringBuilder();
        while (graphIterator.hasNext()) {
            DijkstraNode node = graphIterator.next();
            Iterator<Edge> edgeIterator = node.edges.iterator();
            while (edgeIterator.hasNext()) {
                Edge edge = edgeIterator.next();
                DijkstraNode edgeNode = (DijkstraNode) edge.dest;
                stringBuilder.append(node.name + " " + edgeNode.name + " Weight " + edge.weight + "\n");
            }
        }
        return stringBuilder.toString();
    }

    private void dijkstra(String from, String to) {
        DijkstraNode toNode = graph.findNode(to);
        Iterator<DijkstraNode> graphIterator = graph.getNodes().iterator();
        while (graphIterator.hasNext()) {
            DijkstraNode node = graphIterator.next();
            node.mark = Boolean.parseBoolean(null); // black = 0
            node.dist = 0;
            node.prev = null;
        }

        LinkedList<DijkstraNode> red = new LinkedList<>();
        DijkstraNode start = graph.findNode(from);
        start.dist = 0;
        red.add(start);
        DijkstraNode current = start;
        while (!red.isEmpty()) {
            if (current == toNode) return; // kürzester Pfad gefunden -> Abbruch
            if (current == null) return;
            current.mark = GREEN;
            red.remove(current); // roter Knoten mit kürzeste Distanz

            for (Object edgeObj: current.edges) {
                Edge edge = (Edge) edgeObj;
                DijkstraNode neighbourNode = (DijkstraNode) edge.dest;
                if (neighbourNode.mark != GREEN) {
                    neighbourNode.mark = RED;
                    if (!red.contains(neighbourNode)) red.add(neighbourNode);
                    double dist = current.dist + edge.weight;
                    if (neighbourNode.dist == 0) {
                        neighbourNode.dist = dist;
                        neighbourNode.prev = current;
                    } else if (dist < neighbourNode.dist) {
                        neighbourNode.dist = dist;
                        neighbourNode.prev = current;
                    }
                    //System.out.println(current.name + " " + neighbourNode.name);
                }
            }
            current = findNodeWithSmallestDist(current); // suche den besten roten Knoten
        }
    }

    private String outputPath(String from, String goal) {
        DijkstraNode lastNode = graph.findNode(goal);
        StringBuilder stringBuilder = new StringBuilder();
        while (lastNode.prev != null) {
            stringBuilder.append("Aktuell: " + lastNode.name + " Prev: " + lastNode.prev.name + " Dist: " + lastNode.dist + "\n");
            lastNode = lastNode.prev;
        }
        return stringBuilder.toString();
    }

    public DijkstraNode findNodeWithSmallestDist(DijkstraNode current) {
        Iterator<Edge> iterator = current.edges.iterator();
        double min = Double.POSITIVE_INFINITY;
        DijkstraNode foundNode = null;
        while (iterator.hasNext()) {
            Edge edge = iterator.next();
            if (edge.getWeight() < min) {
                if (((DijkstraNode) edge.dest).mark != GREEN) {
                    min = edge.getWeight();
                    foundNode = (DijkstraNode) edge.dest;
                }
            };
        }
        return foundNode;
    }

    public TreeMap findNodeWithSmallestDist2(String from, String goal) {
        DijkstraNode goalNode = graph.findNode(goal);
        TreeMap<DijkstraNode, Double> redNodes = new TreeMap<>();
        DijkstraNode startNode = graph.findNode(from);
        startNode.dist = 0;
        redNodes.put(startNode, 0.0);
        while (!redNodes.isEmpty()) {
            DijkstraNode current = redNodes.pollLastEntry().getKey(); // roter Knoten mit kürzeste Distanz
            if (current == goalNode) return null; // kürzester Pfad gefunden -> Abbruch
            current.mark = GREEN; // 'grüne' Knoten werden markiert
            for (Object neighbour : current.edges) {
                Edge neighbourEdge = (Edge) neighbour;
                DijkstraNode neighbourNode = (DijkstraNode) neighbourEdge.dest;
                if (neighbourNode.mark != GREEN) {
                    if (!redNodes.containsKey(neighbourNode)) { redNodes.put(neighbourNode, 0.0); }
                    double dist = current.dist + neighbourEdge.weight;
                    if (dist < neighbourNode.dist) {
                        neighbourNode.dist = dist;
                        neighbourNode.prev = current;
                    }
                }
            }
            //for all neighbour in successors(current) {
            //}
        }
        return redNodes;
    }
}

