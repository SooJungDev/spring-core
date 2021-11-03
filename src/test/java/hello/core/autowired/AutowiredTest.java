package hello.core.autowired;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import hello.core.member.Member;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        final ApplicationContext ac =
                new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean {

        // 호출안됨
        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        // null 호출
        @Autowired
        public void setNoBean2(@Nullable Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        // Optional.empty 호출
        @Autowired
        public void setNobean3(Optional<Member> nobean3) {
            System.out.println("nobean3 = " + nobean3);
        }
    }
}
