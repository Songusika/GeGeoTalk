/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author Seok17
 */
public class UserAccount {
    private String id;
    private String pw="";
    private String name;
    
    public String getId(){
        return id;
    }
    public String getPw(){
        return pw;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setId(String id){
        this.id = id;
    }
    public void setPw(String pw){
        this.pw = pw;
        }
    }
    
