package model;

public class Actor {

    public Actor(){
        this.id = 0;
        this.name = "";
        this.awards = 0;
    }

    public Actor(int id, String name, int awards) {
        this.id = id;
        this.name = name;
        this.awards = awards;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAwards() {
        return awards;
    }

    public void setAwards(int awards) {
        this.awards = awards;
    }

    private int id;
    private String name;
    private int awards;


}
