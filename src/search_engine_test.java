import java.util.Arrays;
import java.util.List;

public class search_engine_test {
    public static void main(String[] args){
        String filePath="./data/3001.txt";
        WordsFrequencyCounter.countWordsFrequency(filePath, "./data/4001.txt");
        String filePath1="./data/3002.txt";
        WordsFrequencyCounter.countWordsFrequency(filePath1, "./data/4002.txt");
        String filePath2="./data/3003.txt";
        WordsFrequencyCounter.countWordsFrequency(filePath2, "./data/4003.txt");

        String[] docList={"./data/4001.txt","./data/4002.txt","./data/4003.txt"};
        InvertIndex.create(docList,"./data/5001.txt");
        String[] sortedDocList=DocRankCalculator.docRank("./data/5001.txt", "参考消息", 5);
        List<String> list=Arrays.asList(sortedDocList);
        for(String doc:list){
            System.out.println(doc);
        }
    }

}