package com.xj.util;

/**
 * Created by Asus on 2017/7/7.
 */


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.*;
import org.mira.lucene.analysis.IK_CAnalyzer;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IndexUtils {

    //0. 创建增量索引
    public static void buildIndex(String indexFile, String storeIdFile) {
        IncrementIndex.buildIndex(indexFile, storeIdFile);
    }

    //1. 单字段查询
    @SuppressWarnings("deprecation")
    public static List<IndexResult> queryByOneKey(IndexSearcher indexSearcher, String field,
                                                  String key) {
        try {
            Date date1 = new Date();
            QueryParser queryParser = new QueryParser(field, new StandardAnalyzer());
            Query query = queryParser.parse(key);
            Hits hits = indexSearcher.search(query);
            Date date2 = new Date();
            System.out.println("耗时：" + (date2.getTime() - date1.getTime()) + "ms");
            List<IndexResult> list = new ArrayList<IndexResult>();
            for (int i = 0; i < hits.length(); i++) {
                list.add(getIndexResult(hits.doc(i)));
            }
            return list;
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //2. 多条件查询。这里实现的是and操作
    //注：要查询的字段必须是index的
    //即doc.add(new Field("pid", rs.getString("pid"), Field.Store.YES,Field.Index.TOKENIZED));
    @SuppressWarnings("deprecation")
    public static List<IndexResult> queryByMultiKeys(IndexSearcher indexSearcher, String[] fields,
                                                     String[] keys) {

        try {
            BooleanQuery m_BooleanQuery = new BooleanQuery();
            if (keys != null && keys.length > 0) {
                for (int i = 0; i < keys.length; i++) {
                    QueryParser queryParser = new QueryParser(fields[i], new StandardAnalyzer());
                    Query query = queryParser.parse(keys[i]);
                    m_BooleanQuery.add(query, BooleanClause.Occur.MUST);//and操作
                }
                Hits hits = indexSearcher.search(m_BooleanQuery);
                List<IndexResult> list = new ArrayList<IndexResult>();
                for (int i = 0; i < hits.length(); i++) {
                    list.add(getIndexResult(hits.doc(i)));
                }
                return list;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //3.高亮显示  实现了单条件查询
    //可改造为多条件查询

    //4. 多字段查询
    @SuppressWarnings("deprecation")
    public static List<IndexResult> queryByMultiFileds(IndexSearcher indexSearcher,
                                                       String[] fields, String key) {
        try {
            MultiFieldQueryParser mfq = new MultiFieldQueryParser(fields, new StandardAnalyzer());
            Query query = mfq.parse(key);
            Hits hits = indexSearcher.search(query);
            List<IndexResult> list = new ArrayList<IndexResult>();
            for (int i = 0; i < hits.length(); i++) {
                list.add(getIndexResult(hits.doc(i)));
            }

            return list;
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //5. 删除索引
    public static void deleteIndex(String indexFile, String id) throws CorruptIndexException,
            IOException {
        IndexReader indexReader = IndexReader.open(indexFile);
        indexReader.deleteDocuments(new Term("id", id));
        indexReader.close();
    }

    //6. 一元分词
    @SuppressWarnings("deprecation")
    public static String Standard_Analyzer(String str) {
        Analyzer analyzer = new StandardAnalyzer();
        Reader r = new StringReader(str);
        StopFilter sf = (StopFilter) analyzer.tokenStream("", r);
        System.out.println("=====StandardAnalyzer====");
        System.out.println("分析方法：默认没有词只有字（一元分词）");
        Token t;
        String results = "";
        try {
            while ((t = sf.next()) != null) {
                System.out.println(t.termText());
                results = results + " " + t.termText();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

    //7. 字典分词
    @SuppressWarnings("deprecation")
    public static String ik_CAnalyzer(String str) {
        Analyzer analyzer = new IK_CAnalyzer();
        Reader r = new StringReader(str);
        TokenStream ts = (TokenStream) analyzer.tokenStream("", r);
        System.out.println("=====IK_CAnalyzer====");
        System.out.println("分析方法:字典分词,正反双向搜索");
        Token t;
        String results = "";
        try {
            while ((t = ts.next()) != null) {
                System.out.println(t.termText());
                results = results + " " + t.termText();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(results);
        return results;
    }

    //在结果中搜索
    public static void queryFromResults() {

    }

    //组装对象
    public static IndexResult getIndexResult(Document doc) {
        IndexResult ir = new IndexResult();
        ir.setId(doc.get("id"));
        ir.setTitle(doc.get("title"));
        ir.setNote(doc.get("note"));
        ir.setContent(doc.get("content"));
        return ir;
    }
}

