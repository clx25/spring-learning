/*
 * Copyright 2002-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.transaction.annotation;

import io.vavr.control.Try;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.testfixture.CallCountingTransactionManager;

import static org.assertj.core.api.Assertions.*;

/**
 * @author Rob Harrop
 * @author Juergen Hoeller
 */
public class AnnotationTransactionInterceptorTests {

	private final CallCountingTransactionManager ptm = new CallCountingTransactionManager();

	private final AnnotationTransactionAttributeSource source = new AnnotationTransactionAttributeSource();

	private final TransactionInterceptor ti = new TransactionInterceptor(this.ptm, this.source);


	@Test
	public void classLevelOnly() {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(new TestClassLevelOnly());
		proxyFactory.addAdvice(this.ti);

		TestClassLevelOnly proxy = (TestClassLevelOnly) proxyFactory.getProxy();

		proxy.doSomething();
		assertGetTransactionAndCommitCount(1);

		proxy.doSomethingElse();
		assertGetTransactionAndCommitCount(2);

		proxy.doSomething();
		assertGetTransactionAndCommitCount(3);

		proxy.doSomethingElse();
		assertGetTransactionAndCommitCount(4);
	}

	@Test
	public void withSingleMethodOverride() {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(new TestWithSingleMethodOverride());
		proxyFactory.addAdvice(this.ti);

		TestWithSingleMethodOverride proxy = (TestWithSingleMethodOverride) proxyFactory.getProxy();

		proxy.doSomething();
		assertGetTransactionAndCommitCount(1);

		proxy.doSomethingElse();
		assertGetTransactionAndCommitCount(2);

		proxy.doSomethingCompletelyElse();
		assertGetTransactionAndCommitCount(3);

		proxy.doSomething();
		assertGetTransactionAndCommitCount(4);
	}

	@Test
	public void withSingleMethodOverrideInverted() {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(new TestWithSingleMethodOverrideInverted());
		proxyFactory.addAdvice(this.ti);

		TestWithSingleMethodOverrideInverted proxy = (TestWithSingleMethodOverrideInverted) proxyFactory.getProxy();

		proxy.doSomething();
		assertGetTransactionAndCommitCount(1);

		proxy.doSomethingElse();
		assertGetTransactionAndCommitCount(2);

		proxy.doSomethingCompletelyElse();
		assertGetTransactionAndCommitCount(3);

		proxy.doSomething();
		assertGetTransactionAndCommitCount(4);
	}

	@Test
	public void withMultiMethodOverride() {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(new TestWithMultiMethodOverride());
		proxyFactory.addAdvice(this.ti);

		TestWithMultiMethodOverride proxy = (TestWithMultiMethodOverride) proxyFactory.getProxy();

		proxy.doSomething();
		assertGetTransactionAndCommitCount(1);

		proxy.doSomethingElse();
		assertGetTransactionAndCommitCount(2);

		proxy.doSomethingCompletelyElse();
		assertGetTransactionAndCommitCount(3);

		proxy.doSomething();
		assertGetTransactionAndCommitCount(4);
	}

	@Test
	public void withRollbackOnRuntimeException() {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(new TestWithExceptions());
		proxyFactory.addAdvice(this.ti);

		TestWithExceptions proxy = (TestWithExceptions) proxyFactory.getProxy();

		assertThatIllegalStateException().isThrownBy(
				proxy::doSomethingErroneous)
				.satisfies(ex -> assertGetTransactionAndRollbackCount(1));

		assertThatIllegalArgumentException().isThrownBy(
				proxy::doSomethingElseErroneous)
				.satisfies(ex -> assertGetTransactionAndRollbackCount(2));
	}

	@Test
	public void withCommitOnCheckedException() {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(new TestWithExceptions());
		proxyFactory.addAdvice(this.ti);

		TestWithExceptions proxy = (TestWithExceptions) proxyFactory.getProxy();

		assertThatExceptionOfType(Exception.class).isThrownBy(
				proxy::doSomethingElseWithCheckedException)
				.satisfies(ex -> assertGetTransactionAndCommitCount(1));
	}

	@Test
	public void withRollbackOnCheckedExceptionAndRollbackRule() {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(new TestWithExceptions());
		proxyFactory.addAdvice(this.ti);

		TestWithExceptions proxy = (TestWithExceptions) proxyFactory.getProxy();

		assertThatExceptionOfType(Exception.class).isThrownBy(
				proxy::doSomethingElseWithCheckedExceptionAndRollbackRule)
				.satisfies(ex -> assertGetTransactionAndRollbackCount(1));
	}

	@Test
	public void withVavrTrySuccess() {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(new TestWithVavrTry());
		proxyFactory.addAdvice(this.ti);

		TestWithVavrTry proxy = (TestWithVavrTry) proxyFactory.getProxy();

		proxy.doSomething();
		assertGetTransactionAndCommitCount(1);
	}

	@Test
	public void withVavrTryRuntimeException() {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(new TestWithVavrTry());
		proxyFactory.addAdvice(this.ti);

		TestWithVavrTry proxy = (TestWithVavrTry) proxyFactory.getProxy();

		proxy.doSomethingErroneous();
		assertGetTransactionAndRollbackCount(1);
	}

	@Test
	public void withVavrTryCheckedException() {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(new TestWithVavrTry());
		proxyFactory.addAdvice(this.ti);

		TestWithVavrTry proxy = (TestWithVavrTry) proxyFactory.getProxy();

		proxy.doSomethingErroneousWithCheckedException();
		assertGetTransactionAndCommitCount(1);
	}

	@Test
	public void withVavrTryCheckedExceptionAndRollbackRule() {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(new TestWithVavrTry());
		proxyFactory.addAdvice(this.ti);

		TestWithVavrTry proxy = (TestWithVavrTry) proxyFactory.getProxy();

		proxy.doSomethingErroneousWithCheckedExceptionAndRollbackRule();
		assertGetTransactionAndRollbackCount(1);
	}

	@Test
	public void withInterface() {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(new TestWithInterfaceImpl());
		proxyFactory.addInterface(TestWithInterface.class);
		proxyFactory.addAdvice(this.ti);

		TestWithInterface proxy = (TestWithInterface) proxyFactory.getProxy();

		proxy.doSomething();
		assertGetTransactionAndCommitCount(1);

		proxy.doSomethingElse();
		assertGetTransactionAndCommitCount(2);

		proxy.doSomethingElse();
		assertGetTransactionAndCommitCount(3);

		proxy.doSomething();
		assertGetTransactionAndCommitCount(4);

		proxy.doSomethingDefault();
		assertGetTransactionAndCommitCount(5);
	}

	@Test
	public void crossClassInterfaceMethodLevelOnJdkProxy() {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(new SomeServiceImpl());
		proxyFactory.addInterface(SomeService.class);
		proxyFactory.addAdvice(this.ti);

		SomeService someService = (SomeService) proxyFactory.getProxy();

		someService.bar();
		assertGetTransactionAndCommitCount(1);

		someService.foo();
		assertGetTransactionAndCommitCount(2);

		someService.fooBar();
		assertGetTransactionAndCommitCount(3);
	}

	@Test
	public void crossClassInterfaceOnJdkProxy() {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(new OtherServiceImpl());
		proxyFactory.addInterface(OtherService.class);
		proxyFactory.addAdvice(this.ti);

		OtherService otherService = (OtherService) proxyFactory.getProxy();

		otherService.foo();
		assertGetTransactionAndCommitCount(1);
	}

	@Test
	public void withInterfaceOnTargetJdkProxy() {
		ProxyFactory targetFactory = new ProxyFactory();
		targetFactory.setTarget(new TestWithInterfaceImpl());
		targetFactory.addInterface(TestWithInterface.class);

		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(targetFactory.getProxy());
		proxyFactory.addInterface(TestWithInterface.class);
		proxyFactory.addAdvice(this.ti);

		TestWithInterface proxy = (TestWithInterface) proxyFactory.getProxy();

		proxy.doSomething();
		assertGetTransactionAndCommitCount(1);

		proxy.doSomethingElse();
		assertGetTransactionAndCommitCount(2);

		proxy.doSomethingElse();
		assertGetTransactionAndCommitCount(3);

		proxy.doSomething();
		assertGetTransactionAndCommitCount(4);

		proxy.doSomethingDefault();
		assertGetTransactionAndCommitCount(5);
	}

	@Test
	public void withInterfaceOnTargetCglibProxy() {
		ProxyFactory targetFactory = new ProxyFactory();
		targetFactory.setTarget(new TestWithInterfaceImpl());
		targetFactory.setProxyTargetClass(true);

		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(targetFactory.getProxy());
		proxyFactory.addInterface(TestWithInterface.class);
		proxyFactory.addAdvice(this.ti);

		TestWithInterface proxy = (TestWithInterface) proxyFactory.getProxy();

		proxy.doSomething();
		assertGetTransactionAndCommitCount(1);

		proxy.doSomethingElse();
		assertGetTransactionAndCommitCount(2);

		proxy.doSomethingElse();
		assertGetTransactionAndCommitCount(3);

		proxy.doSomething();
		assertGetTransactionAndCommitCount(4);

		proxy.doSomethingDefault();
		assertGetTransactionAndCommitCount(5);
	}

	private void assertGetTransactionAndCommitCount(int expectedCount) {
		assertThat(this.ptm.begun).isEqualTo(expectedCount);
		assertThat(this.ptm.commits).isEqualTo(expectedCount);
	}

	private void assertGetTransactionAndRollbackCount(int expectedCount) {
		assertThat(this.ptm.begun).isEqualTo(expectedCount);
		assertThat(this.ptm.rollbacks).isEqualTo(expectedCount);
	}


	public interface BaseInterface {

		void doSomething();
	}


	@Transactional
	public interface TestWithInterface extends BaseInterface {

		@Transactional(readOnly = true)
		void doSomethingElse();

		default void doSomethingDefault() {
			assertThat(TransactionSynchronizationManager.isActualTransactionActive()).isTrue();
			assertThat(TransactionSynchronizationManager.isCurrentTransactionReadOnly()).isFalse();
		}
	}


	public interface SomeService {

		void foo();

		@Transactional
		void bar();

		@Transactional(readOnly = true)
		void fooBar();
	}


	public interface OtherService {

		void foo();
	}

	@Transactional
	public static class TestClassLevelOnly {

		public void doSomething() {
			assertThat(TransactionSynchronizationManager.isActualTransactionActive()).isTrue();
			assertThat(TransactionSynchronizationManager.isCurrentTransactionReadOnly()).isFalse();
		}

		public void doSomethingElse() {
			assertThat(TransactionSynchronizationManager.isActualTransactionActive()).isTrue();
			assertThat(TransactionSynchronizationManager.isCurrentTransactionReadOnly()).isFalse();
		}
	}

	@Transactional
	public static class TestWithSingleMethodOverride {

		public void doSomething() {
			assertThat(TransactionSynchronizationManager.isActualTransactionActive()).isTrue();
			assertThat(TransactionSynchronizationManager.isCurrentTransactionReadOnly()).isFalse();
		}

		@Transactional(readOnly = true)
		public void doSomethingElse() {
			assertThat(TransactionSynchronizationManager.isActualTransactionActive()).isTrue();
			assertThat(TransactionSynchronizationManager.isCurrentTransactionReadOnly()).isTrue();
		}

		public void doSomethingCompletelyElse() {
			assertThat(TransactionSynchronizationManager.isActualTransactionActive()).isTrue();
			assertThat(TransactionSynchronizationManager.isCurrentTransactionReadOnly()).isFalse();
		}
	}

	@Transactional(readOnly = true)
	public static class TestWithSingleMethodOverrideInverted {

		@Transactional
		public void doSomething() {
			assertThat(TransactionSynchronizationManager.isActualTransactionActive()).isTrue();
			assertThat(TransactionSynchronizationManager.isCurrentTransactionReadOnly()).isFalse();
		}

		public void doSomethingElse() {
			assertThat(TransactionSynchronizationManager.isActualTransactionActive()).isTrue();
			assertThat(TransactionSynchronizationManager.isCurrentTransactionReadOnly()).isTrue();
		}

		public void doSomethingCompletelyElse() {
			assertThat(TransactionSynchronizationManager.isActualTransactionActive()).isTrue();
			assertThat(TransactionSynchronizationManager.isCurrentTransactionReadOnly()).isTrue();
		}
	}

	@Transactional
	public static class TestWithMultiMethodOverride {

		@Transactional(readOnly = true)
		public void doSomething() {
			assertThat(TransactionSynchronizationManager.isActualTransactionActive()).isTrue();
			assertThat(TransactionSynchronizationManager.isCurrentTransactionReadOnly()).isTrue();
		}

		@Transactional(readOnly = true)
		public void doSomethingElse() {
			assertThat(TransactionSynchronizationManager.isActualTransactionActive()).isTrue();
			assertThat(TransactionSynchronizationManager.isCurrentTransactionReadOnly()).isTrue();
		}

		public void doSomethingCompletelyElse() {
			assertThat(TransactionSynchronizationManager.isActualTransactionActive()).isTrue();
			assertThat(TransactionSynchronizationManager.isCurrentTransactionReadOnly()).isFalse();
		}
	}

	@Transactional
	public static class TestWithExceptions {

		public void doSomethingErroneous() {
			assertThat(TransactionSynchronizationManager.isActualTransactionActive()).isTrue();
			assertThat(TransactionSynchronizationManager.isCurrentTransactionReadOnly()).isFalse();
			throw new IllegalStateException();
		}

		public void doSomethingElseErroneous() {
			assertThat(TransactionSynchronizationManager.isActualTransactionActive()).isTrue();
			assertThat(TransactionSynchronizationManager.isCurrentTransactionReadOnly()).isFalse();
			throw new IllegalArgumentException();
		}

		@Transactional
		public void doSomethingElseWithCheckedException() throws Exception {
			assertThat(TransactionSynchronizationManager.isActualTransactionActive()).isTrue();
			assertThat(TransactionSynchronizationManager.isCurrentTransactionReadOnly()).isFalse();
			throw new Exception();
		}

		@Transactional(rollbackFor = Exception.class)
		public void doSomethingElseWithCheckedExceptionAndRollbackRule() throws Exception {
			assertThat(TransactionSynchronizationManager.isActualTransactionActive()).isTrue();
			assertThat(TransactionSynchronizationManager.isCurrentTransactionReadOnly()).isFalse();
			throw new Exception();
		}
	}

	@Transactional
	public static class TestWithVavrTry {

		public Try<String> doSomething() {
			assertThat(TransactionSynchronizationManager.isActualTransactionActive()).isTrue();
			assertThat(TransactionSynchronizationManager.isCurrentTransactionReadOnly()).isFalse();
			return Try.success("ok");
		}

		public Try<String> doSomethingErroneous() {
			assertThat(TransactionSynchronizationManager.isActualTransactionActive()).isTrue();
			assertThat(TransactionSynchronizationManager.isCurrentTransactionReadOnly()).isFalse();
			return Try.failure(new IllegalStateException());
		}

		public Try<String> doSomethingErroneousWithCheckedException() {
			assertThat(TransactionSynchronizationManager.isActualTransactionActive()).isTrue();
			assertThat(TransactionSynchronizationManager.isCurrentTransactionReadOnly()).isFalse();
			return Try.failure(new Exception());
		}

		@Transactional(rollbackFor = Exception.class)
		public Try<String> doSomethingErroneousWithCheckedExceptionAndRollbackRule() {
			assertThat(TransactionSynchronizationManager.isActualTransactionActive()).isTrue();
			assertThat(TransactionSynchronizationManager.isCurrentTransactionReadOnly()).isFalse();
			return Try.failure(new Exception());
		}
	}

	public static class TestWithInterfaceImpl implements TestWithInterface {

		@Override
		public void doSomething() {
			assertThat(TransactionSynchronizationManager.isActualTransactionActive()).isTrue();
			assertThat(TransactionSynchronizationManager.isCurrentTransactionReadOnly()).isFalse();
		}

		@Override
		public void doSomethingElse() {
			assertThat(TransactionSynchronizationManager.isActualTransactionActive()).isTrue();
			assertThat(TransactionSynchronizationManager.isCurrentTransactionReadOnly()).isTrue();
		}
	}

	public static class SomeServiceImpl implements SomeService {

		@Override
		public void bar() {
		}

		@Override
		@Transactional
		public void foo() {
		}

		@Override
		@Transactional(readOnly = false)
		public void fooBar() {
		}
	}

	@Transactional
	public static class OtherServiceImpl implements OtherService {

		@Override
		public void foo() {
		}
	}

}
