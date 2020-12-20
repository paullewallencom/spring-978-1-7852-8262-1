package com.packt.spring.aop.ui;

public interface UIFactory {
	UIComponent createComponent(Class<? extends UIComponent> componentClass) throws Exception;
}
