import java.util.HashMap;
import java.util.Map;


public class InvertIndex {
    // 创建倒排索引 格式为: word doc_frequency doc1,frequency1,totalWords doc2,frequency2,totalWords,doc3,frequency3,totalWords 
    // 例: Trump 3 ./data/4001.txt,10,290,./data/4002.txt,12,420,./data/4003.txt,5,20391

    public static void create(String[] filePathList,String newFilePath) {
        Map<String,String> indexMap=new HashMap<String,String>();
        // 用于统计词出现的文档频率
        Map<String,Integer> docFrequencyMap=new HashMap<String,Integer>();
        int wordsSum=0; // 被索引词总数
        // 遍历每个文件
        for (String filePath:filePathList) {
            String word;
            String frequency;
            String wordInfo="";
            String data=FileHander.read(filePath, " ");
            String[] wordsFrequency=data.split(" ",0);
            String totalWords=wordsFrequency[wordsFrequency.length-1];
            // 词频文档最后一行储存的是文档总词数,所以是length-1
            for (int i=0;i<wordsFrequency.length-2;i+=2){
                word=wordsFrequency[i];
                frequency=wordsFrequency[i+1];

                // 如果索引中没有这个词,则添加,并且词的文档频率也要+1
                if (!indexMap.containsKey(word)){
                    wordsSum++;
                    docFrequencyMap.put(word,1);
                    wordInfo=filePath+","+frequency+","+totalWords;
                    indexMap.put(word,wordInfo);
                }else{
                    docFrequencyMap.put(word, docFrequencyMap.get(word)+1);
                    // 不同文档用三个下划线连接
                    wordInfo=indexMap.get(word)+"___"+filePath+","+frequency+","+totalWords;
                    indexMap.put(word, wordInfo);
                }
            }                       
        }

        String[] indexSet=new String[wordsSum];
        int i=0;
        // 遍历词典,将索引信息放入数组
        for (Map.Entry<String,String> term : indexMap.entrySet()){
            indexSet[i++]=term.getKey()+" "+String.valueOf(docFrequencyMap.get(term.getKey()))+" "+term.getValue();    
        }
        FileHander.create(newFilePath);
        // 把词频统计的结果集写入文件
        FileHander.write(newFilePath, indexSet, wordsSum);
    }
}
