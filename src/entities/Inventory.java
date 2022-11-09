package entities;

import java.util.Arrays;

public class Inventory {
    int grade;
    int size;
    Item[] items;

    public Inventory(int grade) {
        setGrade(grade);
        switch (grade){
            case 1: setSize(4);
            case 2: setSize(8);
            case 3: setSize(16);
        }
        this.items = new Item[size];
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("{grade=" + grade +
                ", size=" + size +
                "\n\t\titems=\n");
        for (Item item :
                items) {
            output.append("\t\t\t").append(item).append("\n");
        }
        return output.toString();
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }
}
