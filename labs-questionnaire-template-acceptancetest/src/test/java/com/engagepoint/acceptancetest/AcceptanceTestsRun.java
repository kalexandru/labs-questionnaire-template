package com.engagepoint.acceptancetest;

import net.thucydides.core.Thucydides;
import net.thucydides.core.reports.html.HtmlAggregateStoryReporter;
import net.thucydides.jbehave.ThucydidesJUnitStories;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.StoryFinder;

import java.io.File;
import java.util.List;

import static java.util.Arrays.asList;

public class AcceptanceTestsRun {

    private static String testsFolder;

	public static void main(String[] args) throws Throwable {
        if(args.length == 1) {
            testsFolder = args[0];
            new RunableBillspanIT().run();
        } else {
            testsFolder = "stories";
            new RunableBillspanIT().run();
        }
        File sourceDirectory = new File("target/site/thucydides/");
        HtmlAggregateStoryReporter reporter = new HtmlAggregateStoryReporter(Thucydides.getDefaultProjectKey());
        reporter.setOutputDirectory(sourceDirectory);
        reporter.generateReportsForTestResultsFrom(sourceDirectory);
		System.exit(0);
	}

    public static class RunableBillspanIT extends ThucydidesJUnitStories {

        public RunableBillspanIT() {
            configuredEmbedder().embedderControls().doIgnoreFailureInStories(true).doIgnoreFailureInView(true);
        }

        @Override
        public List<String> storyPaths() {
            return new StoryFinder().findPaths(CodeLocations.codeLocationFromClass(getClass()),
                    asList("**/" + testsFolder + "/**/*.story"), asList(""));
        }
    }

}
