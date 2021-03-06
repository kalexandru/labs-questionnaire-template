Narrative:
In order to have an ability to edit template 
As a template author
I want to add “Grid" questions to question groups
	
	
GivenStories: base_stories/openDefaultPage.story			 
Scenario: 
User can add questions on the template creation/editing form.
User can select "Grid" in the Question Type drop-down list.
User can enter some text for the question in the Question Text field.
Meta: @testName         LQE-14
      @sectionName      Page 1
      @groupName        Group 1
      @questionType     GRID
      @questionText     Question text LQE-14
      @helpText         Help text LQE-14
GivenStories: base_stories/template/addTemplate.story,
              base_stories/template/tree/addSection.story,
              base_stories/template/tree/findSectionInTree.story,
              base_stories/template/tree/addGroup.story,
              base_stories/template/tree/findGroupInTree.story,
              base_stories/template/tree/addQuestion.story,
              base_stories/question/fillCommonPropertiesInQuestion.story
Then wait until all animations on page completed

Scenario: add row 1
Meta: @rowName         row 1
GivenStories: base_stories/question/addRow.story
Then wait until all animations on page completed

Scenario: add row 2
Meta: @rowName         row 2
GivenStories: base_stories/question/addRow.story
Then wait until all animations on page completed

Scenario: add column 1
Meta: @columnName      column 1
GivenStories: base_stories/question/addColumn.story
Then wait until all animations on page completed

Scenario: add column 2
Meta: @columnName      column 2
GivenStories: base_stories/question/addColumn.story
Then wait until all animations on page completed


Scenario:
User can save the template with added questions.
Meta: @testName         LQE-14
      @sectionName      Page 1
      @groupName        Group 1
      @questionName     Question ...
      @questionText     Question text LQE-14
      @helpText         Help text LQE-14
GivenStories: base_stories/question/saveQuestion.story,
              base_stories/template/saveTemplate.story,
              base_stories/template/editTemplate.story,
              base_stories/template/tree/findQuestionInTree.story,
              base_stories/template/tree/clickEditQuestion.story,
              base_stories/question/checkSavedCommonPropertiesInQuestion.story
Then wait until all animations on page completed

Scenario:
User can edit question.
Meta: @questionText     Question text LQE-14 edit
      @helpText         Help text LQE-14 edit
GivenStories: base_stories/question/fillCommonPropertiesInQuestion.story
Then wait until all animations on page completed

Scenario: 
check edited and save
Meta: @testName         LQE-14
      @sectionName      Page 1
      @groupName        Group 1
      @questionName     Question ...
      @questionText     Question text LQE-14 edit
      @helpText         Help text LQE-14 edit
GivenStories: base_stories/question/saveQuestion.story,
              base_stories/template/saveTemplate.story,
              base_stories/template/editTemplate.story,
              base_stories/template/tree/findQuestionInTree.story,
              base_stories/template/tree/clickEditQuestion.story,
              base_stories/question/checkSavedCommonPropertiesInQuestion.story
Then wait until all animations on page completed
