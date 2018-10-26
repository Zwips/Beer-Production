import java.io.File;
import java.io.IOException;

public class derp {


    public static void main(String[] args) {

        File file = new File("Simulation/batTest.bat");
        try {
            System.out.println(file.getCanonicalPath());
            System.out.println(file.getAbsolutePath());
            System.out.println(file.getPath());
            System.out.println(file.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }



        /*try {
            ProcessBuilder pb = new ProcessBuilder("cmd", "/C", "batTest.bat");
            File dir = new File("C:/Users/HCHB/IdeaProjects/Beer-Production/Simulation/batTest.bat");
            pb.directory(dir);
            Process p = pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        /*File file1 = file.getParentFile();
        System.out.println(file1.getAbsolutePath());

        File file2 = file1.getParentFile();
        System.out.println(file2.getAbsolutePath());*/


        try {String[] command = { "cmd.exe", "/C", "Start", file.getAbsolutePath() };
            Runtime r = Runtime.getRuntime();
            Process p  = r.exec(command);
            p.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /*File file2 = new File("C:/Users/HCHB/IdeaProjects/Beer-Production/Simulation/batTest.bat");
        try {String[] command = { "cmd.exe", "/C", "Start", "C:/Users/HCHB/IdeaProjects/Beer-Production/Semester Project 3/Simulation/batTest.bat" };
            Runtime r = Runtime.getRuntime();
            Process p  = r.exec("cmd /c start "+file2.getAbsolutePath());
            p.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/


    }
}
