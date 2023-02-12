package logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Logging {
    static long code;    
    static File fileLog = new File("log.txt");

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
        return getInfos() + "\nError : " + e.getLocalizedMessage() + "\nCode : " + e.hashCode() + "\n\n";
    }


    public String successLog(String usr, String pss) {
        return getTime() + " : Success logged in Database. \nUsername : " + usr + "\nPassword : " + hidePass(pss) + "\n";
    }
    

    String hidePass(String pss) {
        int passLength = pss.length();
        String result = "";
        for (int i = 0; i < passLength; i++) {
            result += "*";
        }
        return result;
    }

    public static void setCode(long code) {
        Logging.code = code;
    }

    public String sqlSuccess(String query, boolean success, int rowAffected) {
        return getTime() + "\n\t" + query.substring(0, 6) + " action excuted " + (success ? "success" : "failure")
                + "\n\tAffected " + rowAffected + " row" + (rowAffected > 1 ? "s" : "") + ".\n";
    }
    
    public String view(long start, long end) {
        return getTime() + " Viewed users where id " + ((end - start > 1) ? " in range " + start + "-" + end : " = " + end) + "\n";
    }

    public static void writeLog(String logLine) {
        try {
            FileWriter fileWriter = new FileWriter(fileLog, true);
            if (!fileLog.exists()) {
                fileLog.getParentFile().mkdirs();
                fileWriter.write("#" + code + ": " + logLine);
            } else {
                Logging.setCode(++code);
                fileWriter.append("#" + code + ": " + logLine);
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage() + fileLog.getName() + ": Please try look up the log again.");
        }
    }


}
