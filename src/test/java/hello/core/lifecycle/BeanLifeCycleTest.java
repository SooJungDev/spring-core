package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    /**
     * 스프링 빈은 다음과 같은 라이프 사이클
     * 객체 생성 -> 의존관계 주입
     */
    @Configuration
    static class LifeCycleConfig {

        /**
         * 메소드 이름을 자유롭게 줄수 있다
         * 스프링 빈이 스프링 코드에 의존하지 않는다.
         * 코드가 아니라 설정 정보를 사용하기 때문에 코드를 고칠수 없는 외부 라이브러리에도 초기화 , 종료 메소드를 적용할 수 있다.
         * <p>
         * 종료메소드 추론
         * inferred(추론)
         * close, shutdown 이라는 메서드를 자동으로 호출해준다. 이름 그대로 종료메소드를 추론해서 호출해준다.
         * 종료 메소드를 따로 적어주지 않아도 잘 동작한다.
         * 추론 기능을 사용하기 싫으면 destroyMethod = "" 넣는다.
         */
        //@Bean(initMethod = "init", destroyMethod = "close")
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
