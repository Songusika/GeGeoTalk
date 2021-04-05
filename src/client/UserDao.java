/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.EventQueue;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ArrayList;
import java.sql.SQLException;

public class UserDao {     // DB에 쿼리를 날리기 위한 클래스 

	private static UserDao userDao = new UserDao();
	private static MariaDBConnector mariaDBConnector = MariaDBConnector.getInstance();

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	UserAccount user;
        RoomInfo room;
	ArrayList<UserAccount> userList;
        ArrayList<RoomInfo> roomList;

	private UserDao() {
	}

	public static UserDao getInstance() {
		if (userDao == null) {
			userDao = new UserDao();
		}
		return userDao;
	}

	public ArrayList<UserAccount> select() throws Exception {
		con = mariaDBConnector.getConnection();
		String sql = "SELECT * FROM CUSTOMER";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		userList = new ArrayList<>();
                
		while (rs.next()) {
			user = new UserAccount();
			user.setId(rs.getString("id"));
			user.setPw(rs.getString("pw"));
			user.setName(rs.getString("name"));
			userList.add(user);
		}
		return userList;
	}

        public boolean check(String id){
            con = mariaDBConnector.getConnection();
		ResultSet loginOk;
		try {
			String sql = "SELECT count(*) cnt FROM CUSTOMER WHERE id=?";
			pstmt = con.prepareStatement(sql);
                        pstmt.setString(1, id);
                        rs = pstmt.executeQuery();
			if(rs.next()){
                            int cnt = rs.getInt("cnt");
                            if(cnt > 0){
                                return true;
                            }
                        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
              
		return false;
        }
        
	public int insert(String id, String pw, String name) {
		con = mariaDBConnector.getConnection();
		int loginOk;
		try {
			String sql = "INSERT INTO CUSTOMER VALUES ('" + id + "','" + pw + "','" + name + "')";
			pstmt = con.prepareStatement(sql);
			loginOk = pstmt.executeUpdate();
		} catch (Exception e) {
			return loginOk = 0;
		}
		return loginOk;
	}
        public int insertRoom(String roomName){
            con = mariaDBConnector.getConnection();
            int RoomOk;
            try{
                int i = 0;
                String sql = "INSERT INTO ROOM VALUES ('"+ i + "','" + roomName + "')";
                pstmt = con.prepareStatement(sql);
                RoomOk = pstmt.executeUpdate();
            }catch(Exception e){
                return RoomOk = 0;
            }
            return RoomOk;
        }

	public Optional<UserAccount> loginCheck(String id, String pw) throws Exception {
		con = mariaDBConnector.getConnection();
		ArrayList<UserAccount> userList = this.select();
		//boolean loginOk = false;
		for (UserAccount user : userList) {
			if (id.equals(user.getId()) && pw.equals(user.getPw()))
				return Optional.of(user);
		}
		return Optional.empty();
	}
        public Optional<UserAccount> findPassword(String id, String name) throws Exception {
		con = mariaDBConnector.getConnection();
		ArrayList<UserAccount> userList = this.select();
		//boolean loginOk = false;
		for (UserAccount user : userList) {
			if (id.equals(user.getId()) && name.equals(user.getName()))
				return Optional.of(user);
		}
		return Optional.empty();
	}
        public ArrayList<RoomInfo> getRoomInfo() throws Exception{
            con = mariaDBConnector.getConnection();
            String sql = "SELECT * FROM ROOM";
            try{
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            roomList = new ArrayList<>();
           
            while (rs.next()) {
			room = new RoomInfo();
			room.setroomId(rs.getInt("roomid"));
			room.setRoomName(rs.getString("roomname"));
			roomList.add(room);
		}
             }catch(Exception e){
                e.printStackTrace();
            }
            return roomList;
        }
        
         public int getRoomLen(){
            con = mariaDBConnector.getConnection();
		int result=0;
		try {
			String sql = "SELECT count(*) cnt FROM ROOM";
			pstmt = con.prepareStatement(sql);
                        rs = pstmt.executeQuery();
			if(rs.next()){
                            int cnt = rs.getInt("cnt");
                            result = cnt;
                        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
                return result;
         }
}


