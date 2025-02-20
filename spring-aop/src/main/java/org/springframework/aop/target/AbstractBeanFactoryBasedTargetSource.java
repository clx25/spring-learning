/*
 * Copyright 2002-2018 the original author or authors.
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

package org.springframework.aop.target;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.TargetSource;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;

/**
 * Base class for {@link org.springframework.aop.TargetSource} implementations
 * that are based on a Spring {@link org.springframework.beans.factory.BeanFactory},
 * delegating to Spring-managed bean instances.
 *
 * <p>Subclasses can create prototype instances or lazily access a
 * singleton target, for example. See {@link LazyInitTargetSource} and
 * {@link AbstractPrototypeBasedTargetSource}'s subclasses for concrete strategies.
 *
 * <p>BeanFactory-based TargetSources are serializable. This involves
 * disconnecting the current target and turning into a {@link SingletonTargetSource}.
 *
 * @author Juergen Hoeller
 * @author Rod Johnson
 * @see org.springframework.beans.factory.BeanFactory#getBean
 * @see LazyInitTargetSource
 * @see PrototypeTargetSource
 * @see ThreadLocalTargetSource
 * @see CommonsPool2TargetSource
 * @since 1.1.4
 */
public abstract class AbstractBeanFactoryBasedTargetSource implements TargetSource, BeanFactoryAware, Serializable {

	/**
	 * use serialVersionUID from Spring 1.2.7 for interoperability.
	 */
	private static final long serialVersionUID = -4721607536018568393L;


	/**
	 * Logger available to subclasses.
	 */
	protected final Log logger = LogFactory.getLog(getClass());

	/**
	 * Name of the target bean we will create on each invocation.
	 */
	private String targetBeanName;

	/**
	 * Class of the target.
	 */
	private volatile Class<?> targetClass;

	/**
	 * BeanFactory that owns this TargetSource. We need to hold onto this
	 * reference so that we can create new prototype instances as necessary.
	 */
	private BeanFactory beanFactory;

	/**
	 * Return the name of the target bean in the factory.
	 */
	public String getTargetBeanName() {
		return this.targetBeanName;
	}

	/**
	 * Set the name of the target bean in the factory.
	 * <p>The target bean should not be a singleton, else the same instance will
	 * always be obtained from the factory, resulting in the same behavior as
	 * provided by {@link SingletonTargetSource}.
	 *
	 * @param targetBeanName name of the target bean in the BeanFactory
	 *                       that owns this interceptor
	 * @see SingletonTargetSource
	 */
	public void setTargetBeanName(String targetBeanName) {
		this.targetBeanName = targetBeanName;
	}

	/**
	 * Return the owning BeanFactory.
	 */
	public BeanFactory getBeanFactory() {
		return this.beanFactory;
	}

	/**
	 * Set the owning BeanFactory. We need to save a reference so that we can
	 * use the {@code getBean} method on every invocation.
	 */
	@Override
	public void setBeanFactory(BeanFactory beanFactory) {
		if (this.targetBeanName == null) {
			throw new IllegalStateException("Property 'targetBeanName' is required");
		}
		this.beanFactory = beanFactory;
	}

	@Override
	public Class<?> getTargetClass() {
		Class<?> targetClass = this.targetClass;
		if (targetClass != null) {
			return targetClass;
		}
		synchronized (this) {
			// Full check within synchronization, entering the BeanFactory interaction algorithm only once...
			targetClass = this.targetClass;
			if (targetClass == null && this.beanFactory != null) {
				// Determine type of the target bean.
				targetClass = this.beanFactory.getType(this.targetBeanName);
				if (targetClass == null) {
					if (logger.isTraceEnabled()) {
						logger.trace("Getting bean with name '" + this.targetBeanName + "' for type determination");
					}
					Object beanInstance = this.beanFactory.getBean(this.targetBeanName);
					targetClass = beanInstance.getClass();
				}
				this.targetClass = targetClass;
			}
			return targetClass;
		}
	}

	/**
	 * Specify the target class explicitly, to avoid any kind of access to the
	 * target bean (for example, to avoid initialization of a FactoryBean instance).
	 * <p>Default is to detect the type automatically, through a {@code getType}
	 * call on the BeanFactory (or even a full {@code getBean} call as fallback).
	 */
	public void setTargetClass(Class<?> targetClass) {
		this.targetClass = targetClass;
	}

	@Override
	public boolean isStatic() {
		return false;
	}

	@Override
	public void releaseTarget(Object target) throws Exception {
		// Nothing to do here.
	}


	/**
	 * Copy configuration from the other AbstractBeanFactoryBasedTargetSource object.
	 * Subclasses should override this if they wish to expose it.
	 *
	 * @param other object to copy configuration from
	 */
	protected void copyFrom(AbstractBeanFactoryBasedTargetSource other) {
		this.targetBeanName = other.targetBeanName;
		this.targetClass = other.targetClass;
		this.beanFactory = other.beanFactory;
	}


	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other == null || getClass() != other.getClass()) {
			return false;
		}
		AbstractBeanFactoryBasedTargetSource otherTargetSource = (AbstractBeanFactoryBasedTargetSource) other;
		return (ObjectUtils.nullSafeEquals(this.beanFactory, otherTargetSource.beanFactory) &&
				ObjectUtils.nullSafeEquals(this.targetBeanName, otherTargetSource.targetBeanName));
	}

	@Override
	public int hashCode() {
		int hashCode = getClass().hashCode();
		hashCode = 13 * hashCode + ObjectUtils.nullSafeHashCode(this.beanFactory);
		hashCode = 13 * hashCode + ObjectUtils.nullSafeHashCode(this.targetBeanName);
		return hashCode;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(getClass().getSimpleName());
		sb.append(" for target bean '").append(this.targetBeanName).append("'");
		if (this.targetClass != null) {
			sb.append(" of type [").append(this.targetClass.getName()).append("]");
		}
		return sb.toString();
	}

}
