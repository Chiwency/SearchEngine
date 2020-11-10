public class DocRankCalculator {
    // 遍历倒排索引 返回经过排序的文档集
    public static String[] docRank(String filePath,String word,int docSum){
        String[] indexInfo;
        String[] docList;
        int docFrequency;
        String[] sortedDocList;
        // 倒排索引每条记录用分号隔开
        String data=FileHander.read(filePath, ";");
        String[] indexList=data.split(";",0);
        for (String indexEntry:indexList){
            indexInfo=indexEntry.split(" ",0);
            if (indexInfo.length<3){
                System.out.println("Wrong idexInfo while analyzing indexEntry");
                continue;
            }
            // 如果找到被搜索词,则计算文档tf-idf值并排序
            if (indexInfo[0].equals(word)){
                docFrequency=Integer.parseInt(indexInfo[1]);
                docList=indexInfo[2].split("___",0);
                sortedDocList=TfIdfCalculator.sortDocList(docList, docFrequency, docSum);
                return sortedDocList;
            }
        } 
        // 没找到就返回空
        return null;
    }
}
