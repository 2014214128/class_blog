package com.xj.util;



//--------------------- Change Logs----------------------
//<p>@author zhiqiang.zhang Initial Created at 2010-12-23<p>
//-------------------------------------------------------

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

//增量索引
/*
* 实现思路:首次查询数据库表所有记录，对每条记录建立索引，并将最后一条记录的id存储到storeId.txt文件中
*         当新插入一条记录时，再建立索引时不必再对所有数据重新建一遍索引，
*         可根据存放在storeId.txt文件中的id查出新插入的数据，只对新增的数据新建索引，并把新增的索引追加到原来的索引文件中
* */
public class IncrementIndex {

//    public static void main(String[] args) {
//        try {
//            IncrementIndex index = new IncrementIndex();
//            String path = "E:\\Test\\lucene_test\\poiIdext";//索引文件的存放路径
//            String storeIdPath = "E:\\Test\\lucene_test\\storeId.txt";//存储ID的路径
//            String storeId = "";
//            Date date1 = new Date();
//            storeId = index.getStoreId(storeIdPath);
//            ResultSet rs = index.getResult(storeId);
//            System.out.println("开始建立索引。。。。");
//            index.indexBuilding(path, storeIdPath, rs);
//            Date date2 = new Date();
//            System.out.println("耗时："+(date2.getTime()-date1.getTime())+"ms");
//            storeId = index.getStoreId(storeIdPath);
//            System.out.println(storeId);//打印出这次存储起来的ID
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static void buildIndex(String indexFile, String storeIdFile) {
        try {

            String path = indexFile;//索引文件的存放路径
            String storeIdPath = storeIdFile;//存储ID的路径
            String storeId = "";
            storeId = getStoreId(storeIdPath);
            ResultSet rs = getResult(storeId);
            indexBuilding(path, storeIdPath, rs);
            storeId = getStoreId(storeIdPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ResultSet getResult(String storeId) throws Exception {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        String url = "jdbc:mysql://121.251.19.130:3306/blog?userunicode=true&characterEncoding=utf-8";
        String userName = "root";
        String password = "615615";
        Connection conn = DriverManager.getConnection(url, userName, password);
        Statement stmt = conn.createStatement();
        String sql = "select  * from article";
        ResultSet rs = stmt.executeQuery(sql + " where id > '" + storeId + "'order by id");
        return rs;
    }

    public static boolean indexBuilding(String path, String storeIdPath, ResultSet rs) {
        try {
            Analyzer luceneAnalyzer = new StandardAnalyzer();
            // 取得存储起来的ID，以判定是增量索引还是重新索引
            boolean isEmpty = true;
            try {
                File file = new File(storeIdPath);
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileReader fr = new FileReader(storeIdPath);
                BufferedReader br = new BufferedReader(fr);
                if (br.readLine() != null) {
                    isEmpty = false;
                }
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //isEmpty=false表示增量索引
            IndexWriter writer = new IndexWriter(path, luceneAnalyzer, isEmpty);
            String storeId = "";
            boolean indexFlag = false;
            String id;
            String title;
            String note;
            String content;
            while (rs.next()) {
                id = rs.getInt("id") + "";
                title = rs.getString("title");
                note = rs.getString("note");
                content = rs.getString("content");
                writer.addDocument(Document(id, title, note, content));
                storeId = id;//将拿到的id给storeId，这种拿法不合理，这里为了方便
                indexFlag = true;
            }
            writer.optimize();
            writer.close();
            if (indexFlag) {
                // 将最后一个的ID存到磁盘文件中
                writeStoreId(storeIdPath, storeId);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("出错了" + e.getClass() + "\n   错误信息为:   " + e.getMessage());
            return false;
        }

    }

    public static Document Document(String id, String title, String note, String content) {
        Document doc = new Document();
        doc.add(new Field("id", id, Field.Store.YES, Field.Index.TOKENIZED));
        doc.add(new Field("title", title, Field.Store.YES, Field.Index.TOKENIZED));//查询字段
        //doc.add(new Field("note",note , Field.Store.YES, Field.Index.TOKENIZED));//查询字段-
        doc.add(new Field("content", content, Field.Store.YES, Field.Index.TOKENIZED));//查询字段
        return doc;
    }

    // 取得存储在磁盘中的ID
    public static String getStoreId(String path) {
        String storeId = "";
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            storeId = br.readLine();
            if (storeId == null || storeId == "") storeId = "0";
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return storeId;
    }

    // 将ID写入到磁盘文件中
    public static boolean writeStoreId(String path, String storeId) {
        boolean b = false;
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(path);
            PrintWriter out = new PrintWriter(fw);
            out.write(storeId);
            out.close();
            fw.close();
            b = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }
}
