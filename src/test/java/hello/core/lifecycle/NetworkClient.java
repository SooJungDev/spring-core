package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * InitializingBean, DisposableBean
 * 초기화 , 소멸 인터페이스 단점
 * 이 인터페이스는 스프링 전용 인터페이스, 스프링 전용 인터페이스에 의존한다
 * 초기화, 소멸 메서드 이름 변경 X
 * 외부라이브러리에 적용할 수 없다
 * 잘 사용하지 않음
 */
public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);

    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }


    /**
     * javax 으로 시작 -> 자바에서 공식적으로 지원
     * 최신 스프링에서 가장 권장하는 방법
     * 애노테이션 하나만 붙이면 되므로 매우 편리
     * 스프링 종속적인 기술 X 스프링이 아닌 다른 컨테이너에서 잘 동작
     * 컴포넌트 스캔과 잘 어울림
     * 유일한 단점 외부 라이브러리에는 적용 X
     * 외부 라이브러리 초기화 종료해야하면 @Bean 기능을 사용하자
     *
     * @PostConstruct, @PreDestroy 어노테이션을 사용하자
     */
    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메세지");
    }


    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
