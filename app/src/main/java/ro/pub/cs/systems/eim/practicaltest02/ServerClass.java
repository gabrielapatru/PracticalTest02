package ro.pub.cs.systems.eim.practicaltest02;

import android.util.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import Model.Pokemon;
import cz.msebera.android.httpclient.client.ClientProtocolException;

public class ServerClass extends Thread{
    ServerSocket serverSocket = null;
    int port = 0;
    HashMap<String, Pokemon> data = null;

    //aici se instantiaza un obiect de tip serversocket pt un port
    public ServerClass(int port){
        this.port = port;
        data = new HashMap<>();

        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException ioException) {
            Log.e("[PracticalTest02]", "An exception has occurred: " + ioException.getMessage());
            if (true) {
                ioException.printStackTrace();
            }
        }
    }

    //getteri
    public int getPort() {
        return port;
    }

    public synchronized HashMap<String, Pokemon> getData() {
        return data;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    //setteri
    public void setPort(int port) {
        this.port = port;
    }

    public synchronized void setData(String name, Pokemon pokemon) {
        this.data.put(name, pokemon);
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    //se accepta cereri de la clienti, comunicarea este realizata
    //folosing fire de executie
    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Log.i("[PracticalTest02]", "[SERVER THREAD] Waiting for a client invocation...");
                Socket socket = serverSocket.accept();
                Log.i("[PracticalTest02]", "[SERVER THREAD] A connection request was received from " + socket.getInetAddress() + ":" + socket.getLocalPort());
                CommunicationThread communicationThread = new CommunicationThread(this, socket);
                communicationThread.start();
            }
        } catch (ClientProtocolException clientProtocolException) {
            Log.e("[PracticalTest02]", "[SERVER THREAD] An exception has occurred: " + clientProtocolException.getMessage());
            if (true) {
                clientProtocolException.printStackTrace();
            }
        } catch (IOException ioException) {
            Log.e("[PracticalTest02]", "[SERVER THREAD] An exception has occurred: " + ioException.getMessage());
            if (true) {
                ioException.printStackTrace();
            }
        }
    }

    //oprim firul de executie
    public void stopThread() {
        interrupt();
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException ioException) {
                Log.e("[PracticalTest02]", "[SERVER THREAD] An exception has occurred: " + ioException.getMessage());
                if (true) {
                    ioException.printStackTrace();
                }
            }
        }
    }
}
