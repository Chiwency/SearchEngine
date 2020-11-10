import java.util.HashMap;
import java.util.Map;


// 读取分词文件 统计词频并储存到一个新文件中
public class WordsFrequencyCounter {
    public static void countWordsFrequency(String filePath, String newFilePath){
        Map<String,Integer> wordsMap=new HashMap<String,Integer>();
        int wordsSum=0;  // 不重复词的个数
        int totalWords=0; // 所有词的总数

        // 读取文件,统计词频,写入词典
        String data = FileHander.read(filePath, " ");
        String[] splitedData=data.split(" ",0);
        for (String word:splitedData){
            if (!wordsMap.containsKey(word)){   // 如果该词没有在字典内,词频置为1
                if (word.equals("")){   // 空串不能被计入
                    continue;
                }
                wordsSum++;
                totalWords++;
                wordsMap.put(word, 1);
            }else{
                totalWords++;
                wordsMap.put(word, wordsMap.get(word)+1);    // 如果该词在词典内,词频++
            }  
        }

        // 遍历词频字典,将词频信息放入数组中
        String[] wordsFrequency=new String[wordsSum+1];
        wordsFrequency[wordsSum]="totalWords"+" "+totalWords;  // 在最后一行写入词的总数,后面计算tf-idf值要用
        int i=0;
        for (Map.Entry<String,Integer> word : wordsMap.entrySet()){
            wordsFrequency[i++]=word.getKey()+" "+word.getValue();       // 词频信息存储格式: 词 3 
        }                                                                //  (即词与词频之间用空格隔开)
        FileHander.create(newFilePath);
        // 把词频统计的结果集写入文件
        FileHander.write(newFilePath, wordsFrequency, wordsSum+1);
    }
}


