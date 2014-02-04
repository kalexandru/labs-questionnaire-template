package com.engagepoint.bean;

import com.engagepoint.mock.MockBean;
import com.engagepoint.mock.MockBeansCreator;
import com.engagepoint.mock.impl.bean.MockBeanContainer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by yurii.kukharskyi on 1/28/14.
 */
public class QuestionBeanTest {
    List<MockBean> mockBeanList = new MockBeanContainer().getList();
    MockBeansCreator mockBeansCreator = new MockBeansCreator();

    @Test
    public void testClone() throws Exception {
        for (MockBean mockBean : mockBeanList) {
            mockBeansCreator.setMockBean(mockBean);
            if (mockBeansCreator.getQuestionBean() != null) {
                Assert.assertEquals(mockBeansCreator.getQuestionBean().toString(),
                        mockBeansCreator.getQuestionBean(), mockBeansCreator.getQuestionBean().clone());
            }
        }
    }
}
