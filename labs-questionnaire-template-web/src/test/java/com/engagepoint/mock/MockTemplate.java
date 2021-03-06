package com.engagepoint.mock;

import com.engagepoint.model.question.options.CheckBoxQuestionBean;
import com.engagepoint.model.question.options.ChooseFromListQuestionBean;
import com.engagepoint.model.question.options.MultipleChoiceQuestionBean;
import com.engagepoint.model.question.utils.RangeItem;
import com.engagepoint.model.question.utils.VariantItem;
import com.engagepoint.model.questionnaire.GroupBean;
import com.engagepoint.model.questionnaire.QuestionType;
import com.engagepoint.model.questionnaire.SectionBean;
import com.engagepoint.model.questionnaire.TemplateBean;
import com.engagepoint.model.question.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;




/**
* Created by oleksandr.koval on 2/25/14.
*/
public class MockTemplate {
    TemplateBean templateBean;
    List<SectionBean> sectionBeans;
    List<GroupBean> groupBeans;
    List<Question> questionBeans;
    Question question;

    private QuestionType selectedQuestionType  ;

    public MockTemplate(QuestionType selectedQuestionType) {
        this.selectedQuestionType = selectedQuestionType;
        sectionBeans = new ArrayList<SectionBean>();
        questionBeans = new ArrayList<Question>();
        groupBeans =new ArrayList<GroupBean>();
        sectionBeans.add(creatorSections());
        templateBean = new TemplateBean();
        templateBean.setSectionsList(sectionBeans);
        groupBeans.add(creatorGroup());
        questionBeans.add(typeOfQuestion());
    }


    public Question typeOfQuestion(){


        switch (selectedQuestionType) {
            case TEXT:
                question =  new TextQuestionBean();
                TextQuestionBean tb =(TextQuestionBean)question;
                //tb.setDefaultAnswer("i don't know");
                creator(question);
                return question;
            case DATE:
                question = new DateQuestionBean();
                DateQuestionBean dateQuestionBean = (DateQuestionBean)question;

                dateQuestionBean.setDefaultDate(new Date());
                creator(question);

                return question;
            case RANGE:
                question = new RangeQuestionBean();
                RangeItem rangeItem=new RangeItem();
                RangeQuestionBean rangeQuestionBean = (RangeQuestionBean)question;
                rangeQuestionBean.setRangeItem(rangeItem);
                rangeQuestionBean.getRangeItem().setMaxValue("do not need");
                rangeQuestionBean.getRangeItem().setMinValue("help");
                creator(question);

                return question;
            case TIME:
                question = new DateQuestionBean();
                DateQuestionBean timeQuestionBean = (DateQuestionBean)question;
                timeQuestionBean.setDefaultDate(new Date());
                creator(question);
                return question;
            case PARAGRAPHTEXT:
                question = new TextQuestionBean();
                TextQuestionBean paragraph =(TextQuestionBean)question;
                //paragraph.setDefaultAnswer("i don't know");
                creator(question);
                return question;

            case CHOOSEFROMLIST:
                question = new ChooseFromListQuestionBean();
                ChooseFromListQuestionBean choose = (ChooseFromListQuestionBean)question;
                VariantItem variantItem = new  VariantItem();
                variantItem.setValue("nice");
                choose.setDefaultOption(variantItem);
                List<VariantItem>variantItemList = new ArrayList<VariantItem>();
                variantItemList.add(variantItem);
                choose.setOptions(variantItemList);
                creator(question);

                return question;
            case FILEUPLOAD:
                question = new TextQuestionBean();
                creator(question);
                return question;

            case MULTIPLECHOICE:
                question = new MultipleChoiceQuestionBean();
                creator(question);
                MultipleChoiceQuestionBean multiple = (MultipleChoiceQuestionBean)question;
                VariantItem variant = new  VariantItem();
                variant.setValue("nice");

                List<VariantItem>variantList = new ArrayList<VariantItem>();
                variantList.add(variant);
                multiple.setOptions(variantList);
                multiple.setDefaultOption(variant);

                return question;

            case CHECKBOX:
                question = new CheckBoxQuestionBean();
                creator(question);
                CheckBoxQuestionBean checkbox = (CheckBoxQuestionBean)question;
                VariantItem variantc = new  VariantItem();
                variantc.setValue("nice");

                List<VariantItem>variantLists = new ArrayList<VariantItem>();
                variantLists.add(variantc);
                checkbox.setOptions(variantLists);
                checkbox.setDefaultOptions(variantLists);

                return question;

        }

        return question;
    }
    public void creator(Question question){
        question.setHelpText("its my help");
        question.setQuestionText("WHY UYoA ArE SO SAD?");
        question.setId("1567");

    }

    public SectionBean creatorSections(){
        SectionBean sectionBean=  new  SectionBean();
        sectionBean.setGroupsList(groupBeans);
        //sectionBean.setPageNumber(1);
        return sectionBean;
    }
    public GroupBean creatorGroup(){
        GroupBean groupBean = new GroupBean();
        groupBean.setQuestionsList(questionBeans);
        groupBean.setGroupName("GroupName");
        return groupBean;
    }

    public Question question(){
        return question;
    }
    public TemplateBean template(){return templateBean;}

}
