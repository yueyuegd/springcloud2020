package net.zhangyue.consumer.order.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalance {

    ServiceInstance instance(List<ServiceInstance> serviceInstanceList);
}
