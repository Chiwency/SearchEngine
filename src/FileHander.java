import java.util.*; 
import java.io.*;

public class FileHander {
    // 读取文件  // 返回一串字符串 每行之间用 lineSplit 符号隔开
    public static String read(String filePath,String lineSplit)  {
        try {
            File myFile = new File(filePath);
            Scanner myReader = new Scanner(myFile);
            String result=myReader.nextLine();
            while (myReader.hasNextLine()) {
                result=result+lineSplit+myReader.nextLine();               
            }
            myReader.close();
            return result;

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while opening file.");
            e.printStackTrace();
       }
       return "";
    }

    // 创建文件
    public static void create(String filePath) {
        try {
          File newFile = new File(filePath);
          if (newFile.createNewFile()) {
            System.out.println("File created: " + newFile.getName());
          } else {
            System.out.println("File already exists.");
          }
        } catch (IOException e) {
          System.out.println("An error occurred while creating file.");
          e.printStackTrace();
        }
      }


      // 写入文件
      public static void write(String filePath,String[] records,int lines) {
        // 每行储存一个词的词频信息
        String dataWithNewLine;
        try {
            File file =new File(filePath);
            FileWriter fr = new FileWriter(file);
            BufferedWriter br=new BufferedWriter(fr);
            for(int i = 0; i<lines; i++){
                dataWithNewLine=records[i]+System.getProperty("line.separator");
                br.write(dataWithNewLine);
            }

            br.close();
            fr.close();
        } catch (IOException e) {
            System.out.println("An error occurred While Writing to file.");
            e.printStackTrace();
        }
    }
} 
