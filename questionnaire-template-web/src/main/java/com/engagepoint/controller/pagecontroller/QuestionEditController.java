package com.engagepoint.controller.pagecontroller;

import java.io.Serializable;

import com.engagepoint.bean.GroupBean;
import com.engagepoint.bean.QuestionBeans.*;
import com.engagepoint.bean.QuestionType;
import com.engagepoint.controller.TemplateTreeController;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Used for controlling questionEdit.xhtml
 */

@Named("questionController")
@RequestScoped
public class QuestionEditController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    //temp properties
    private QuestionType selectedQuestionType;
    private QuestionBean currentQuestion;
    private String questionText = "";        //question text
    private boolean requiredAnswer;          //is answer required or not
    private String helpText = "";            //Help texts for questions
    //...temp properties

    @Inject
    private TemplateTreeController templateTreeController;

    @PostConstruct
    private void postConstruct() {
        changeTempPropertiesDueToCurrentQuestion();
    }

    public QuestionBean getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(QuestionBean currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public QuestionType getSelectedQuestionType() {
        return selectedQuestionType;
    }

    public void setSelectedQuestionType(QuestionType selectedQuestionType) {
        this.selectedQuestionType = selectedQuestionType;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public boolean isRequiredAnswer() {
        return requiredAnswer;
    }

    public void setRequiredAnswer(boolean requiredAnswer) {
        this.requiredAnswer = requiredAnswer;
    }

    public String getHelpText() {
        return helpText;
    }

    public void setHelpText(String helpText) {
        this.helpText = helpText;
    }

    public QuestionType[] getQuestionTypes() {
        return QuestionType.values();
    }

    public String getChangeQuestionType() {
        String notChoose = "/question-pages/notChooseQuestion.xhtml";
        if (selectedQuestionType == null) return notChoose;
        switch (selectedQuestionType) {
            case TEXT:
                currentQuestion = new TextQuestionBean();
                return "/question-pages/textQuestion.xhtml";
            case DATE:
                currentQuestion = new DateQuestionBean();
                return "/question-pages/dateQuestion.xhtml";
            case RANGE:
                currentQuestion = new RangeQuestionBean();
                return "/question-pages/rangeQuestion.xhtml";
            case TIME:
                currentQuestion = new DateQuestionBean();
                return "/question-pages/timeQuestion.xhtml";
            case PARAGRAPHTEXT:
                currentQuestion = new TextQuestionBean();
                return "/question-pages/paragraphQuestion.xhtml";
            case CHOOSEFROMLIST:
                currentQuestion = new OptionsQuestionBean();
                return "/question-pages/chooseFromListQuestion.xhtml";
            case FILEUPLOAD:
                currentQuestion = new TextQuestionBean();
                return "/question-pages/fileUploadQuestion.xhtml";
            case MULTIPLECHOICE:
                currentQuestion = new OptionsQuestionBean();
                return "/question-pages/stab.xhtml";
            default:
                return notChoose;
        }
    }

    /**
     * Saving common properties of question
     * and adding question to group (if new)
     */
    public void addQuestionToTree() {
        changeCurrentQuestionDueToTempProperties();
        templateTreeController.addQuestionToCurrentGroup(getCurrentQuestion());
    }

    /**
     * Pulling values of common properties of
     * current question to temp properties
     */
    public void changeTempPropertiesDueToCurrentQuestion() {
        QuestionBean question = getCurrentQuestion();
        if (question != null) {
            selectedQuestionType = question.getQuestionType();
            questionText = question.getQuestionText();
            requiredAnswer = question.isRequiredAnswer();
            helpText = question.getHelpText();
        }
    }

    /**
     * Pushing values of common properties from
     * temp properties to current question
     */
    public void changeCurrentQuestionDueToTempProperties() {
        QuestionBean question = getCurrentQuestion();
        question.setQuestionType(selectedQuestionType);
        question.setHelpText(helpText);
        question.setRequiredAnswer(requiredAnswer);
        question.setQuestionText(questionText);
    }

    /**
     * Creating a new question with type CHOOSEFROMLIST
     */
    public void newChooseFromListQuestion() {
        OptionsQuestionBean question = new OptionsQuestionBean();
        setCurrentQuestion(question);
        question.setQuestionType(QuestionType.CHOOSEFROMLIST);
        changeTempPropertiesDueToCurrentQuestion();
    }

    /**
     * Creating a new question with type MULTIPLECHOICE
     */
    public void newMultipleChoiceQuestion() {
        OptionsQuestionBean question = new OptionsQuestionBean();
        setCurrentQuestion(question);
        question.setQuestionType(QuestionType.MULTIPLECHOICE);
        changeTempPropertiesDueToCurrentQuestion();
    }

    public static String income() {
        return "/pages/questionEdit?faces-redirect=true&includeViewParams=true";
    }

    public String actionSave() {
        // This controller must be available when selected node is GroupBean
        ((GroupBean) templateTreeController.getSelectedNode().getData()).addToInnerList(currentQuestion);
        return TemplateEditController.income();
    }

    public String actionCancel() {
        return TemplateEditController.income();
    }
}
