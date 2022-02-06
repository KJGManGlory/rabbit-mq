package com.lizza.rabbit.mq.job.task;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
//@ElasticJobConfig
@Component
public class RabbitSimpleJob implements SimpleJob {

	/**
	 *
	 * @param shardingContext	分片上下文
	 * @return void
	 */
//	@JobTrace
	@Override
	public void execute(ShardingContext shardingContext) {

		log.info("=================== rabbit simple job start ===================");
//		
//		log.info(shardingContext.getJobName());
//		log.info(shardingContext.getJobParameter());
//		log.info(shardingContext.getShardingItem());
//		log.info(shardingContext.getShardingParameter());
//		log.info(shardingContext.getShardingTotalCount());
//		log.info("当前线程 : ---------------" + Thread.currentThread().getName());
//		
//		log.info("----------结束任务------");
	}
	
	
	
}