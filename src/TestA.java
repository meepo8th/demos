import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by admin on 2017/4/19.
 */
public class TestA {
    public static final int MAX_MSG_LEN = 1600;
    public static int port = 9002; //服务器监听端口
    float d='a';
    float a=0.1f;
    public static void main(String args[]) {
        try {
            DatagramSocket udp = new DatagramSocket(port);
            udp.setSoTimeout(5000);
            DatagramPacket dPacket;
            byte[] echo = new byte[1];
            echo[0] = (byte) 1;
            while (true) {
                try {
                    dPacket = new DatagramPacket(new byte[MAX_MSG_LEN], MAX_MSG_LEN);
                    udp.receive(dPacket);
                    String result = new String(dPacket.getData(), 0, dPacket.getLength());
                    //返回一个字节给探针设备
                    InetAddress addr = dPacket.getAddress();
                    dPacket = new DatagramPacket(echo, echo.length);
                    dPacket.setAddress(addr);
                    udp.send(dPacket);
                } catch (Throwable e) {
                    e.printStackTrace();
                    udp.close();
                    udp = new DatagramSocket(port);
                    udp.setSoTimeout(5000);
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
