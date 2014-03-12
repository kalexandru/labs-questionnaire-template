package com.engagepoint.controller.page;

import com.engagepoint.controller.utils.PageNavigator;
import com.engagepoint.model.question.options.ChooseFromListQuestionBean;
import com.engagepoint.model.question.options.MultipleChoiceQuestionBean;
import com.engagepoint.model.question.options.OptionsQuestion;
import com.engagepoint.model.question.rules.RulesContainer;
import com.engagepoint.model.question.utils.VariantItem;
import com.engagepoint.model.table.ListOfOptionsDataModel;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Controller for question rules.
 */
@Named
@SessionScoped
public class QuestionRuleController implements Serializable {
    //contains all rules
    private RulesContainer rulesContainer;
    //show add rule button
    private boolean addRuleButtonIsVisible;
    //show cancel rule edition button
    private boolean cancelRuleEditionButtonIsVisible;
    //show rules table
    private boolean addRulesTableIsVisible;
    //show question id list
    private boolean chooseDependentQuestionListVisible;
    //for multiple,choose from list,checkbox
    private List<VariantItem> defaultOptions;
    private ListOfOptionsDataModel dataModel;
    private VariantItem defaultOption;
    @Inject
    private TemplateEditController templateEditController;
    //
    private OptionsQuestion optionsQuestion;

    public QuestionRuleController() {
        rulesContainer = new RulesContainer();
        addRuleButtonIsVisible = true;
        optionsQuestion=new MultipleChoiceQuestionBean();
    }

    public RulesContainer getRulesContainer() {
        return rulesContainer;
    }

    public void setRulesContainer(RulesContainer rulesContainer) {
        this.rulesContainer = rulesContainer;
    }

    public boolean isAddRulesTableIsVisible() {
        return addRulesTableIsVisible;
    }

    public void setAddRulesTableIsVisible(boolean addRulesTableIsVisible) {
        this.addRulesTableIsVisible = addRulesTableIsVisible;
    }

    public boolean isCancelRuleEditionButtonIsVisible() {
        return cancelRuleEditionButtonIsVisible;
    }

    public void setCancelRuleEditionButtonIsVisible(boolean cancelRuleEditionButtonIsVisible) {
        this.cancelRuleEditionButtonIsVisible = cancelRuleEditionButtonIsVisible;
    }

    public boolean isAddRuleButtonIsVisible() {
        return addRuleButtonIsVisible;
    }

    public void setAddRuleButtonIsVisible(boolean addRuleButtonIsVisible) {
        this.addRuleButtonIsVisible = addRuleButtonIsVisible;
    }

    public boolean isChooseDependentQuestionListVisible() {
        return chooseDependentQuestionListVisible;
    }

    public void setChooseDependentQuestionListVisible(boolean chooseDependentQuestionListVisible) {
        this.chooseDependentQuestionListVisible = chooseDependentQuestionListVisible;
    }

    public VariantItem getDefaultOption() {
        return defaultOption;
    }

    public void setDefaultOption(VariantItem defaultOption) {
        this.defaultOption = defaultOption;
    }

    public ListOfOptionsDataModel getDataModel() {
        return dataModel;
    }

    public void setDataModel(ListOfOptionsDataModel dataModel) {
        this.dataModel = dataModel;
    }

    public List<VariantItem> getDefaultOptions() {
        return defaultOptions;
    }

    public void setDefaultOptions(List<VariantItem> defaultOptions) {
        this.defaultOptions = defaultOptions;
    }

    public void setOptionsQuestion(OptionsQuestion optionsQuestion) {
        this.optionsQuestion = optionsQuestion;
    }

    public OptionsQuestion getOptionsQuestion() {
        return this.optionsQuestion;
    }

    /**
     * Set elements visibility after add rule button was clicked.
     */
    public void addRuleAction() {
        setAddRuleButtonIsVisible(false);
        setCancelRuleEditionButtonIsVisible(true);
        setAddRulesTableIsVisible(true);
    }

    /**
     * Set elements visibility after cancel rule button was clicked.
     */
    public void cancelAddRuleAction() {
        setChooseDependentQuestionListVisible(false);
        setCancelRuleEditionButtonIsVisible(false);
        setAddRulesTableIsVisible(false);
        setAddRuleButtonIsVisible(true);
    }

    /**
     * Set elements visibility after create rule button was clicked.
     *
     * @param ruleName
     */
    public void createRule(String ruleName) {
        setAddRulesTableIsVisible(false);
        setChooseDependentQuestionListVisible(true);
    }

    public void setDependentQuestionAnswer() {
        switch (templateEditController.getDependentQuestion().getQuestionType()) {
            case TEXT:
                break;
            case DATE:
                break;
            case RANGE:
                break;
            case TIME:
                break;
            case PARAGRAPHTEXT:
                break;
            case CHOOSEFROMLIST:
                optionsQuestion = (ChooseFromListQuestionBean) templateEditController.getDependentQuestion();
                dataModel = new ListOfOptionsDataModel(optionsQuestion.getOptions());
                break;
            case FILEUPLOAD:
                break;
            case MULTIPLECHOICE:
                optionsQuestion = (MultipleChoiceQuestionBean) templateEditController.getDependentQuestion();
                dataModel = new ListOfOptionsDataModel(optionsQuestion.getOptions());
                break;
            case CHECKBOX:
                break;
            case GRID:
                break;
        }


    }
}
