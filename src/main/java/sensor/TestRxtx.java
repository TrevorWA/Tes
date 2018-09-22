package sensor;

import java.io.IOException;
import java.io.InputStream;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TooManyListenersException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mapper.LoginMapper;
import com.pojo.Login;
import com.service.LoginService;
import com.service.LoginServiceImpl;
import com.service.SensorService;
import com.service.SensorServiceImpl;
import com.socket.TestHandler;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

public class TestRxtx implements SerialPortEventListener {
	
	String light_control="";
    String water_control="";
    String addo2_control="";
    String heating_control="";

	
	// 检测系统中可用的通讯端口类
	private CommPortIdentifier portId;
	// 枚举类型
	private Enumeration<CommPortIdentifier> portList;

	// RS232串口
	private SerialPort serialPort;

	// 输入输出流
	private InputStream inputStream;
	private OutputStream outputStream;

	private NioSocketAcceptor acceptor = null;
	
	// 地址
	public  Map<String, String> dataAll = new HashMap<String, String>();
	
	public String xxx=new String("000");

	// 初始化串口
	@SuppressWarnings("unchecked")
	public void init() {
		// 获取系统中所有的通讯端口
		portList = CommPortIdentifier.getPortIdentifiers();
		// 循环通讯端口s
		while (portList.hasMoreElements()) {
			portId = portList.nextElement();
			// 判断是否是串口
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				// 比较串口名称是否是"COM1"
				if ("COM3".equals(portId.getName())) {
					System.out.println("找到串口COM3");
					// 打开串口
					try {
						// open:（应用程序名【随意命名】，阻塞时等待的毫秒数）
						serialPort = (SerialPort) portId.open(Object.class.getSimpleName(), 100);
						System.out.println("获取到串口对象,COM3");
						// 设置串口监听
						serialPort.addEventListener(this);
						// 设置串口数据时间有效(可监听)
						serialPort.notifyOnDataAvailable(true);
						// 设置串口通讯参数
						// 波特率，数据位，停止位和校验方式
						// 波特率2400,偶校验
						serialPort.setSerialPortParams(115200, SerialPort.DATABITS_8, //
								SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
						outputStream = serialPort.getOutputStream();

					} catch (PortInUseException e) {
						e.printStackTrace();
					} catch (TooManyListenersException e) {
						e.printStackTrace();
					} catch (UnsupportedCommOperationException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}
		}
	}

	// 实现接口SerialPortEventListener中的方法 读取从串口中接收的数据
	@Override
	public void serialEvent(SerialPortEvent event) {
		switch (event.getEventType()) {
		case SerialPortEvent.BI: // 通讯中断
		case SerialPortEvent.OE: // 溢位错误
		case SerialPortEvent.FE: // 帧错误
		case SerialPortEvent.PE: // 奇偶校验错误
		case SerialPortEvent.CD: // 载波检测
		case SerialPortEvent.CTS: // 清除发送
		case SerialPortEvent.DSR: // 数据设备准备好
		case SerialPortEvent.RI: // 响铃侦测
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY: // 输出缓冲区已清空
			break;
		case SerialPortEvent.DATA_AVAILABLE: // 有数据到达
			readComm();
			ErrorControl();
			break;
		default:
			break;
		}
	}

	// 读取串口返回信息
	public void readComm() {
		byte[] readBuffer = new byte[1024];
		String data2 = null;
		try {
			inputStream = serialPort.getInputStream();
			// 从线路上读取数据流
			int len = 0;
			while ((len = inputStream.read(readBuffer)) != -1) {

				System.out.println("实时反馈：" + byte2HexStr(readBuffer, len));

				data2 = byte2HexStr(readBuffer, len);
				int x = data2.split(" ").length;
				String[] handler = data2.split(" ");
				if (x == 10) {
					// s传感器短地址，t传感器获取数据
					String s = handler[5] + " " + handler[6] + " " + handler[7];
					String t = handler[8];
					System.out.println("网络地址：" + s + " 值：" + t);
					dataAll.put(s, t);
				} else if (x == 11) {
					String s = handler[5] + " " + handler[6] + " " + handler[7];
					String t = handler[8] + " " + handler[9];
					// System.err.println(s);
					// System.err.println(t);
					System.out.println("网络地址：" + s + " 值：" + t);
					dataAll.put(s, t);
				}

				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	// 关闭串口
	public void closeSerialPort() {
		if (serialPort != null) {
			serialPort.notifyOnDataAvailable(false);
			serialPort.removeEventListener();
			if (inputStream != null) {
				try {
					inputStream.close();
					inputStream = null;
				} catch (IOException e) {
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
					outputStream = null;
				} catch (IOException e) {
				}
			}
			serialPort.close();
			serialPort = null;
		}
	}

	public static void main(String[] args) {

		TestRxtx testRxtx = new TestRxtx();

		TestRxtx sp = new TestRxtx();

		sp.init();

		// sp.sendMsg();;

		MaplayoutThread map1 = testRxtx.new MaplayoutThread();
		map1.start();

		// System.out.println("输出" + sp.test);
		// sp.closeSerialPort();

	}

	public static String byte2HexStr(byte[] b, int len) {
		String stmp = "";
		StringBuilder sb = new StringBuilder();

		for (int n = 0; n < len; n++) {
			stmp = Integer.toHexString(b[n] & 0xFF);
			sb.append((stmp.length() == 1) ? "0" + stmp : stmp);
			sb.append(" ");
		}
		return sb.toString().toUpperCase().trim();
	}

	public void write(byte x[]) {
		try {
			OutputStream outputStream = serialPort.getOutputStream();
			outputStream.write(x);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
	}

	public static byte[] hexStr2Bytes(String src) {
		/* 对输入值进行规范化整理 */
		src = src.trim().replace(" ", "").toUpperCase(Locale.US);
		// 处理值初始化
		int m = 0, n = 0;
		int iLen = src.length() / 2; // 计算长度
		byte[] ret = new byte[iLen]; // 分配存储空间

		for (int i = 0; i < iLen; i++) {
			m = i * 2 + 1;
			n = m + 1;
			ret[i] = (byte) (Integer.decode("0x" + src.substring(i * 2, m) + src.substring(m, n)) & 0xFF);
		}
		return ret;
	}

	private static String Xor(String strHex_X, String strHex_Y) {

		// 将x、y转成二进制形式

		String anotherBinary = Integer.toBinaryString(Integer.valueOf(strHex_X, 16));

		String thisBinary = Integer.toBinaryString(Integer.valueOf(strHex_Y, 16));

		String result = "";

		// 判断是否为8位二进制，否则左补零
		if (anotherBinary.length() != 8) {
			for (int i = anotherBinary.length(); i < 8; i++) {

				anotherBinary = "0" + anotherBinary;
			}
		}
		if (thisBinary.length() != 8) {
			for (int i = thisBinary.length(); i < 8; i++) {
				thisBinary = "0" + thisBinary;
			}
		}

		// 异或运算
		for (int i = 0; i < anotherBinary.length(); i++) {
			// 如果相同位置数相同，则补0，否则补1
			if (thisBinary.charAt(i) == anotherBinary.charAt(i))
				result += "0";
			else {
				result += "1";
			}
		}
		return Integer.toHexString(Integer.parseInt(result, 2));
	}

	public static String checkcode(String para) {
		int length = para.length() / 2;
		String[] dateArr = new String[length];

		for (int i = 0; i < length; i++) {
			dateArr[i] = para.substring(i * 2, i * 2 + 2);
		}
		String code = "00";
		for (int i = 0; i < dateArr.length; i++) {
			code = Xor(code, dateArr[i]);
		}
		return code;
	}

	// 向串口发送信息方法
	public void sendMsg(String com) {

		String info = "";
		String msg = "071800F100A001"+com;// 要发送的命令
		info = "02" + msg + checkcode(msg);

		try {
			outputStream.write(hexStr2Bytes(info));
			outputStream.flush();
			System.out.println("输出成功");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("输出失败");
		}
	}

	class MaplayoutThread extends Thread {
		@Override
		public void run() {
			
	        // 2.从容器中获取mapper
			

			while (true) {
				try {
					sleep(3000);
					Set<String> keySet = dataAll.keySet();
					Iterator<String> it1 = keySet.iterator();
					while (it1.hasNext()) {
						String ID = it1.next();
						System.out.println(ID + " " + dataAll.get(ID));
					}

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
	}
	
	public void ErrorControl() {
		
		String control="";
		control=dataAll.get("00 A0 01");
		
		String x=hexString2binaryString(control);

        light_control=x.substring(4,5);
        water_control=x.substring(5,6);
        addo2_control=x.substring(6,7);
        heating_control=x.substring(7,8);
        
        if(dataAll.get("EE 61 01").equals("01")&&water_control.equals("1")) {
			sendMsg("0"+binaryString2hexString(light_control+"0"+addo2_control+heating_control));
		}else if(dataAll.get("EE 61 01").equals("00")&&water_control.equals("0")) {
			sendMsg("0"+binaryString2hexString(light_control+"1"+addo2_control+heating_control));
		}
        
        String[] handler = dataAll.get("47 8C 01").split(" ");
        String t = handler[1] + handler[0];
        float wendu=(float) (Integer.parseInt(t,16)/100.00);
		
		if(wendu>26&&heating_control.equals("1")) {
			sendMsg("0"+binaryString2hexString(light_control+water_control+addo2_control+"0"));
		}else if(wendu<26&&heating_control.equals("0")) {
			sendMsg("0"+binaryString2hexString(light_control+water_control+addo2_control+"1"));
		}
		
	}
	
	public static String hexString2binaryString(String hexString)
    {
        if (hexString == null || hexString.length() % 2 != 0)
            return null;
        String bString = "", tmp;
        for (int i = 0; i < hexString.length(); i++)
        {
            tmp = "0000" + Integer.toBinaryString(Integer.parseInt(hexString.substring(i, i + 1), 16));
            bString += tmp.substring(tmp.length() - 4);
        }
        return bString;
    }
	
	public static String binaryString2hexString(String bString)
    {
        if (bString == null || bString.equals("") || bString.length() % 4!= 0)
            return null;
        StringBuffer tmp = new StringBuffer();
        int iTmp = 0;
        for (int i = 0; i < bString.length(); i += 4)
        {
            iTmp = 0;
            for (int j = 0; j < 4; j++)
            {
                iTmp += Integer.parseInt(bString.substring(i + j, i + j + 1)) << (4 - j - 1);
            }
            tmp.append(Integer.toHexString(iTmp));
        }
        return tmp.toString();
    }

	/*@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

		TestRxtx testRxtx = new TestRxtx();

		TestRxtx sp = new TestRxtx();

		sp.init();

		// sp.sendMsg();;

		MaplayoutThread map1 = testRxtx.new MaplayoutThread();
		map1.start();

		// System.out.println("输出" + sp.test);
		// sp.closeSerialPort();

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}*/

}
