import javafx.scene.Node;
import java.util.List;
import java.util.*;

import java.util.NoSuchElementException;

public class ListGraph {

    private Map<Node, Set<Edge>> nodes = new HashMap<>();
    private List<Edge> edges;
    private List<Node> visitedNodes;

    public void add(Node node) {
        nodes.put(node);
    }

    public void remove(Node node) throws NoSuchElementException {
        if(!nodes.containsKey(node)) {
            throw new NoSuchElementException();
        }
        for(Edge edge : getEdgesFrom(node)) {
            disconnect(edge.getDestination(), node);
            edges.remove(edge);
        }
        nodes.remove(node);
    }

    public void connect(Node startNode, Node endNode, String connection, int weight) throws NoSuchElementException, IllegalArgumentException, IllegalStateException {
        if(!nodes.containsKey(startNode) || !nodes.containsKey(endNode)) {
            throw new NoSuchElementException();
        }
        if(weight < 0){
            throw new IllegalArgumentException();
        }
        Edge edge = new Edge(connection, weight, startNode, endNode);
    }

    public void disconnect(Node node1, Node node2) throws NoSuchElementException, IllegalStateException {

    }

    public void setConnectionWeight(Node node1, Node node2, int weight) throws NoSuchElementException, IllegalArgumentException {

    }

    public Map<Node, Set<Edge>> getNodes() {
        return nodes;
    }

    public List<Edge> getEdgesFrom(Node node) throws NoSuchElementException{
        return null;
    }

    public Edge getEdgeBetween(Node node1, Node node2) throws NoSuchElementException{
        for(Edge edge : edges) {
            if(edge.)
        }
        return null;
    }

    public String toString(){
        return "";
    }

    public boolean pathExists(Node currentNode, Node destination) {
        visitedNodes.add(currentNode);
        if (currentNode.equals(destination)) {
            return true;
        }
        for (Edge edge : getNodes().get(currentNode)) {
            if (!visitedNodes.contains(edge.getDestination())) {
               if (pathExists(edge.getDestination(), destination)){
                   return true;
               }
            }
        }
        return false;
    }

    public List<Edge> getPath(Node node1, Node node2) {
        return null;
    }

//    private boolean recursiveVisitAll(City city, City searchingFor, Set<City> visited) {
//        visited.add(city);
//        if (city.equals(searchingFor)) {
//            return true;
//        }
//        for (Edge e : cities.get(city)) {
//            if (!visited.contains(e.getDestination())) {
//                if (recursiveVisitAll(e.getDestination(), searchingFor, visited)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
}
