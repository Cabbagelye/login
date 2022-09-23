package com.cabbagelye.thread.api.ThreadJoin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName FightQueryExample
 * @Description 实现类
 * @Author Cabbagelye
 * @Date 2022/9/23 14:20
 **/
public class FightQueryExample {

    /**
     * 合作的各大航司
     */
    private static List<String> fightCompany = Arrays.asList("CSA","CEA","HNA");

    public static void main(String[] args) {
        List<String> results = search("SH","BJ");
        System.out.println("================result=============");
        results.forEach(System.out::println);
    }

    private static List<String> search(String original, String destination) {
        List<String> result = new ArrayList<>();
        //创建查询航班信息线程列表
        List<FightQueryTask> tasks = fightCompany.stream().map(fight -> createSearchTask(fight, original, destination)).collect(Collectors.toList());
        tasks.forEach(Thread::start);

        tasks.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //此前，当前线程会阻塞，获取每一个查询线程的结果，并加入到result中；
        tasks.stream().map(FightQuery::get).forEach(result::addAll);
        return result;
    }

    private static FightQueryTask createSearchTask(String fight, String original, String destination) {
        return new FightQueryTask(fight,original,destination);
    }
}
