PSD3 Team K
====
Component Based System Exercise
====

----

To import in Eclipse, go to File -> Import -> General -> Existing Projects into Workspace. This is for editing the code; use **ant** to build/run it.

All ant commands should be run from the "osgi" folder.

Run "ant bundles" to build the bundle jars only.

For the JBehave tests:

* To make them work on a lab machine, download ant-junit-1.7.1.jar somewhere outside the project. The jar is available at http://mvnrepository.com/artifact/org.apache.ant/ant-junit/1.7.1 . Put it in your classpath, e.g. in your .bashrc,

        export CLASSPATH=$CLASSPATH:/path/to/ant-junit-1.7.1.jar

* Run "ant test" to generate the reports (this will rebuild the bundles automatically).
* Check the reports in jbehave/view/reports.html

If files have become inconsistent on your machine, run "ant cleanrun" to delete runtime files, "ant clean" to delete all intermediate files, or "ant cleanall" to delete intermediate files and ivy dependencies.

----

This thing is fragile. Don't break the build!

Work on lab machines. Don't change too much at once.

When pulling, use "git pull --rebase" to keep the commit history granular, so that it's easier to track down any problems.

Before pushing, run "ant test" as above, and check the reports to make sure you haven't made things worse.

After pushing, go to Jenkins and hit "build now". When it's done, check the reports again at
http://hoved.dcs.gla.ac.uk:8080/job/TeamKMainBuild/ws/osgi/jbehave/view/reports.html (there are slight differences in the build environment there). If it broke, try using "Workspace -> Wipe Out Current Workspace" and build again. If this doesn't help, revert your change.

----

Run "ant run" to start the Felix shell (gogo). This won't be needed much any more.

At the Felix prompt:

* "install file:jars/example1.jar" installs example1.jar for the first time (does not update it!). Similarly for the other jar files.
* "lb" lists bundles with their ID numbers.
* "start BUNDLEID" starts the bundle.
* "stop BUNDLEID" stops the bundle.
* "update BUNDLEID" updates the bundle - use this after rebuilding the jars.
* (todo: refresh? after updating more than one bundle?)
* "stop 0" exits.

