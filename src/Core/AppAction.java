package Core;

import org.json.*;

import java.util.ArrayList;
import java.util.HashMap;

public class AppAction {
    private JSONArray data_pool;
    private ArrayList<HashMap> selects;
    private ArrayList<HashMap> saveList;
    private ArrayList<HashMap> prevList;
    private ArrayList<HashMap> now;
    private int random, maxBound;
    private static AppAction INSTANCE;

    public AppAction(){

    }
    public AppAction(ArrayList<HashMap> arr){
        this.selects = arr;
        prepInstance();
    }
    public static AppAction getInstance(){
        if(INSTANCE == null){
            INSTANCE = new AppAction();
        }
        return INSTANCE;
    }

    public void setSelects(ArrayList<HashMap> selects) {
        this.selects = selects;
    }

    public void prepInstance(){
        this.maxBound = this.selects.size()-1;

        this.random = getRandom();
        System.out.println(this.selects.size());
        this.saveList = new ArrayList<>();
        this.prevList = new ArrayList<>();
        this.now = new ArrayList<>();
    }
    public int getRandom(){
        maxBound = selects.size()-1;
        return (int)(Math.random() * maxBound) + 1;
    }
    public ArrayList<HashMap> getSaveList(){
        if(this.saveList.size() == 0){
            return null;
        }
        return this.saveList;
    }
    public ArrayList<HashMap> getPrev(){
        return prevList;
    }
    public HashMap getNow(){
        this.now.add(selects.get(this.random));
        this.selects.remove(this.random);
        this.random = getRandom();
        return this.now.get(0);
    }
    public void next(){
        prevList.add(selects.get(this.random));
        this.random = getRandom();
        setNow(this.selects.get(this.random));
//        selects.get(this.random);
    }
    public void save(){
        saveList.add(selects.get(this.random));
        selects.remove(this.random);
        this.random = getRandom();
        System.out.println("Random: "+this.random);
        System.out.println("size: "+this.selects.size());
        setNow(this.selects.get(this.random));
    }
    public void setNow(HashMap txt){
        if(this.prevList.size() > 0){
            System.out.println(now.toString());
            if(this.now.size() == 0){
                this.now.add(0, txt);
            }else {
                this.now.set(0, txt);
            }

        }
    }
    public void prev(){
        if(this.prevList.size() > 0) {
            setNow(this.prevList.get(this.prevList.size() - 1));
            this.prevList.remove(this.prevList.size() - 1);
        }
    }
}