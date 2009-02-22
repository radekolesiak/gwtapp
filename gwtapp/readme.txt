Prepare:
I. Create MySQL databases
    1. With scripts (you need a root database permission):
	- in the srv/src/main/resources/com/cuprum/server/config/db/shell/ path run restartdb-smallapp.sh
    2. Or by sql statements:
	-  command:
	    "
		create database $DB;
		grant all on $DB.* to $DB@localhost identified by '12345678';
		flush privileges;
	    "
	, where $DB is 'smallapp'.
II. Install Sun JDK 1.5 or 1.6
III. Install maven

Run:
I. Go to the ../smallapp
    1. In the 'srv' and 'app' subdirectory type: mvn jetty:run-war
Development:
I. type 'mvn eclipse:eclipse' to prepare the Eclipse project.
II. In the Eclipse: 
	- Window->Preferences->Java->Build Path->Classpath Variables->New->(Name: M2_REPO, Path: ~/.m2/repositories)
	- Import the projects.