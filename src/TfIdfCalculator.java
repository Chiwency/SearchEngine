import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

// 用于计算文档集内各文档的Tf-Idf值,并按该值降序排列输出排列后的文档集
public class TfIdfCalculator {

    public static String[] sortDocList(String[] docList,int docFrequency, int docSum){
        String docPath;
        String[] docInfo;
        int wordFrequency;
        int wordsSum;   // 文档中的总词数
        String[] sortedDocList=new String[docFrequency];
        Map<String,Double> docRankMap=new HashMap<String,Double>();
        Map<String,Double> sortedDocRankMap=new HashMap<String,Double>();

        if (docList.length!=docFrequency || docFrequency==0){
            System.out.println("Wrong index entry while sorting doc");
            return null;
        }
        for(String doc:docList){
            docInfo=doc.split(",",0);
            if (docInfo.length!=3){
                System.out.println("Wrong index entry while sorting doc");
                continue;   // 索引条目格式错误就跳过分析下一个
            }
            docPath=docInfo[0];
            wordFrequency=Integer.parseInt(docInfo[1]);
            wordsSum=Integer.parseInt(docInfo[2]);
            docRankMap.put(docPath, tfIdf(docFrequency,wordFrequency,docSum,wordsSum));
        }

        // 将文档集Map按照tf-idf值排序后放入结果集中返回
        sortedDocRankMap=mapSortInDesc(docRankMap);
        int i=0;
        for (Map.Entry<String,Double> entry : sortedDocRankMap.entrySet()){
            sortedDocList[i++]=entry.getKey();
        }
        return sortedDocList;
    }


    private static Map<String,Double> mapSortInDesc(Map<String,Double> unsortedMap){
        LinkedHashMap<String, Double> reverseSortedMap = new LinkedHashMap<>();
 
        unsortedMap.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) 
            .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

        return reverseSortedMap;   // 可能发生了隐式类型转换? 不管它,能用就行.
 
    }

    // 计算tf_idf值
    private static double tfIdf(int docFrequency,int wordFrequency,int docSum, int wordsSum){
        double tf=(double)wordFrequency/wordsSum;
        double df=(double)docSum/(docFrequency+1);
        double idf=(double)(Math.log(df));

        return tf*idf;
    }
}