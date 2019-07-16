package itcast.discovery;

import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2019/7/14.
 */
public class RandomLoadBalance extends AbstractLoadBalance{

    @Override
    protected String doSelect(List<String> repos) {
        int length=repos.size();
        Random random=new Random(); //从repos的集合内容随机获得一个地址
        return repos.get(random.nextInt(length));
    }
}

