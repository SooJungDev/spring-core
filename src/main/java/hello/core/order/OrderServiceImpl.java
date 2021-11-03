package hello.core.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import hello.core.annotaion.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /**
     * 생성자가 딱 1개 만 있으면 @Autowired 를 생략해도 자동주입된다.
     * 물론 스프링 빈에만 해당됨
     * 생성자 주입 방식을 선택하는 이유는 여러가지가 있지만 프레임워크에 의존하지 않고 순수한 자바 언어의 특징을 잘 살리는 방법이기도 하다
     * 기본으로 생성자 주입을 사용하고, 필수값이 아닌 경우에는 수정자 주입 방식을 옵션으로 부여하면 된다.
     * 생성자 주입과 수정자 주입을 동시에 사용할 수 있다
     * 항상 생성자 주입을 선택해라!
     * 그리고 가끔 옵션이 필요하면 수정자 주입을 선택 필드 주입은 사용하지 않는게 좋음(필드 주입은 외부주입 X, 테스트 만들기 부적합)!!
     */
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        // System.out.println("memberRepository = " + memberRepository);
        // System.out.println("discountPolicy = " + discountPolicy);
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        final Member member = memberRepository.findById(memberId);
        final int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
