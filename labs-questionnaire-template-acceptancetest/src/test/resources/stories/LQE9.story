Narrative:
As a template author I want to add "Paragraph
Text" questions to question groups so that
the template meets my needs.

GivenStories: base_stories/openDefaultPage.story
Scenario: create and fill
Meta: @testName         LQE-9
      @sectionName      Page 1
      @groupName        Group 1
      @questionType     PARAGRAPHTEXT
      @questionText     Question text LQE-9
      @helpText         Help text LQE-9
GivenStories: base_stories/template/addTemplate.story,
              base_stories/template/tree/addSection.story,
              base_stories/template/tree/findSectionInTree.story,
              base_stories/template/tree/addGroup.story,
              base_stories/template/tree/findGroupInTree.story,
              base_stories/template/tree/addQuestion.story,
              base_stories/question/fillCommonPropertiesInQuestion.story
When the user fills 'form2:defaultAnswer' field with 'Default answer LQE-9'


Scenario: save and check
Meta: @testName         LQE-9
      @sectionName      Page 1
      @groupName        Group 1
      @questionName     Question ...
      @questionText     Question text LQE-9
      @helpText         Help text LQE-9
GivenStories: base_stories/question/saveQuestion.story,
              base_stories/template/saveTemplate.story,
              base_stories/template/editTemplate.story,
              base_stories/template/tree/findQuestionInTree.story,
              base_stories/template/tree/clickEditQuestion.story,
              base_stories/question/checkSavedCommonPropertiesInQuestion.story
Then element 'form2:defaultAnswer' has attribute value 'Default answer LQE-9'


Scenario: edit
Meta: @questionText     Question text LQE-9 edit
      @helpText         Help text LQE-9 edit
GivenStories: base_stories/question/fillCommonPropertiesInQuestion.story
When the user fills 'form2:defaultAnswer' field with 'Default answer LQE-9 edit'


Scenario: check and save
Meta: @testName         LQE-9
      @sectionName      Page 1
      @groupName        Group 1
      @questionName     Question ...
      @questionText     Question text LQE-9 edit
      @helpText         Help text LQE-9 edit
GivenStories: base_stories/question/saveQuestion.story,
              base_stories/template/saveTemplate.story,
              base_stories/template/editTemplate.story,
              base_stories/template/tree/findQuestionInTree.story,
              base_stories/template/tree/clickEditQuestion.story,
              base_stories/question/checkSavedCommonPropertiesInQuestion.story
Then element 'form2:defaultAnswer' has attribute value 'Default answer LQE-9 edit'
