package com.socket;

import java.util.Iterator;
import java.util.Set;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import sensor.TestRxtx;


public class TestHandler extends IoHandlerAdapter{
	
	private TestRxtx testRxtx = new TestRxtx();

	 @Override
	    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
	        System.out.println("exceptionCaught: " + cause);
	    }

	    @Override
	    public void messageReceived(IoSession session, Object message) throws Exception {
	        System.out.println("recieve : " + (String) message);
	        //session.write("Hello,我是服务器");
	        if(message.toString().equals("111")) {
	        	SendThread map1 = new SendThread(session);
	    		map1.start();
	        }else {
	        	testRxtx.sendMsg((String) message);
	        }
	       
	    }

	    @Override
	    public void messageSent(IoSession session, Object message) throws Exception {

	    }

	    @Override
	    public void sessionClosed(IoSession session) throws Exception {
	        System.out.println("sessionClosed");
	        
	        testRxtx.closeSerialPort();
	    }

	    @Override
	    public void sessionOpened(IoSession session) throws Exception {
	        System.out.println("sessionOpen");	        
	        
	        testRxtx.init();	        	        	       	    
	        
	    }

	    @Override
	    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
	    }
	    
	    class SendThread extends Thread {
	    	
	    	public SendThread(IoSession session) {
				super();
				this.session = session;
			}
			IoSession session;
			@Override
			public void run() {
				
		        // 2.从容器中获取mapper
				while (true) {
					try {
						sleep(500);//100ms
						
						Set<String> keySet = testRxtx.dataAll.keySet();
						Iterator<String> it1 = keySet.iterator();
						while (it1.hasNext()) {
							String ID = it1.next();
							if(ID.equals("EE 61 01")) {
								session.write("02 07 18 00 F1 "+ID + " " + testRxtx.dataAll.get(ID)+" 11");
								//System.out.println("输出："+"02 07 18 00 F1 "+ID + " " + testRxtx.dataAll.get(ID)+" 11");
								
							}else if(ID.equals("D7 15 01")) {
								session.write("02 08 18 00 F1 "+ID + " " + testRxtx.dataAll.get(ID)+" 11");
								//System.out.println("输出："+"02 08 18 00 F1 "+ID + " " + testRxtx.dataAll.get(ID)+" 11");
								
							}else if(ID.equals("00 A0 01")) {
								session.write("02 07 18 00 F1 "+ID + " " + testRxtx.dataAll.get(ID)+" 11");
								//System.out.println("输出："+"02 07 18 00 F1 "+ID + " " + testRxtx.dataAll.get(ID)+" 11");
								
							}else if(ID.equals("47 8C 01")) {
								session.write("02 07 18 00 F1 "+ID + " " + testRxtx.dataAll.get(ID)+" 11");
								//System.out.println("输出："+"02 07 18 00 F1 "+ID + " " + testRxtx.dataAll.get(ID)+" 11");
								
							}else if(ID.equals("47 8C 02")) {
								session.write("02 07 18 00 F1 "+ID + " " + testRxtx.dataAll.get(ID)+" 11");
								//System.out.println("输出："+"02 07 18 00 F1 "+ID + " " + testRxtx.dataAll.get(ID)+" 11");
								
							}							
						}
						
						//session.write(testRxtx.xxx);
						//System.out.println("输出："+testRxtx.xxx);
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					

				}

			}
		}

}
