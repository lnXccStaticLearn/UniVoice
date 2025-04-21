//package com.ccll.task;
//
//import com.alibaba.fastjson2.JSON;
//import com.ccll.pojo.vo.ChatVo;
//import com.ccll.webSocket.WebSocketServer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//@Component
//public class WebSocketTask {
//    @Autowired
//    private WebSocketServer webSocketServer;
//
//    /**
//     * 通过WebSocket每隔5秒向客户端发送消息
//     */
//    @Scheduled(cron = "0/5 * * * * ?")
//    public void sendMessageToClient() {
//        ChatVo chatVo = new ChatVo(1L,2L,"aaa","xcc",LocalDateTime.now());
//        String jsonString = JSON.toJSONString(chatVo);
//        webSocketServer.sendToAllClient(jsonString);
//    }
//}
