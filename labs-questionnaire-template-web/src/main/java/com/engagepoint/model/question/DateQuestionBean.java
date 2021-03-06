package com.engagepoint.model.question;

import com.engagepoint.model.questionnaire.GroupBean;
import com.engagepoint.model.questionnaire.QuestionType;
import org.apache.log4j.Logger;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlType(name = "dateQuestionBean", propOrder = {
        "id",
        "requiredAnswer",
        "questionText",
        "questionType",
        "questionRules",
        "helpText",
        "defaultAnswers"
})
public class DateQuestionBean extends Question {
    private static final Logger LOG = Logger.getLogger(DateQuestionBean.class);
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
    public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm");

    //Parses given string to produce a date in given format.
    public static Date parseStringToDate(String target, SimpleDateFormat format) throws ParseException{
        return format.parse(target);
    }

    private Date defaultDate;

    public DateQuestionBean() {
        super();
        defaultDate = new Date();
    }

    public DateQuestionBean(GroupBean currentGroup) {
        super(currentGroup);
        defaultDate = new Date();
    }

    @XmlTransient
    public Date getDefaultDate() {
        return defaultDate;
    }

    public void setDefaultDate(Date defaultDate) {
        this.defaultDate = defaultDate;
    }

    @Override
    @XmlElementWrapper(name = "default-answers")
    @XmlElement(name = "default-answer")
    public List<String> getDefaultAnswers() {
        List<String> list = new ArrayList<String>();
        list.add(getDateFormat().format(defaultDate));
        return list;
    }

    @Override
    public void setDefaultAnswers(List<String> list) {
        try {
            defaultDate = parseStringToDate(list.get(0), getDateFormat());
        }catch (StringIndexOutOfBoundsException e) {

            LOG.warn("StringIndexOutOFBoundsException", e);
        }catch (ParseException pe) {
           LOG.warn("ParseException", pe);
        }
    }

    public SimpleDateFormat getDateFormat() {
        SimpleDateFormat df = DATE_FORMAT;
        if (this.getQuestionType() == QuestionType.TIME) {
            df = TIME_FORMAT;
        }
        return df;
    }
}
