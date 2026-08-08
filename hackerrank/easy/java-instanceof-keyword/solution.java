import java.io.*;
import java.util.*;

class Vehicle {
    String type;

    void setType(String type) {
        this.type = type;
    }

    String getType() {
        return type;
    }
}

class Cycle extends Vehicle {
    Cycle() {
        setType("cycle");
    }

    void print() {
        System.out.println("My ancestor is a cycle who is a vehicle with pedals.");
    }
}

class MotorCycle extends Cycle {
    MotorCycle() {
        setType("motorcycle");
    }

    void print() {
        System.out.println("Hello I am a " + getType() + ", I am a cycle with an engine.");
        super.print();
    }
}

public class Solution {

    public static void main(String[] args) {
        MotorCycle m = new MotorCycle();
        m.print();
    }
}
