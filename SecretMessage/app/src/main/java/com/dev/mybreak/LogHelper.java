package com.dev.mybreak;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by huaichen on 2018/4/22.
 */

public class LogHelper {
    public static final String TAG = "my_log" ;
    private static FileWriter writer = null ;

    public synchronized static void print(String format , Object ... args){
        String content = format ;
        if(args != null && args.length > 0){
            content = String.format(content ,args) ;
        }
        Log.i(TAG ,content) ;

        if(writer != null){

            try {
                writer.append(content).append("\n");
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized  static void enableFileWriter (Context context){
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd-HH");
       File logFile = new File(context.getExternalFilesDir(null),"hook_log_"+dateFormat.format(new Date()));
       if(!logFile.exists())
           logFile.mkdirs() ;

       if(logFile.isFile() && logFile.canWrite()){
           try {
               writer = new FileWriter(logFile,true) ;
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
    }
}
