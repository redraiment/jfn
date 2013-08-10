jfn
===

考虑这样一个问题：
1. 在[1, 100]这个区间内，从1开始，间隔13，依次取出这些数。即1、14、27……92
2. 过滤出不小于10的数。即14、27、40……92
3. 将过滤出来的数都加上20。即变成34、47、60……112
4. 输出这些数的和。即511

这里不考虑“算法”优化的问题，因为这是一个抽象的问题。

如果使用Java来开发，代码可能看起来像这样：

```java
package me.zzp.fn;

import java.util.LinkedList;
import java.util.List;

public class CoreTest {
    public static void main(String[] args) {
        List<Integer> seq = new LinkedList<>();
        
        for (int i = 1; i <= 100; i += 13) {
            seq.add(i);
        }

        for (int i = seq.size() - 1; i >= 0; i--) {
            if (seq.get(i) < 10) {
                seq.remove(i);
            }
        }

        for (int i = 0; i < seq.size(); i++) {
            seq.set(i, seq.get(i) + 20);
        }
        
        int sum = 0;
        for (Integer i : seq) {
            sum += i;
        }
        System.out.println(sum);
    }
}
```

看起来真的是太繁琐了！其中充斥着重复冗余的循环。

如果改用函数式编程的思想，就只需要这短短的一行代码：

```java
import static me.zzp.fn.Core.*;

public class CoreTest {
    public static void main(String[] args) {
        println(reduce(F.add, map(partial(F.add, 20), filter(partial(F.less, 10), range(1, 100, 13)))));
    }
}
```

jfn让这一切成为可能！在Java环境中使用函数式编程。它只是Java的一个超轻量级的库，因此没有改变Java的语法，在JDK 1.5及以上版本都能运行。