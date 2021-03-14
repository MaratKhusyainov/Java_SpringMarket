package geekbrains.Aop;

import lombok.extern.log4j.Log4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Aspect
@Component
@Log4j
public class AppLoggingAspect {
    private List<String> methodSignatureList;
    private Map<String, Long> map;
    private long authDuration;
    private long cartDuration;
    private long orderDuration;
    private long productDuration;
    private long userDuration;

    @PostConstruct
    public void init() {
        methodSignatureList = new ArrayList<>();
        map = new HashMap<>();
    }

    @Pointcut("execution(public * ru.geekbrains.market.beans.Cart.*(..))")
    public void cartMethodTrackerPointcut() {
    }

    @Pointcut("execution(public * ru.geekbrains.market.service.*.*(..))")
    public void controllersMethodTrackerPointcut() {
    }

    @Pointcut("execution(public * ru.geekbrains.market.repository.*.*(..))")
    public void repositoriesMethodTrackerPointcut() {
    }

    @Pointcut("cartMethodTrackerPointcut()  || controllersMethodTrackerPointcut() || repositoriesMethodTrackerPointcut()")
    public void mostUsableMethodTrackerPointcut() {
    }

    @After("mostUsableMethodTrackerPointcut()")
    public void beforeAnyMethodInProject(JoinPoint joinPoint) {
        String m = joinPoint.getSignature().toString();
        methodSignatureList.add(m.toString());
        methodSignatureList.stream()
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet().stream().sorted((e1, e2) -> e2.getValue().intValue() - e1.getValue().intValue())
                .limit(1)
                .findFirst()
                .ifPresent(w -> log.info("most usable method " + w.getKey() + " with count " + w.getValue()));
    }

    @Around("execution(public * ru.geekbrains.market.controller.*.*(..))")
    public Object methodProfiling(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        if (proceedingJoinPoint.getSignature().toString().contains("Auth")) {
            authDuration += end - begin;
            map.put("auth", authDuration);
        } else if (proceedingJoinPoint.getSignature().toString().contains("Cart")) {
            cartDuration += end - begin;
            map.put("cart", cartDuration);
        } else if (proceedingJoinPoint.getSignature().toString().contains("Order")) {
            orderDuration += end - begin;
            map.put("order", orderDuration);
        } else if (proceedingJoinPoint.getSignature().toString().contains("Product")) {
            productDuration += end - begin;
            map.put("product", productDuration);
        } else {
            userDuration += end - begin;
            map.put("user", userDuration);
        }
        Object maxEntry = Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey();
        Object maxValue = Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getValue();
        log.info("max time for methods in controller - " + maxEntry + "Controller  with time " + maxValue);
        return out;
    }
}
