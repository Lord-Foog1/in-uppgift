import javafx.scene.Node;
import java.util.List;
import java.util.*;

import java.util.NoSuchElementException;

public class ListGraph {

    private Map<Node, Set<Edge>> nodes = new HashMap<>();
    private List<Edge> edges;
    private List<Node> visitedNodes;

    public void add(Node node) {
        nodes.putIfAbsent(node, new HashSet<>());
    }

    public void remove(Node node) throws NoSuchElementException {
        if(!nodes.containsKey(node)) {
            throw new NoSuchElementException("Node not found");
        }
        for(Edge edge : getEdgesFrom(node)) {
            disconnect(edge.getDestination(), node);
            edges.remove(edge);
        }
        nodes.remove(node);
    }

    public void connect(Node startNode, Node endNode, String connection, int weight) throws NoSuchElementException, IllegalArgumentException, IllegalStateException {
        if(!nodes.containsKey(startNode) || !nodes.containsKey(endNode)) {
            throw new NoSuchElementException("Node not found");
        }
        if(weight < 0){
            throw new IllegalArgumentException("Weight cannot be negative");
        }
        if(getEdgeBetween(startNode, endNode) != null) {
            throw new IllegalStateException("Node already connected");
        }

        Edge edge1 = new Edge(connection, weight, startNode, endNode);
        Edge edge2 = new Edge(connection, weight, endNode, startNode);

        nodes.get(startNode).add(edge1);
        nodes.get(endNode).add(edge2);
    }

    public void disconnect(Node node1, Node node2) throws NoSuchElementException, IllegalStateException {
        if(!nodes.containsKey(node1) || nodes.containsKey(node2)) {
            throw new NoSuchElementException("Node not found");
        }

        Edge edge1 = getEdgeBetween(node1, node2);
        Edge edge2 = getEdgeBetween(node2, node1);

        if (edge1 == null || edge2 == null) {
            throw new IllegalStateException("Edge not found");
        }

        nodes.get(node1).remove(edge1);
        nodes.get(node2).remove(edge2);
    }

    public void setConnectionWeight(Node node1, Node node2, int weight) throws NoSuchElementException, IllegalArgumentException {
        if(!nodes.containsKey(node1) || nodes.containsKey(node2)){
            throw new NoSuchElementException("Node not found!");
        }

        Edge edge1 = getEdgeBetween(node1, node2);
        Edge edge2 = getEdgeBetween(node2, node1);

        if (edge1 == null || edge2 == null) {
            throw new IllegalStateException("Edge not found");
        }

        edge1.setWeight(weight);
        edge2.setWeight(weight);
    }

    public Map<Node, Set<Edge>> getNodes() {
        Map<Node, Set<Edge>> nodeMap = new HashMap<>(nodes);
        return nodeMap;
    }

    public List<Edge> getEdgesFrom(Node node) throws NoSuchElementException{
        if(!nodes.containsKey(node)) {
            throw new NoSuchElementException("Node not found");
        }
        List<Edge> ed = new ArrayList<>();
        for(Edge edge : edges){
            if(edge.getDestination().equals(node)){
                ed.add(edge);
            }
        }
        return ed;
    }

    public Edge getEdgeBetween(Node node1, Node node2) throws NoSuchElementException{
        if (!nodes.containsKey(node1) || !nodes.containsKey(node2)) {
            throw new NoSuchElementException("Node not found");
        }

        for(Edge edge : nodes.get(node1)) {
            if(edge.getDestination().equals(node2)) {
                return edge;
            }
        }
        return null;
    }

    public String toString(){
        return  edges.toString() + "\n" + nodes.toString();
    }

    public boolean pathExists(Node currentNode, Node destination) {
        if (!nodes.containsKey(currentNode) || !nodes.containsKey(destination)) {
            return false;
        }
        return dfs(currentNode, destination, new HashSet<>(), new ArrayList<>());
    }

    public List<Edge> getPath(Node node1, Node node2) {
        if(!nodes.containsKey(node1) || !nodes.containsKey(node2)) {
            return null;
        }

        Set<Node> visited = new HashSet<>();
        List<Edge> path = new ArrayList<>();

        if (dfs(node1, node2, visited, path)) {
            return path;
        }
        return null;
    }

    private boolean dfs(Node currentNode, Node destination, Set<Node> visited, List<Edge> path) {
        visited.add(currentNode);
        if (currentNode.equals(destination)) {
            return true;
        }

        for (Edge edge : nodes.get(currentNode)) {
            Node nextNode = edge.getDestination();
            if (!visited.contains(nextNode)) {
                path.add(edge);
                if (dfs(nextNode, destination, visited, path)) {
                    return true;
                }
                path.remove(path.size() - 1);
            }
        }
        return false;
    }
}
