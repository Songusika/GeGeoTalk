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
public class RoomInfo {
    
    private String roomName;
    private int roomId;
    
    public void setRoomName(String roomName){
        this.roomName = roomName;
    }
    public void setroomId(int roomId){
        this.roomId = roomId;
    }
    public String getRoomName(){
        return this.roomName;
    }
    public int getroomId(){
        return this.roomId;
    }
    
}
