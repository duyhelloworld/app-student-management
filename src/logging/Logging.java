package logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

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
        return getInfos() + "\n\tError : " + e.getLocalizedMessage() + "\n\tCode : " + e.hashCode();
    }


    public String successLog(String usr, String pss) {
        return getTime() + " : Success logged in Database. \nUsername : " + usr + "\nPassword : " + hideSomeLetters(pss, pss.length());
    }
    

    String hideSomeLetters(String source, int sizeToHide) {
        String result = "";
        if (source.length() == sizeToHide) {
            for (int i = 0; i < sizeToHide; i++) {
                result += "*";
            }
        } else {
            result = source.substring(0, sizeToHide) + hideSomeLetters(source.substring(sizeToHide, source.length()),
                    source.length() - sizeToHide);
        }
        return result;
    }

    public static void setCode(long code) {
        Logging.code = code;
    }

    public String sqlSuccess(String query, boolean success, int rowAffected) {
        return getTime() + "\n\t" + query.substring(0, 6) + " action excuted " + (success ? "success" : "failure")
                + "\n\tAffected " + rowAffected + " row" + (rowAffected > 1 ? "s" : "");
    }
    
    public String view(long start, long end) {
        return getTime() + " Got users where id "
                + ((end - start > 1) ? " in range " + start + "-" + end : " = " + end);
    }

    public String viewByUsername(String username) {
        return getTime() + " Viewed user where user_name = " + hideSomeLetters(username, username.length() / 3); 
    }

    public String getRangeId(List<Long> listIds) {
        return listIds.toString().replace("[", "").replace("]", "");
    }

    public String delete(List<Long> listIds) {
        return getTime() + " Deleted users where id = {" + getRangeId(listIds) + "}";
    }
    
    public String update(String field, long id) {
        return getTime() + " Updated " + field + " of user's id = " + id;
    }

    public static void writeLog(String logLine) {
        try {
            FileWriter fileWriter = new FileWriter(fileLog, true);
            if (!fileLog.exists()) {
                fileLog.getParentFile().mkdirs();
                fileWriter.write("#" + code + ": " + logLine + "\n");
            } else {
                Logging.setCode(++code);
                fileWriter.append("#" + code + ": " + logLine + ".\n");
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage() + fileLog.getName() + ": Please try look up the log again.");
        }
    }


}
