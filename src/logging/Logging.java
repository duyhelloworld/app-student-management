package logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Logging {
    private static long code;

    public String getTime() {
        Calendar now = Calendar.getInstance();
        SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return fm.format(now.getTime()).toString();
    }


    public String getInfos() {
        return "DATE: " + getTime() + ",\n\tUser agent : " + System.getProperty("os.name") + " " + System
                .getProperty("os.version") + " " + System.getProperty("os.arch");
    }

    public String getFullInfo() {
        return getInfos() + "\n\tJava Version : " + System.getProperty("java.vm.name") + "\t" + System.getProperty("java.version");
    }


    public String getError(Exception e) {
        return getInfos() + "\nError : " + e.getLocalizedMessage() + "\nCode : " + e.hashCode();
    }


    public String successLog(String usr, String pss) {
        return getTime() + " : Success logged in Database. \nUsername : " + usr + "\nPassword : " + hidePass(pss);
    }
    

    String hidePass(String pss) {
        int passLength = pss.length();
        String result = "";
        for (int i = 0; i < passLength; i++) {
            result += "*";
        }
        return result;
    }

    public static void writeLog(String logLine) {
        File fileLog = new File("log.txt");
        try {
            FileWriter fileWriter = new FileWriter(fileLog);
            fileWriter.write("#" + code + ": " + logLine);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage() + fileLog.getName() + ": No such file or directory");
        }
    }
}
