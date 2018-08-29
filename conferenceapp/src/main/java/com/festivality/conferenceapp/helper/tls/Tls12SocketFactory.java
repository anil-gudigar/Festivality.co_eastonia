package com.festivality.conferenceapp.helper.tls;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 * Created by ankumar on 11/14/2017.
 */

public class Tls12SocketFactory  extends SSLSocketFactory {
    private static final String[] TLS_V12_ONLY = new String[]{"TLSv1.2"};
    final SSLSocketFactory delegate;

    public Tls12SocketFactory(SSLSocketFactory base) {
        this.delegate = base;
    }

    public String[] getDefaultCipherSuites() {
        return this.delegate.getDefaultCipherSuites();
    }

    public String[] getSupportedCipherSuites() {
        return this.delegate.getSupportedCipherSuites();
    }

    public Socket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException {
        return this.patch(this.delegate.createSocket(s, host, port, autoClose));
    }

    public Socket createSocket(String host, int port) throws IOException {
        return this.patch(this.delegate.createSocket(host, port));
    }

    public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException {
        return this.patch(this.delegate.createSocket(host, port, localHost, localPort));
    }

    public Socket createSocket(InetAddress host, int port) throws IOException {
        return this.patch(this.delegate.createSocket(host, port));
    }

    public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws IOException {
        return this.patch(this.delegate.createSocket(address, port, localAddress, localPort));
    }

    private Socket patch(Socket s) {
        if(s instanceof SSLSocket) {
            ((SSLSocket)s).setEnabledProtocols(TLS_V12_ONLY);
        }

        return s;
    }
}

