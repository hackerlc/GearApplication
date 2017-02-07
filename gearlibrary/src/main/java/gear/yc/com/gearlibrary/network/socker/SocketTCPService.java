package gear.yc.com.gearlibrary.network.socker;

import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * youmai
 * Created by YichenZ on 2015/4/24 17:22.
 */
public class SocketTCPService implements Runnable{
    //tcp server info
    protected Socket socket = null;
    protected String ip="";
    protected int port=0;

    protected BufferedReader in = null;
    protected PrintWriter out = null;
    protected DataOutputStream outputStream;
    protected String content = "";
    protected String text="";
    protected SocketTcpContextListener mSocketLis;
    protected char[] cbuf=new char[1024];

    public SocketTCPService(String ip, int port){
        this.ip=ip;
        this.port=port;
    }

    public SocketTCPService(String ip, int port, SocketTcpContextListener mSocketLis){
        this.ip=ip;
        this.port=port;
        this.mSocketLis=mSocketLis;
    }

    /**
     * 运行线程连接服务器
     * in.read 与 in.readLine 用法不同
     */
    @Override
    public void run() {
        try {
            InetAddress serverAddress = InetAddress.getByName(ip);
            socket = new Socket(serverAddress,port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                    socket.getOutputStream())), true);
            //outputStream=new DataOutputStream(socket.getOutputStream());
            while (true) {
                if (socket.isConnected()) {
                    if (!socket.isInputShutdown()) {
                        if(in.read(cbuf, 0, cbuf.length)!=-1) {
                            text = setContent(cbuf);
                            content="";
                            mHandler.sendEmptyMessage(100);
                        }
                    }
                } else {

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(104);
        }
    }

    Handler mHandler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 100:
                    if(mSocketLis!=null) {
                        mSocketLis.getServerContent(text);
                    }
                    break;
                case 104:break;
            }
        }
    };

    /**
     * 向服务器发送数据
     * @param str
     */
    public void writerContentToServer(String str){
        if(socket.isConnected()){
            out.print(str);
            out.flush();
        }
    }

    /**
     * 关闭连接
     */
    public void closeService(){
        try {
            if(socket.isConnected()) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取缓存区数据
     * @param chars
     * @return
     */
    private String setContent(char[] chars){
        for(int i=0;i<chars.length;i++){
            if(chars[i]!=(char)7){
                content+=chars[i];
            }else{
                break;
            }
        }
        return content;
    }
}
