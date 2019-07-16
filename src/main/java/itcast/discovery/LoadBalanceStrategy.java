package itcast.discovery;

import java.util.List;

/**
 * Created by Administrator on 2019/7/14.
 */
public interface LoadBalanceStrategy {
    String selectHost(List<String> repos);
}
