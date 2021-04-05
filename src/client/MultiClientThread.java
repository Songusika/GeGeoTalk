package client;

import java.awt.Font;
import java.util.Scanner;

public class MultiClientThread extends Thread{
    private ChattingRoom ms;
    
    public MultiClientThread(ChattingRoom ct){
        this.ms= ct;
    }
    
    
    public void run(){
        String message = null;
        String[] receivedMsg = null;
        
        boolean isStop = false; //작동
        while(!isStop){
            try{
                message = (String)ms.getOis().readObject();
                System.out.println(message);
                receivedMsg = message.split("#"); //받은 메세지를 #으로 쪼개서 문자열 배열에 저장
                //파일에 저장하는 코드 추가 예정

            }catch(Exception e){
                e.printStackTrace();
                isStop = true; //정지
            }
            if(receivedMsg[2].equals("exit")){
                if(receivedMsg[1].equals(ms.getId())){
                    ms.dispose();//자신이면 멀티 클라이언트 종료
                }else{
                    ms.getJta().append( //타인이면 채팅창에 종료했다고 표시
                    receivedMsg[1] +"님이 종료 하셨습니다."+ 
                    System.getProperty("line.separator"));
                    ms.getJta().setCaretPosition(
                    ms.getJta().getDocument().getLength());
                }
            }
            else if(receivedMsg[1].equals(ms.getId())){ //받은 메세지가 본인 자신이면 출력
            	ms.getJta().append(
            			receivedMsg[1] +" : "+receivedMsg[2]+
                        System.getProperty("line.separator"));
                        ms.getJta().setCaretPosition(
                            ms.getJta().getDocument().getLength());  
            	
            
            }
            else{               
                ms.getJta().append(
                receivedMsg[1] +" : "+receivedMsg[2]+
                System.getProperty("line.separator")); //일단 채팅방에 추가하는 문인거 같은데 방번호는 채팅방에서 안뜨니 1과 2만 넣어짐
                ms.getJta().setCaretPosition(
                    ms.getJta().getDocument().getLength());     
            }
        }
    }
}