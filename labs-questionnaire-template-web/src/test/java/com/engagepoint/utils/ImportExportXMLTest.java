package com.engagepoint.utils;

import com.engagepoint.controller.page.FileDownloadController;
import com.engagepoint.mock.MockTemplate;
import com.engagepoint.model.questionnaire.QuestionType;
import com.engagepoint.model.questionnaire.TemplateBean;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
* Created with IntelliJ IDEA.
* User: anton.kovunov
* Date: 2/10/14
* Time: 11:53 AM
* To change this template use File | Settings | File Templates.
*/
public class ImportExportXMLTest {
    MockTemplate mock = new MockTemplate(QuestionType.CHECKBOX);
    @Test
    public void testEqualityXML() throws FileNotFoundException, FileNotFoundException {
//        Question bean = new CheckBoxQuestionBean("blah",false, QuestionType.CHECKBOX);
//        Question eqBean = new CheckBoxQuestionBean("blah",true, QuestionType.CHECKBOX);
//        //---------------------------------1---------------------------------------
//        List<Question> questionBeanList = new ArrayList<Question>();
//        questionBeanList.add(bean);
//        questionBeanList.add(eqBean);
//        //-----------------------------1----------------------------------------------
//        GroupBean testBean = new GroupBean("EqBeans",questionBeanList);
//        GroupBean testBean2 = new GroupBean("EqBeans",questionBeanList);
//        ArrayList<GroupBean> groupBeans = new ArrayList<GroupBean>();
//        groupBeans.add(testBean);
//        groupBeans.add(testBean2);
//        //--------------------------------1------------------------------------
//        SectionBean section1 = new SectionBean(2,groupBeans);
//        Question bean1 = new CheckBoxQuestionBean("blah",true, QuestionType.CHECKBOX);
//        Question eqBean1 = new CheckBoxQuestionBean("blah",true, QuestionType.CHECKBOX);
//        //-----------------------------------1-------------------------------
//        List<Question> questionBeanList1 = new ArrayList<Question>();
//        questionBeanList1.add(bean1);
//        questionBeanList1.add(eqBean1);
//        //-------------------------------------------1------------------------------
//        GroupBean groupBean2 = new GroupBean("EqBeans",questionBeanList1);
//        GroupBean groupBean3 = new GroupBean("EqBeans",questionBeanList1);
//        ArrayList<GroupBean> groupBeans1 = new ArrayList<GroupBean>();
//        groupBeans1.add(groupBean2);
//        groupBeans1.add(groupBean3);
//        SectionBean section2 = new SectionBean(2,groupBeans1);
//        //----------------------------------1-----------------------------
//        ArrayList<SectionBean> sectionsList = new ArrayList<SectionBean>();
//        sectionsList.add(section1);
//        sectionsList.add(section2);
//        TemplateBean testTemplate = new TemplateBean(new Long(5),"Questionnaire",sectionsList);
//        //end of first template
//        //
//        Question bean12 = new CheckBoxQuestionBean("blah",false, QuestionType.CHECKBOX);
//        Question eqBean13 = new CheckBoxQuestionBean("blah",true, QuestionType.CHECKBOX);
//        List<Question> questionBeanList13 = new ArrayList<Question>();
//        questionBeanList13.add(bean12);
//        questionBeanList13.add(eqBean13);
//        GroupBean testBean21 = new GroupBean("EqBeans",questionBeanList13);
//        GroupBean testBean22 = new GroupBean("EqBeans",questionBeanList13);
//        ArrayList<GroupBean> groupBeans12 = new ArrayList<GroupBean>();
//        groupBeans12.add(testBean21);
//        groupBeans12.add(testBean22);
//        SectionBean section12 = new SectionBean(2,groupBeans12);
//        ArrayList<GroupBean> groupBeans11 = new ArrayList<GroupBean>();
//        Question bean18 = new CheckBoxQuestionBean("blah",true, QuestionType.CHECKBOX);
//        Question eqBean19 = new CheckBoxQuestionBean("blah",true, QuestionType.CHECKBOX);
//        List<Question> questionBeanList14 = new ArrayList<Question>();
//        questionBeanList14.add(bean18);
//        questionBeanList14.add(eqBean19);
//        GroupBean groupBean24 = new GroupBean("EqBeans",questionBeanList14);
//        GroupBean groupBean37 = new GroupBean("EqBeans",questionBeanList14);
//        groupBeans11.add(groupBean24);
//        groupBeans11.add(groupBean37);
//        SectionBean section21 = new SectionBean(2,groupBeans11);
//        ArrayList<SectionBean> sectionsList1 = new ArrayList<SectionBean>();
//        sectionsList1.add(section12);
//        sectionsList1.add(section21);
//        TemplateBean testTemplate1 = new TemplateBean(new Long(5),"Questionnaire",sectionsList1);
//        List<TemplateBean> tempList = new ArrayList<TemplateBean>();
//        tempList.add(testTemplate);
//        tempList.add(testTemplate1);

        List<TemplateBean> tempList = new ArrayList<TemplateBean>();
        tempList.add(mock.template());

        //-----------------------------------------------------------------------------------------
        File file = null;
        try {
            file = FileDownloadController.createTempXml(tempList);
        }
        catch (IOException e) {
            Assert.fail();
            return;
        }
        FileInputStream stream = new FileInputStream(file.getAbsolutePath());
        List<TemplateBean> newTempsList = new ArrayList<TemplateBean>();
        newTempsList.addAll(XmlImportExport.importXmlTemplate(stream));
        Assert.assertTrue(tempList.equals(newTempsList));
    }
}
