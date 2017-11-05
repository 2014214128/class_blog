import com.xj.util.IndexResult;
import com.xj.util.IndexUtils;
import com.xj.util.WordSegment;
import org.apache.lucene.search.IndexSearcher;

import java.util.Date;
import java.util.List;

/**
 * Created by Asus on 2017/7/7.
 */
public class Test {
    //存放索引文件
    private static String indexFile = "E:\\Test\\lucene_test\\poiIdext";

    //存放id
    private static String storeIdFile = "E:\\Test\\lucene_test\\storeId.txt";

    public static void main(String[] args) throws Exception {
        //0. 创建增量索引

        IndexUtils.buildIndex(indexFile, storeIdFile);


        IndexSearcher indexSearcher = new IndexSearcher(indexFile);

        List<IndexResult> list =null;
        String key ="";
        Date date1 = new Date();
        Date date2 =new Date();


        //1.单字段查询
//       key = IndexUtils.ik_CAnalyzer("完美");
//        list = IndexUtils.queryByOneKey(indexSearcher, "title", key);
//        date2 = new Date();
//        System.out.println("耗时：" + (date2.getTime() - date1.getTime()) + "ms\n" + list.size()
//                + "条=======================================单字段查询");
//        printResults(list);
//        System.exit(1);

        //2.多条件查询
        String[] fields = { "title","content"};
        //String[] keys = { IndexUtils.ik_CAnalyzer("程序员完美")};
        String keys[] = WordSegment.split("程序员");
        for (String s : keys) {
            System.out.println(s);
        }
        date1 = new Date();
        list = IndexUtils.queryByMultiKeys(indexSearcher, fields, keys);
        date2 = new Date();
        System.out.println("查询耗时：" + (date2.getTime() - date1.getTime()) + "ms\n" + list.size()
                + "条\n===============================多条件查询");
        printResults(list);
        System.exit(1);
        //3.高亮显示  单字段查询
//        System.out.println("\n\n");
//        date1 = new Date();
//        String  key="安徽";
//        list = IndexUtils.highlight(indexSearcher, key);
//        date2 = new Date();
//        System.out.println("耗时：" + (date2.getTime() - date1.getTime()) + "ms\n" + list.size()
//                + "条\n======================================高亮显示");
//        printResults(list);
//        System.exit(1);
        //4. 多字段查询
//        date1 = new Date();
//        list = IndexUtils.queryByMultiFileds(indexSearcher, fields, key);
//        date2 = new Date();
//        System.out.println("耗时：" + (date2.getTime() - date1.getTime()) + "ms\n" + list.size()
//                + "条\n=====================================多字段查询");
        // printResults(list);

        //5. 删除索引中的字段  根据id进行删除
//        IndexUtils.deleteIndex(indexFile, "1552");
    }


    //打印结果
    public static void printResults(List<IndexResult> list) {
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).getId() + "," + list.get(i).getTitle() + ","
                        + list.get(i).getNote() + "," + list.get(i).getContent()+"--->"+i);
            }

        }
    }
}
