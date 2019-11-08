package com.dy.java8.demo.stream;

import java.util.concurrent.RecursiveTask;

/**
 * @author dingyu
 * @description 实现数的累加
 * ForkJoin框架思想：工作窃取模式，当执行新的任务时候，分成一个个小任务执行，加到线程队列中，工作完成的线程然后再从随机的阻塞的线程的队列中随机偷取一个放到自己队列中执行
 * 这样就减少线程阻塞导致后面任务没有执行。更多利用cpu资源
 *
 * RecursiveTask：有返回值
 * RecursiveAction: 无返回时
 * @date 2019/11/8
 */
public class ForkJoin extends RecursiveTask<Long> {

    private long start;
    private long end;

    private static final long THRESHOLD=10000;

    public ForkJoin(long start, long end) {
        this.start = start;
        this.end =  end;
    }

    @Override
    protected Long compute() {
        long length=start-end;
        if (length<THRESHOLD){
            long sum=0;
            for (long i=0;i<=end;i++){
                sum+=i;
            }
            return sum;
        }else {
            //拆分 分为两部分
            long mid=(start+end)/2;
            ForkJoin left=new ForkJoin(start,mid);
            left.fork(); //拆分子任务 压入到线程队列中


            ForkJoin right=new ForkJoin(mid+1,end);
            right.fork();

            // 合并
            return left.join()+right.join();
        }
    }
}
