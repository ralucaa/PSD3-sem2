
Open a terminal in this folder.

Run "ant init" to begin.
Run "ant resolve" to download library jars.
Run "ant compile" to compile the classes.
Run "ant bundles" to build the bundle jars.
Run "ant run" to start Felix.

At the Felix prompt:
"install file:example1.jar" installs example1.jar for the first time (does not update it!)
(example2.jar similarly)
"lb" lists bundles with their ID numbers.
"start <bundleID>" starts the bundle.
"stop <bundleID>" stops the bundle.
"update <bundleID>" updates the bundle - use this after rebuilding the jars.
(todo: refresh? after updating more than one bundle?)
"stop 0" exits.

To import in Eclipse, go to File -> Import -> General -> Existing Projects into Workspace
(Eclipse does the equivalent of "ant compile" continuously.)

