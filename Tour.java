import edu.princeton.cs.algs4.*;

public class Tour {

    private class Node{
        private Point p;
        private Node next;
        public Node(Point p){
            this.p = p;
            this.next = null;
        }
    }
    /*
 * Variables
 *
 *
 * * */
    private int n;
    private Node first; //first node in linked list

    public Tour(){
        first = null;
    }


    //show values.. in StdOut
    public void show() {
        if (first == null) {
            throw new RuntimeException("First is null: no points");
        }
        Node thisNode = first;
        if (thisNode != null) {
            do {
                StdOut.println(thisNode.p);
                thisNode = thisNode.next;
            } while ( thisNode != first);
        }
    }
    //draw it... duh
    public void draw() {
        if ( first.p != null) {
            Node thisNode = first;
            do {
                thisNode.p.drawTo(thisNode.next.p);
                thisNode = thisNode.next;
            } while (thisNode.equals(first));
        }
    }

    //number of points on tour
    public int size() {
        Node thisNode = first;
        int count = 0;
        while(thisNode.next != first){
            count++;

        }
        return count;
    }



//    private void insertPoint(Point p, Node node) {
//        if (node.next == null) {
//            throw new RuntimeException("Not attached to list... error..");
//        }
//
//        Node insertionNode = new Node();
//        insertionNode.next = node.next;
//        node.next = insertionNode;
//    }

    double distance() {
        double distance = 0;
        Node thisNode = first;

        if (thisNode != null) {
            do {
                distance += thisNode.p.distanceTo(thisNode.next.p);
                thisNode = thisNode.next;
            } while (thisNode != first);
        }
        return distance;
    }

    //insert p using nearest neighbor heuristic
    public void insertNearest(Point p){

        if (first == null) { //this is for if the first point node is given..
            first = new Node(p);
            first.next = first;
        } else {
            Node thisNode = first;
            double currentDist = thisNode.p.distanceTo(p);
            Node nearestNode =  thisNode;
            double nearestDist = currentDist;

            do {
                if (nearestDist > currentDist) {
                    nearestNode = thisNode;
                    nearestDist = currentDist;
                }
                thisNode = thisNode.next;
                currentDist = thisNode.p.distanceTo(p);
            } while ( thisNode != first);

            Node insertionNode = new Node(p);
            insertionNode.next = nearestNode.next;
            nearestNode.next = insertionNode;
        }
    }

    //insert p using nearest neighbor heuristic
    public void insertSmallest(Point p){
        if ( first == null){
            first = new Node(p);
            first.next = first;
        } else {
            Node thisNode = first;
            double distInc = thisNode.p.distanceTo(p) + p.distanceTo(thisNode.next.p) - thisNode.p.distanceTo(thisNode.next.p);

            Node idealNode = thisNode;
            double smallestDist = distInc;
            do {
                if(smallestDist > distInc) {
                    idealNode = thisNode;
                    smallestDist = distInc;
                }
                thisNode = thisNode.next;
                distInc = thisNode.p.distanceTo(p)
                        + p.distanceTo(thisNode.next.p)
                        - thisNode.p.distanceTo(thisNode.next.p);
            } while (thisNode != first);

            Node newNode = new Node(p);
            newNode.next = idealNode.next;
            idealNode.next = newNode;
        }
    }

//    void insertAfter(Point a) {
//        if (first.p == null) {
//            first.p == a;
//        }
//        else {
//            Node thisNode = new Node();
//            thisNode.p = a;
//            last.next = thisNode;
//            last = last.next;
//            n++;
//        }
//    }

    public static void main(String[] args) {
        Point a = new Point(200.0, 100.0);
        Point b = new Point(100.0, 300.0);
        Point c = new Point(300.0, 100.0);
        Point d = new Point(200.0, 100.0);

        //test tour
        Tour test = new Tour();

    }


}
