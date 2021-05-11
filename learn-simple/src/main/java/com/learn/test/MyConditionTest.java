package com.learn.test;

import com.learn.condition.MyCondition;
import org.springframework.context.annotation.Conditional;

@Conditional(MyCondition.class)
public class MyConditionTest {
}
