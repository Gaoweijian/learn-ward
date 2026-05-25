package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Optional;

/**
 * Controller 层接口日志切面，记录入参、出参及执行耗时
 */
@Slf4j
@Aspect
@Component
public class ControllerLogAspect {

    /**
     * 切点：匹配 com.example.demo.controller 包及其子包下所有 public 方法
     */
    @Pointcut("execution(public * com.example.demo.controller..*.*(..))")
    public void controllerLog() {
    }

    @Around("controllerLog()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = signature.getDeclaringType().getSimpleName();
        String methodName = signature.getName();

        // 组装入参日志
        String argsStr = buildArgsString(signature, joinPoint.getArgs());

        long start = System.currentTimeMillis();
        Object result;
        try {
            result = joinPoint.proceed();
            long elapsed = System.currentTimeMillis() - start;

            String resultStr = buildResultString(result);
            log.info("[{}#{}] 入参: {} | 出参: {} | 耗时: {}ms",
                    className, methodName, argsStr, resultStr, elapsed);
        } catch (Throwable e) {
            long elapsed = System.currentTimeMillis() - start;
            log.error("[{}#{}] 入参: {} | 异常: {} | 耗时: {}ms",
                    className, methodName, argsStr, e.getClass().getSimpleName() + ": " + e.getMessage(), elapsed);
            throw e;
        }

        return result;
    }

    /**
     * 构建方法入参字符串
     */
    private String buildArgsString(MethodSignature signature, Object[] args) {
        if (args == null || args.length == 0) {
            return "";
        }
        Parameter[] parameters = signature.getMethod().getParameters();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            String paramName = parameters[i].getName();
            String valueStr = formatArg(args[i]);
            sb.append(paramName).append("=").append(valueStr);
        }
        return sb.toString();
    }

    /**
     * 构建出参字符串，解包 ResponseEntity/Page/Optional 等包装类型，展示实际数据
     */
    private String buildResultString(Object result) {
        if (result == null) {
            return "null";
        }
        return formatValue(extractBody(result));
    }

    /**
     * 提取实际数据：ResponseEntity → body, Page → content, Optional → value
     */
    private Object extractBody(Object obj) {
        if (obj == null) {
            return null;
        }
        // 解包 ResponseEntity
        if (obj instanceof ResponseEntity) {
            return extractBody(((ResponseEntity<?>) obj).getBody());
        }
        // 解包 Page → 返回 content 列表
        if (obj instanceof Page) {
            List<?> content = ((Page<?>) obj).getContent();
            return content;
        }
        // 解包 Optional
        if (obj instanceof Optional) {
            return ((Optional<?>) obj).orElse(null);
        }
        return obj;
    }

    /**
     * 格式化值，列表类型展开每项
     */
    private String formatValue(Object obj) {
        if (obj == null) {
            return "null";
        }
        if (obj instanceof List) {
            List<?> list = (List<?>) obj;
            if (list.isEmpty()) {
                return "[]";
            }
            StringBuilder sb = new StringBuilder("[");
            int count = 0;
            for (Object item : list) {
                if (count > 0) {
                    sb.append(", ");
                }
                // 每项截断到 150 字符
                String itemStr = item == null ? "null" : item.toString();
                if (itemStr.length() > 150) {
                    itemStr = itemStr.substring(0, 150) + "...";
                }
                sb.append(itemStr);
                count++;
                if (sb.length() > 2000) {
                    sb.append(", ...(共").append(list.size()).append("条)");
                    break;
                }
            }
            sb.append("]");
            return sb.toString();
        }
        String str = obj.toString();
        if (str.length() > 500) {
            str = str.substring(0, 500) + "...(truncated)";
        }
        return str;
    }

    /**
     * 格式化单个参数值
     */
    private String formatArg(Object arg) {
        if (arg == null) {
            return "null";
        }
        String str = arg.toString();
        if (str.length() > 200) {
            str = str.substring(0, 200) + "...";
        }
        return str;
    }
}
