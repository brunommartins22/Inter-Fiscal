package br.com.interagese.interfiscal.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/*
Classe usada para não deixar uma instancia do programa ser executada 2 vezes
*/
public class AppLock {

    private static File arquivo;
    private static FileChannel channel;
    private static FileLock lock;

    public static boolean lock() {
        try {

            String directory = System.getProperty("user.home");
            String fileName = "down.lock";

            arquivo = new File(directory + "/" + fileName);
            
            //Cria um arquivo .lock e faz um canal de acesso até ele
            channel = new RandomAccessFile(arquivo, "rw").getChannel();
            
            //Faz um lock no arquivo, ou seja, o programa agora tem um acesso ao arquivo
            lock = channel.tryLock();
            
            //Se retorna null, significa que o arquivo já está sendo usado, ou seja, já
            //existe um instancia do programa aberta usando ele
            
            if (lock == null) {
                //Fecha o canal
                channel.close();
                //Retorna falso
                return false;
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        System.out.println("Lock no arquivo " + arquivo.getAbsolutePath() + ", criado!");
        
        //Retorna true
        return true;
    }

    public static void unlock() {
        
        try {
            
            //caso o programa esteja com um lock no arquivo
            if (lock != null) {
                
                //Fecha o canal, o lock e deleta o arquivo
                lock.release();
                channel.close();
                arquivo.delete();
                System.out.println("Lock no arquivo " + arquivo.getAbsolutePath() + ", desfeito!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //Adiciona um listenner na maquina virtual, para quando ela for encerrada
    //tirar o lock do arquivo
    public static void adicionarShutdownHook() {

        Runnable runner = new Runnable() {
            @Override
            public void run() {
                AppLock.unlock();
            }
        };
        
        Runtime.getRuntime().addShutdownHook(new Thread(runner, "FileLock"));
    }
    
}