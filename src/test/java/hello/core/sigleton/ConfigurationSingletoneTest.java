package hello.core.sigleton;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;

public class ConfigurationSingletoneTest {

    @Test
    void configurationTest() {
        final ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        final MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        final OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        final MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        final MemberRepository memberRepository1 = memberService.getMemberRepository();
        final MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService-> memberRepository1 = " + memberRepository1);
        System.out.println("orderService-> memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);

    }

    @Test
    void configurationDeep() {
        final ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        final AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
    }
}
