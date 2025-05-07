import javafx.scene.Node;

public class Edge {

    private String name;
    private int weight;
    private Node startNode;
    private Node endNode;

    public Edge(String name, int weight, Node startNode, Node endNode) {
        this.name = name;
        this.weight = weight;
        this.startNode = startNode;
        this.endNode = endNode;
    }

    public Node getDestination(){
        return endNode;
    }

    public int getWeight(){
        return weight;
    }

    public void setWeight(int newWeight){
        try {
            if(newWeight >= 0) {
                weight = newWeight;
            } else {
                throw new IllegalArgumentException("Weight must be greater than or equal to zero.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Weight must be an integer.");

        }
    }

    public String getName(){
        return name;
    }

    public String toString(){
        return "Name: " + getName() + ", Weight: " + getWeight() + ", Start: " + startNode + ", End: " + endNode;
    }

}
