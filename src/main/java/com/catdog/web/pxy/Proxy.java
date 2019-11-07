package com.catdog.web.pxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.catdog.web.brd.ArticleMapper;
import com.catdog.web.cmm.ISupplier;
import com.catdog.web.utl.Printer;
import lombok.Data;
@Component @Data @Lazy
public class Proxy {
    private int totalCount, startRow, endRow,
    			pageCount, pageNum, pageSize, startPage, endPage, 
    			blockCount, blockNum, nextBlock, prevBlock;
    private String search;
    private final int BLOCK_SIZE = 5;
    private boolean existPrev, existNext;
    @Autowired Printer p;
    @Autowired ArticleMapper articleMapper;
    
    @SuppressWarnings("unused")
    public void paging() {
    	ISupplier<String> s = ()->articleMapper.countArticle();
    	totalCount = Integer.parseInt(s.get());
    	pageCount = (totalCount%pageSize==0)?totalCount/pageSize:(totalCount/pageSize)+1;
    	startRow = (pageNum-1) * pageSize;
    	endRow = (pageNum==pageCount) ? totalCount -1 : startRow + pageSize -1;
    	blockCount = (pageCount%BLOCK_SIZE==0)?pageCount/BLOCK_SIZE:(pageCount/BLOCK_SIZE)+1;
    	blockNum = (pageNum - 1) / BLOCK_SIZE;
    	startPage = blockNum*BLOCK_SIZE + 1;
    	endPage = (BLOCK_SIZE * (blockNum + 1) > pageCount) ? pageCount : BLOCK_SIZE * (blockNum + 1);
    	existPrev = blockNum > 0;
    	existNext = blockNum < blockCount -1;
    	prevBlock = startPage - BLOCK_SIZE;
    	nextBlock = startPage + BLOCK_SIZE;
    }
    public int parseInt(String param) {
    	Function<String, Integer> f = s -> Integer.parseInt(s);
    	return f.apply(param);
    }
    public List<?> crawl(Map<?,?> paramMap){
    
        String url = "http://"+paramMap.get("site")+"/";
        p.accept("넘어온 URL \n"+url);
        List<String> proxyList = new ArrayList<>();
        proxyList.clear();
        try {
            Connection.Response response = Jsoup.connect(url)
                                                .method(Connection.Method.GET)
                                                .execute();
            Document document = response.parse();
            //String text = document.html();
            String text = document.text();
            p.accept("크롤링한 텍스트 \n"+text);
            proxyList.add(text);
            
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        
        return proxyList;
    }
    public int random(int min, int max) {
		BiFunction<Integer, Integer, Integer> f = (t,u) -> (int) (Math.random()*u-t)+t;
    	return f.apply(min,max);
    }
}