package hello.aop.internalcall;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 참고: 생성자 주입은 순환 사이클을 만들기 때문에 실패한다.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CallServiceV2 {
  /**
   * ApplicationContext을 사용해도 되나 굳이 사용 할 필요 없음 (개미 잡는데 소 잡는 칼 쓰는 격)
   */
//  private final ApplicationContext applicationContext;
//
//  public CallServiceV2(ApplicationContext applicationContext) {
//    this.applicationContext = applicationContext;
//  }

  private final ObjectProvider<CallServiceV2> callServiceProvider;

  public void external() {
    log.info("call external");
//    CallServiceV2 callServiceV2 = applicationContext.getBean(CallServiceV2.class);
    CallServiceV2 callServiceV2 = callServiceProvider.getObject();
    callServiceV2.internal(); // calling external method
  }

  public void internal() {
    log.info("call internal");
  }
}
