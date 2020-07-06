package collection;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class MinaClient {
    static String HOST = "127.0.0.1";
    static int PORT = 18081;
    public static void main(String[] args) {
        IoSession session = null;
        IoConnector connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(3000);
        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"), LineDelimiter.WINDOWS.getValue(),LineDelimiter.WINDOWS.getValue())));
//        connector.setHandler(new MyHandler());
        ConnectFuture future = connector.connect(new InetSocketAddress(HOST, PORT));
        future.awaitUninterruptibly(); //等待链接
        session = future.getSession();
        session.write("wolaila");
        session.getCloseFuture().awaitUninterruptibly(); //等待关闭链接
        connector.dispose();
    }
}
