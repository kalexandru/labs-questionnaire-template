package com.engagepoint.model.question;

import com.engagepoint.model.question.utils.VariantItem;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by yaroslav on 3/5/14.
 */
public class ChooseFromListQuestionBean extends OptionsQuestionBean {
    //selected variant
    private VariantItem defaultOption;

    @XmlElement(name = "default-option")
    public VariantItem getDefaultOption() {
        return defaultOption;
    }

    public void setDefaultOption(VariantItem defaultOption) {
        this.defaultOption = defaultOption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChooseFromListQuestionBean)) return false;
        if (!super.equals(o)) return false;

        ChooseFromListQuestionBean that = (ChooseFromListQuestionBean) o;

        if (!defaultOption.equals(that.defaultOption)) return false;
        if (!options.equals(that.options)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + defaultOption.hashCode();
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        ChooseFromListQuestionBean copy = (ChooseFromListQuestionBean) super.clone();
        copy.setDefaultOption(this.defaultOption);
        return copy;
    }
}