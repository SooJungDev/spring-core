package hello.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    /**
     * 수동 빈이 우선권을 갖음
     * 자동빈 오버라이딩 자동으로 해줌
     * 최근 스프링 부트에서는 수동빈 자동빈 등록 오류시 스프링 부트 에러를 내면서 아예 어플리케이션 실행안됨!!!
     * @return
     */
 /*   @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }*/
}
