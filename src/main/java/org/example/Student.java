package org.example;

public class Student {

    private String name;
    private String id;
    private String dept;
    private int batch;
    private String section;

    public void setName(String name){
        this.name=name;
    }
    public void setId(String id){
        this.id=id;
    }
    public void setDept(String dept){
        this.dept=dept;
    }
    public void setBatch(int batch){
        this.batch=batch;
    }
    public void setSection(String section){
        this.section=section;
    }

    public String getName(){
        return name;
    }
    public String getId(){
        return id;
    }
    public String getDept(){
        return dept;
    }
    public int getBatch(){
        return batch;
    }
    public String getSection(){
        return section;
    }

}